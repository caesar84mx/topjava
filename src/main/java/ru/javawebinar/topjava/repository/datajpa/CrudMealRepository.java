package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Transactional
    @Override
    Meal save(Meal meal);

    @Query("SELECT m FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    Meal findOneByUserId(@Param("id") Integer id, @Param("userId") int userId);

    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId")
    List<Meal> findAllByUserId(Sort sort, @Param("userId") int userId);

    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId AND m.dateTime BETWEEN :startTime AND :endTime")
    List<Meal> getBetween(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime, @Param("userId") int userId);
}