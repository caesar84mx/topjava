package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Meal save(Meal meal, int userId) throws NotFoundException {
        if (meal.getId() != userId) throw
                new NotFoundException("Error while saving. Meal " + meal.getId() +
                        " does not belong to current user");
        return repository.save(meal);
    }

    @Override
    public boolean delete(int id, int userId) throws NotFoundException {
        this.get(id, userId);
        return repository.delete(id);
    }

    @Override
    public Meal get(int mealId, int userId)  throws NotFoundException {
        Meal meal = repository.get(mealId);
        if (meal.getUserId() != userId) throw
                new NotFoundException("Error while getting. Meal " + meal.getId() +
                        " does not belong to current user");
        return meal;
    }

    @Override
    public Collection<Meal> getAll(int userId)
    {
        return repository.getAll().stream()
                .filter(meal -> meal.getUserId() == userId)
                .collect(Collectors.toList());
    }
}