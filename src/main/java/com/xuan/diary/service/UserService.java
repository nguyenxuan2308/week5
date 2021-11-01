package com.xuan.diary.service;

import com.xuan.diary.model.request.UserRequest;
import com.xuan.diary.model.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> findAllByStatus();

    UserResponse create(UserRequest userRequest);

    UserResponse update(UserRequest userRequest, Integer id);

}
