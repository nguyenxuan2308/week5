package com.xuan.diary.mapper;

import com.xuan.diary.common.status.Status;
import com.xuan.diary.model.entity.User;
import com.xuan.diary.model.request.UserRequest;
import com.xuan.diary.model.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    /**
     * mapper UserRequest to User Entity
     *
     * @return User
     */
    public User map(UserRequest request) {
        return new User().setName(request.getName())
                .setPassword(request.getPassword())
                .setUserName(request.getUserName())
                .setStatus(Status.valueOf(request.getStatus()));
    }

    /**
     * mapper User to StudentResponse
     *
     * @return StudentResponse
     */
    public UserResponse map(User user) {
        return new UserResponse()
                .setId(user.getId())
                .setName(user.getName())
                .setPassword(user.getPassword())
                .setUserName(user.getUserName());
    }

    /**
     * mapper UserRequest to User Exist
     *
     * @return User
     */
    public User map(UserRequest source, User target) {
        return target.setUserName(source.getUserName())
                .setPassword(source.getPassword())
                .setName(source.getName());
    }

    /**
     * mapper List User to List StudentResponse
     *
     * @return List<StudentResponse>
     */
    public List<UserResponse> map(List<User> users) {
        return users.stream().map(this::map).collect(Collectors.toList());
    }
}