package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by caesar-84 on 4/10/17.
 */
@ContextConfiguration({
        "classpath:spring/spring-test-app.xml",
        "classpath:spring/spring-test-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService mealService;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void get() throws Exception {
        Meal meal = mealService.get(1, TEST_USER_ID);
        MATCHER.assertEquals(TEST_LUNCH_MEAL, meal);
    }

    @Test
    public void delete() throws Exception {
        mealService.delete(1, TEST_USER_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(TEST_SUPPER_MEAL), mealService.getAll(TEST_USER_ID));
    }

    @Test
    public void getBetweenDates() throws Exception {
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> all = mealService.getAll(TEST_USER_ID);
        MATCHER.assertCollectionEquals(all, mealService.getAll(TEST_USER_ID));
    }

    @Test
    public void update() throws Exception {
        Meal meal = new Meal(LocalDateTime.now(), "Update test meal", 500);
        mealService.save(meal, TEST_USER_ID);
        meal.setCalories(200);
        mealService.update(meal, TEST_USER_ID);
        MATCHER.assertEquals(meal, mealService.get(meal.getId(), TEST_USER_ID));
    }

    @Test
    public void save() throws Exception {
        Meal meal = new Meal(LocalDateTime.now(), "Save test meal", 200);
        Meal saved = mealService.save(meal, TEST_USER_ID);
        meal.setId(saved.getId());
        MATCHER.assertEquals(meal, saved);
    }

}