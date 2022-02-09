package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.InMemoryMealRepo;
import ru.javawebinar.topjava.repository.MealRepo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    MealRepo repository;

    public MealServlet() {
        this.repository = new InMemoryMealRepo();
    }

    private static int updatedMealId;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward;
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            log.debug("delete meal - " + mealId);
            repository.delete(mealId);
            forward = "/meals.jsp";
            request.setAttribute("listOfMealTo", MealsUtil.mealToMealTo(repository.getList()));
        } else if (action.equalsIgnoreCase("listOfMeals")) {
            forward = "/meals.jsp";
            log.debug("get list of meals");
            request.setAttribute("listOfMealTo", MealsUtil.mealToMealTo(repository.getList()));
        } else if (action.equalsIgnoreCase("update")) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            log.debug("update meal - " + mealId);
            Meal updatedMeal = repository.get(mealId);
            updatedMealId = mealId;
            forward = "/addMeal.jsp";
            request.setAttribute("updatedMeal", updatedMeal);
        } else { log.debug("add meal");
            forward = "/addMeal.jsp";
            updatedMealId = -1;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        log.debug("meal added/updated");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("date"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        if (updatedMealId == -1)
            repository.add(new Meal(-1, dateTime, description, calories));
        else repository.add(new Meal(updatedMealId, dateTime, description, calories));
        RequestDispatcher view = request.getRequestDispatcher("/meals.jsp");
        request.setAttribute("listOfMealTo", MealsUtil.mealToMealTo(repository.getList()));
        view.forward(request, response);
    }

}
