package com.xuan.diary.repository;

import com.xuan.diary.common.status.Status;
import com.xuan.diary.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByStatus(Status status);

    @Query("UPDATE users u SET u.status = 'DELETE' WHERE u.id = ?1")
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    void deleteUserById(int id);
}
