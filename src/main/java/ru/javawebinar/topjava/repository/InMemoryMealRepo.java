package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealRepo implements MealRepo {

    Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(7);

    {
        meals.put(1, new Meal(1, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        meals.put(2, new Meal(2, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        meals.put(3, new Meal(3, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        meals.put(4, new Meal(4, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        meals.put(5, new Meal(5, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        meals.put(6, new Meal(6, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        meals.put(7, new Meal(7, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }


    @Override
    public  void add(Meal meal) {
        Objects.requireNonNull(meal);
        if (meals.containsKey(meal.getMealId())) {
            meals.put(meal.getMealId(), meal);
        } else {
            int newId = idCounter.incrementAndGet();
            meal.setMealId(newId);
            meals.put(newId, meal);
        }
    }

    @Override
    public  void delete(int id) {
        meals.remove(id);
    }


    @Override
    public Meal get(int id) {
        return meals.get(id);
    }



    @Override
    public  List<Meal> getList() {
        return new ArrayList<>(meals.values());
    }
}
