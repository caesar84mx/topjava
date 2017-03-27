package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * Created by caesar-84 on 3/26/17.
 */
public interface MealService
{
    Meal getById(int id);
    void saveOrUpdate(Meal meal);
    void deleteById(int id);
    List<Meal> getList();
}
