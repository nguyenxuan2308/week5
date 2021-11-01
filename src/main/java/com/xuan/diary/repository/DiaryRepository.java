package com.xuan.diary.repository;

import com.xuan.diary.common.status.Status;
import com.xuan.diary.model.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Integer> , JpaSpecificationExecutor<Diary> {

    List<Diary> findAllByStatus(Status status);

    List<Diary> findAllByUserIdAndStatus(Integer id, Status status);

    List<Diary> findAllByIdIn(List<Integer> ids);

//    @Query(value = "select * from diaries d where"
//            + "(d.id in :ids or :ids is null ) "
//            + " and (d.title like cast(:title as text) or :title is null )"
//            + " and (d.favorites_list = :favorite_list or :favorite_list is null ) "
//            + " and(  d.date_create >= cast(:time_start as date ) )"
//            + " and(  d.date_create <= cast(:time_end as date ) )"
//            , nativeQuery = true)
//    List<Diary> filter(@Param("ids") List<Integer> ids, @Param("title") String title, @Param("favorite_list") boolean favoritesList, @Param("time_start") Timestamp timeStart, @Param("time_end") Timestamp timeEnd);


//    List<Diary> findByStatusAndThemeIdIn(Status status, Set<Integer> ids);
//    List<Diary> findAllByFavoritesListAndStatus(Boolean type, Status status);
//
//    List<Diary> findAllByTitleAndStatus(String title, Status status);
//
//    List<Diary> findAllByStatusAndDateCreateBetween(Status status, LocalDateTime timeStart, LocalDateTime timeEnd);

    @Query("UPDATE diaries u SET u.status = 'DELETE' WHERE u.id = ?1")
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    void deleteDiaryById(int id);

    @Query("UPDATE diaries u SET u.status = 'DELETE' WHERE u.userId = ?1")
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    void deleteAllByUserId(Integer userId);
}
