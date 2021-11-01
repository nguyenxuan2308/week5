package com.xuan.diary.repository;

import com.xuan.diary.common.status.Status;
import com.xuan.diary.model.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ThemeRepository extends JpaRepository<Theme,Integer> {

    List<Theme> findAllByStatus(Status status);

    List<Theme> findAllByIdIn(List<Integer> ids);

    @Query("UPDATE themes u SET u.status = 'DELETE' WHERE u.id = ?1")
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    void deleteThemeById(int id);
}
