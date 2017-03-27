package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoHardcodedImpl;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * Created by caesar-84 on 3/26/17.
 */
public class MealServiceHardcodedImpl implements MealService
{
    private MealDao dao = MealDaoHardcodedImpl.getInstance();

    @Override
    public Meal getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void saveOrUpdate(Meal meal) {
        dao.saveOrUpdate(meal);
    }

    @Override
    public void deleteById(int id) {
        dao.deleteById(id);
    }

    @Override
    public List<Meal> getList() { return dao.getList(); }
}
