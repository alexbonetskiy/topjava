package ru.javawebinar.topjava.web.meal;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Controller
@RequestMapping("/meals")
public class JspMealController extends AbstractMealController {


    @GetMapping
    public String getMeals(Model model) {
        model.addAttribute("meals", super.getAll());
        return "meals";
    }

    @GetMapping("/filter")
    public String getMealsFiltered(HttpServletRequest request, Model model) {
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
        LocalTime startTime = LocalTime.parse(request.getParameter("startTime"));
        LocalTime endTime = LocalTime.parse(request.getParameter("endTime"));
        model.addAttribute("meals", super.getBetween(startDate, startTime, endDate, endTime));
        return "meals";
    }


    @GetMapping("/create")
    public String getMealForm(Model model) {
        Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meal", meal);
        model.addAttribute("operation", "Create");
        return "mealForm";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable(name = "id") String id) {
        model.addAttribute("meal", super.get(Integer.parseInt(id.split("=")[1])));
        return "mealForm";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        delete(Integer.parseInt(id.split("=")[1]));
        return "redirect:/meals";
    }

    @PostMapping
    public String create(HttpServletRequest request) {
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));

        if (!StringUtils.hasLength(request.getParameter("id"))) {
            Meal meal = new Meal(dateTime, description, calories);
            super.create(meal);}
        else {int id = Integer.parseInt(request.getParameter("id"));
            Meal meal = new Meal(id, dateTime, description, calories);
            super.update(meal, id);}
        return "redirect:/meals";
    }


}
