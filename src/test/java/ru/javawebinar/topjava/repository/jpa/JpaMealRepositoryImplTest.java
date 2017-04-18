package ru.javawebinar.topjava.repository.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by caesar-84 on 4/17/17.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class JpaMealRepositoryImplTest {

    static {
        SLF4JBridgeHandler.install();}

    private static final Comparator<Meal> MEAL_COMPARATOR = Comparator.comparing(Meal::getDateTime).reversed();

    @Autowired
    private MealRepository repository;

    @Test
    public void testSave() throws Exception {
        Meal created = getCreated();
        repository.save(created, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(created, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1), repository.getAll(USER_ID));
    }

    @Test
    public void testDelete() throws Exception {
        repository.delete(MEAL1_ID, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2), repository.getAll(USER_ID));
    }

    @Test
    public void testGet() throws Exception {
        Meal actual = repository.get(ADMIN_MEAL_ID, ADMIN_ID);
        MATCHER.assertEquals(ADMIN_MEAL1, actual);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(MEALS, repository.getAll(USER_ID));
    }

    @Test
    public void testGetBetween() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL3, MEAL2, MEAL1),
                repository.getBetween(LocalDateTime.of(2015, Month.MAY, 30, 10, 0),
                        LocalDateTime.of(2015, Month.MAY, 30, 20, 0), USER_ID));
    }
}