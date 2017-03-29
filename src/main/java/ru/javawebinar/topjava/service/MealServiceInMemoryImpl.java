package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoInMemoryImpl;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * Created by caesar-84 on 3/26/17.
 */
public class MealServiceInMemoryImpl implements MealService
{
    private static final MealService INSTANCE = new MealServiceInMemoryImpl();
    private MealDao dao;

    private MealServiceInMemoryImpl()
    {
        dao = MealDaoInMemoryImpl.getInstance();
    }

    public static MealService getInstance() { return INSTANCE; }

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
