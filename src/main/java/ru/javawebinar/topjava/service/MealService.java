package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;

public interface MealService {
    Meal save(Meal meal);

    void delete(int id);

    Meal get(int mealId, int userId) throws NotFoundException;

    Collection<Meal> getAll();
}