package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);
    
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);

    {
        MealsUtil.meals.forEach(meal -> save(meal, 1));
        save(new Meal(null, LocalDateTime.now(), "плотный обед", 1400), 0);
        save(new Meal(null, LocalDateTime.now(), "плотный ужин", 1600), 0);
    }

    @Override
    public Meal save(Meal meal, int userId) {
        Objects.requireNonNull(meal);
        log.info("save meal = {}, userId = {}", meal, userId);

        if (meal.isNew()) {
            meal.setId(counter.getAndIncrement());
            Map<Integer, Meal> meals = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
            return meals.put(meal.getId(), meal);
        } else if (get(meal.getId(), userId) == null) return null;
        return repository.get(userId).computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete id = {}, userId = {}", id, userId);
        Map<Integer, Meal> meals = repository.get(userId);
        return meals.remove(id)!= null;
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get id = {}, userId = {}", id, userId);
        Map<Integer, Meal> meals = repository.get(userId);
        return meals == null ? null : meals.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        log.info("getAll id = {}, userId = {}", 1d, userId);
        return repository.get(userId).values().stream().sorted((meal1, meal2) -> meal2.getDate().compareTo(meal1.getDate())).collect(Collectors.toList());
    }

    public List<Meal> getFiltered(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, int authUserId) {
        List<Meal> list = getAll(authUserId);
        if(startDate != null && endDate !=null)
          list = list.stream().filter(meal -> DateTimeUtil.isBetweenOpen(meal.getDate(), startDate, endDate)).collect(Collectors.toList());
        if(startTime != null && endTime !=null && endTime.isAfter(startTime))
            list = list.stream().filter(meal -> DateTimeUtil.isBetweenOpen(meal.getTime(), startTime, endTime)).collect(Collectors.toList());
        return list;
    }
}

