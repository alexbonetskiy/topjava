package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Meal {

    private int mealId;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;


    public Meal(int id, LocalDateTime dateTime, String description, int calories) {
        this.mealId = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }
}
