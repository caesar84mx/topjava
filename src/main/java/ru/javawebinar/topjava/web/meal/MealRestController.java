package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MealRestController {
    @Autowired
    private MealService service;

    public Meal save(Meal meal)
    {
        meal.setId(AuthorizedUser.id());
        return service.save(meal);
    }

    public void delete(int id)
    {
        service.delete(id);
    }

    public Meal get(int mealId)
    {
        Meal meal;
        try
        {
            meal = service.get(mealId, AuthorizedUser.id());
        }
        catch (NotFoundException ex)
        {
            return null;
        }
        return meal;
    }

    public List<Meal> getAll()
    {
        return service.getAll().stream()
                .filter(meal -> meal.getUserId() == AuthorizedUser.id())
                .collect(Collectors.toList());
    }
}