package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/ajax/meals")
public class MealUiController extends AbstractMealController{

        @Override
        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void delete(@PathVariable int id) {
            super.delete(id);
        }

        @PostMapping
        public void create (@RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime, @RequestParam String description, @RequestParam int calories) {
            Meal meal = new Meal(null, dateTime, description, calories);
            super.create(meal);
        }

        @GetMapping("/filter")
        public List<MealTo> getBetween(@RequestParam  LocalDate startDate, @RequestParam LocalTime startTime, @RequestParam LocalDate endDate, @RequestParam LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
        }
}

