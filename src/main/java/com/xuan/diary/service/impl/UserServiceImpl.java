package com.xuan.diary.service.impl;

import com.xuan.diary.common.exception.NotFoundException;
import com.xuan.diary.common.status.Status;
import com.xuan.diary.mapper.UserMapper;
import com.xuan.diary.model.entity.User;
import com.xuan.diary.model.request.UserRequest;
import com.xuan.diary.model.response.UserResponse;
import com.xuan.diary.repository.UserRepository;
import com.xuan.diary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    /**
     * get All UserResponse
     *
     * @return List of UserResponse
     */
    @Override
    public List<UserResponse> findAllByStatus() {
        return userMapper.map(userRepository.findAllByStatus(Status.ACTIVE));
    }

    /**
     * create UserResponse
     *
     * @return UserResponse
     * @RequestBody UserResponse
     */
    @Override
    public UserResponse create(UserRequest userRequest) {
        User user = userMapper.map(userRequest);
        return userMapper.map(userRepository.save(user));
    }

    /**
     * update UserResponse
     *
     * @return UserResponse
     * @RequestBody UserResponse
     */
    @Override
    public UserResponse update(UserRequest userRequest, Integer id) {
        User user = userMapper.map(userRequest, userRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found User")));
        return userMapper.map(userRepository.save(user));
    }

}
