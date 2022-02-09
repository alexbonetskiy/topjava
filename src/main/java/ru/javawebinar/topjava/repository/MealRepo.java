package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealRepo {
    public void add(Meal meal);

    public void delete(int id);

    public Meal get(int id);

    public List<Meal> getList();
}
