package com.xuan.diary.controller;

import com.xuan.diary.common.message.Message;
import com.xuan.diary.common.response.Response;
import com.xuan.diary.common.response.ResponseCode;
import com.xuan.diary.model.request.UserRequest;
import com.xuan.diary.model.response.UserResponse;
import com.xuan.diary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("users")
@Validated
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public Response<List<UserResponse>> getAll() {
        List<UserResponse> userResponses = userService.findAllByStatus();
        return new Response<>(userResponses, ResponseCode.R_200, Message.RESPONSE_OK);
    }

    @PostMapping()
    public Response<UserResponse> create(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.create(userRequest);
        return new Response<>(userResponse, ResponseCode.R_201, Message.RESPONSE_CREATE);
    }

    @PutMapping("/{id}")
    public Response<UserResponse> updateStudent( @NotNull(message = Message.NOT_NULL) @Min(value = 0, message = Message.MIN_ID) @PathVariable(name = "id") Integer id,
                                                @Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.update(userRequest, id);
        return new Response<>(userResponse, ResponseCode.R_200, Message.RESPONSE_OK);
    }

    @DeleteMapping("/{id}")
    public Response<UserResponse> delete(@NotNull(message = Message.NOT_NULL) @Min(value = 0, message = Message.MIN_ID) @PathVariable(name = "id") Integer id) {
//        userService.delete(id);
        return new Response<>(ResponseCode.R_200, "OK");
    }
}
