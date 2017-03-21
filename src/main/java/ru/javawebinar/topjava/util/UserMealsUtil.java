package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList,
                                                                   LocalTime startTime,
                                                                   LocalTime endTime,
                                                                   int caloriesPerDay)
    {
        Map<LocalDate, Integer> totalCaloriesPerDay = mealList.stream()
                .collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(),
                         Collectors.summingInt(UserMeal::getCalories)));


        return mealList.stream()
                .filter(meal -> TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime))
                .map(meal -> new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                        totalCaloriesPerDay.get(meal.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    private static List<UserMealWithExceed> getFilteredWithExceededLoop(List<UserMeal> mealList,
                                                                        LocalTime startTime,
                                                                        LocalTime endTime,
                                                                        int caloriesPerDay)
    {
        Map<LocalDate, Integer> totalCalPerDay = new HashMap<>();

        mealList.forEach(meal -> totalCalPerDay.merge(meal.getDateTime().toLocalDate(), meal.getCalories(),
                Integer::sum));

        /*for (UserMeal meal: mealList)
        {
            if (!totalCalPerDay.containsKey(meal.getDateTime().toLocalDate()))
                totalCalPerDay.put(meal.getDateTime().toLocalDate(), meal.getCalories());
            else
            {
                int sum = totalCalPerDay.get(meal.getDateTime().toLocalDate()) + meal.getCalories();
                totalCalPerDay.put(meal.getDateTime().toLocalDate(), sum);
            }
        }*/

        List<UserMealWithExceed> resultList = new ArrayList<>();
        for (UserMeal meal: mealList)
        {
            if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime))
            {
                resultList.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                        totalCalPerDay.get(meal.getDateTime().toLocalDate()) > caloriesPerDay));
            }
        }

        return resultList;
    }
}
