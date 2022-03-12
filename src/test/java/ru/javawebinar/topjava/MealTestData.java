package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int MEAL_ID = START_SEQ+3;

    public static final Meal userMeal1 = new Meal(MEAL_ID, LocalDateTime.of(2022, Month.MARCH, 3, 20,20,10), "завтрак", 600);
    public static final Meal userMeal2 = new Meal(MEAL_ID+1, LocalDateTime.of(2022, Month.MARCH, 3, 20,20,15), "обед", 1000);
    public static final Meal adminMeal1 = new Meal(MEAL_ID+2, LocalDateTime.of(2022, Month.MARCH, 3, 20,20,10), "завтрак", 300);
    public static final Meal adminMeal2 = new Meal(MEAL_ID+3, LocalDateTime.of(2022, Month.MARCH, 3, 20,20,10), "завтрак", 300);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2022, Month.MARCH, 6, 20,20,10), "полдник", 1300);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(userMeal1);
        updated.setCalories(3000);
        updated.setDescription("хавчик");
        updated.setDateTime(LocalDateTime.of(2020, Month.MARCH, 3, 20,20,10));
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }
}
