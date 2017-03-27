package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceHardcodedImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * User: caesar-84
 * Date: 3/26/17.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);
    private final MealService mealService;

    public MealServlet() {
        super();
        mealService = new MealServiceHardcodedImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String forward = "/meals.jsp";

        if (action != null) {
            //meals?action=delete&mealId=1
            if (action.equalsIgnoreCase("delete")) {
                int mealId = Integer.parseInt(req.getParameter("mealId"));
                mealService.deleteById(mealId);
            }
            //meals?action=createOrUpdate&mealId=1
            else if (action.equalsIgnoreCase("createOrUpdate"))
            {
                int id = Integer.parseInt(req.getParameter("mealId"));
                Meal meal;
                if (id != 0) meal = mealService.getById(id);
                else meal = new Meal(LocalDateTime.now().withSecond(0).withNano(0), "Введите описание", 500);
                req.setAttribute("editedMeal", meal);
                forward = "/mealEdit.jsp";
            }
        }

        List<Meal> mealList = mealService.getList();
        List<MealWithExceed> mealWithExceedList =
                MealsUtil.getFilteredWithExceeded(mealList, LocalTime.MIN, LocalTime.MAX, 2000);
        mealWithExceedList.sort(Comparator.comparing(MealWithExceed::getDateTime));
        req.setAttribute("meals", mealWithExceedList);
        req.getRequestDispatcher(forward).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        int id = Integer.parseInt(req.getParameter("id"));

        Meal meal = new Meal(dateTime, description, calories);
        meal.setId(id);

        mealService.saveOrUpdate(meal);

        resp.sendRedirect("meals");
    }
}