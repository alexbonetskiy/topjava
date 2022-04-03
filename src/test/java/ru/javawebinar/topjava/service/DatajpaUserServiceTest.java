package ru.javawebinar.topjava.service;


import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;

import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.UserTestData.USER_MATCHER;

@ActiveProfiles(DATAJPA)
public class DatajpaUserServiceTest extends AbstractUserServiceTest{

    @Test
    public void getUserWithMeals() {
      User user = service.getUserWithMeals(USER_ID);
      USER_MATCHER.assertMatch(UserTestData.user, user);
      MealTestData.MEAL_MATCHER.assertMatch(MealTestData.meals, user.getMeals());
    }

}
