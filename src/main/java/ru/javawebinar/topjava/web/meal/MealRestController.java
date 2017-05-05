package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by caesar_84 on 5/5/2017.
 */
@Controller
@RequestMapping(value = "/meals")
public class MealRestController extends AbstractMealController {
    @Autowired
    public MealRestController(MealService service) {
        super(service);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Override
    public Meal get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @Override
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @Override
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @Override
    public Meal create(@RequestBody Meal meal) {
        return super.create(meal);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    @Override
    public void update(@RequestBody Meal meal, @PathVariable("id") int id) {
        super.update(meal, id);
    }


    @RequestMapping(value = "/filtered")
    @Override
    public List<MealWithExceed> getBetween(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("startTime") LocalTime startTime,
            @RequestParam("endDate") LocalDate endDate,
            @RequestParam("endTime") LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}
