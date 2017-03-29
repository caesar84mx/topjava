package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by caesar-84 on 3/26/17.
 */
public class MealDaoInMemoryImpl implements MealDao {
    private static Map<Integer, Meal> hcMealDb = new ConcurrentHashMap<>();
    private static final AtomicInteger counter = new AtomicInteger(1);

    private MealDaoInMemoryImpl()
    {
        //день первый
        LocalDateTime dateTime = LocalDateTime.of(2017, 3, 1, 7, 0);
        Meal meal = new Meal(dateTime, "Арепа с ветчиной и сыром, кофе", 300);
        meal.setId(getId());
        hcMealDb.put(meal.getId(), meal);

        dateTime = LocalDateTime.of(2017, 3, 1, 12, 30);
        meal = new Meal(dateTime, "Тушеная курица с картошкой, кофе", 1000);
        meal.setId(getId());
        hcMealDb.put(meal.getId(), meal);

        dateTime = LocalDateTime.of(2017, 3, 1, 19, 0);
        meal = new Meal(dateTime, "Оладьи с сыром, чай", 500);
        meal.setId(getId());
        hcMealDb.put(meal.getId(), meal);

        dateTime = LocalDateTime.of(2017, 3, 1, 23, 30);
        meal = new Meal(dateTime, "5 БУТЕРОВ С КОЛБАСОООЙ, ПОЛЛИТРА ЧААААЯЯЯЯ!!!!!1111РАС", 1000);
        meal.setId(getId());
        hcMealDb.put(meal.getId(), meal);

        //день второй
        dateTime = LocalDateTime.of(2017, 3, 2, 7, 0);
        meal = new Meal(dateTime, "Оладьи с сыром, кофе", 500);
        meal.setId(getId());
        hcMealDb.put(meal.getId(), meal);

        dateTime = LocalDateTime.of(2017, 3, 2, 12, 30);
        meal = new Meal(dateTime, "Курица с рисом, кофе", 1000);
        meal.setId(getId());
        hcMealDb.put(meal.getId(), meal);

        dateTime = LocalDateTime.of(2017, 3, 2, 19, 0);
        meal = new Meal(dateTime, "Арепа с ветчиной и сыром, чай", 300);
        meal.setId(getId());
        hcMealDb.put(meal.getId(), meal);

        //день третий
        dateTime = LocalDateTime.of(2017, 3, 3, 7, 0);
        meal = new Meal(dateTime, "Яичница с беконом, кофе", 500);
        meal.setId(getId());
        hcMealDb.put(meal.getId(), meal);

        dateTime = LocalDateTime.of(2017, 3, 3, 12, 30);
        meal = new Meal(dateTime, "Говяжий стейк, салат, кофе", 1000);
        meal.setId(getId());
        hcMealDb.put(meal.getId(), meal);

        dateTime = LocalDateTime.of(2017, 3, 3, 19, 0);
        meal = new Meal(dateTime, "Арепа с мясом, чай", 300);
        meal.setId(getId());
        hcMealDb.put(meal.getId(), meal);

        //день четвертвый
        dateTime = LocalDateTime.of(2017, 3, 4, 7, 0);
        meal = new Meal(dateTime, "Яичница с беконом, кофе", 500);
        meal.setId(getId());
        hcMealDb.put(meal.getId(), meal);

        dateTime = LocalDateTime.of(2017, 3, 4, 12, 30);
        meal = new Meal(dateTime, "Рыба, рис, салат, кофе", 1000);
        meal.setId(getId());
        hcMealDb.put(meal.getId(), meal);

        dateTime = LocalDateTime.of(2017, 3, 4, 19, 0);
        meal = new Meal(dateTime, "Чай", 20);
        meal.setId(getId());
        hcMealDb.put(meal.getId(), meal);

        //день пятый
        dateTime = LocalDateTime.of(2017, 3, 5, 7, 45);
        meal = new Meal(dateTime, "Кофе", 20);
        meal.setId(getId());
        hcMealDb.put(meal.getId(), meal);

        dateTime = LocalDateTime.of(2017, 3, 5, 12, 30);
        meal = new Meal(dateTime, "Кукурузный пирожок с ветчиной и сыром, кофе", 500);
        meal.setId(getId());
        hcMealDb.put(meal.getId(), meal);

        dateTime = LocalDateTime.of(2017, 3, 5, 20, 10);
        meal = new Meal(dateTime, "Чай", 20);
        meal.setId(getId());
        hcMealDb.put(meal.getId(), meal);

        dateTime = LocalDateTime.of(2017, 3, 5, 23, 10);
        meal = new Meal(dateTime, "АРЕПА С ЧИЧАРРОНОМ, КУРИНАЯ НОГААА, БУУТЕР С КОЛБАСООООЙ, БУТЫЛКА РООМА!!!!!1111РАС", 1500);
        meal.setId(getId());
        hcMealDb.put(meal.getId(), meal);
    }

    private static final MealDaoInMemoryImpl INSTANCE = new MealDaoInMemoryImpl();

    public static MealDaoInMemoryImpl getInstance() { return INSTANCE; }

    private static int getId(){ return counter.getAndIncrement(); }
    
    @Override
    public Meal getById(int id) {
        return hcMealDb.get(id);
    }

    @Override
    public void saveOrUpdate(Meal meal) {
        if (meal.getId() == 0)
        {
            meal.setId(getId());
            hcMealDb.put(meal.getId(), meal);
        }
        else hcMealDb.put(meal.getId(), meal);
    }

    @Override
    public void deleteById(int id) {
        hcMealDb.remove(id);
    }

    @Override
    public List<Meal> getList() {
        return new ArrayList<>(hcMealDb.values());
    }
}
