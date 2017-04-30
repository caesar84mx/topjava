package ru.javawebinar.topjava.service.datajpaprofile;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.MealServiceTest;

/**
 * Created by caesar_84 on 4/30/2017.
 */
@ActiveProfiles(Profiles.DATAJPA)
public class MealServiceDataJpaTest extends MealServiceTest {
}
