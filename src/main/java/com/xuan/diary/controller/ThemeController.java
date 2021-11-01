package com.xuan.diary.controller;

import com.xuan.diary.common.message.Message;
import com.xuan.diary.common.response.Response;
import com.xuan.diary.common.response.ResponseCode;
import com.xuan.diary.model.request.ThemeRequest;
import com.xuan.diary.model.response.ThemeResponse;
import com.xuan.diary.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/themes")
@Validated
@RequiredArgsConstructor
public class ThemeController {

    private final ThemeService themeService;

    @GetMapping()
    public Response<List<ThemeResponse>> findAll() {
        List<ThemeResponse> themeResponses = themeService.findAllByStatus();
        return new Response<>(themeResponses, ResponseCode.R_200, Message.RESPONSE_OK);
    }

    @PostMapping()
    public Response<ThemeResponse> create(@Valid @RequestBody ThemeRequest themeRequest) {
        return new Response<>(themeService.create(themeRequest), ResponseCode.R_201, Message.RESPONSE_CREATE);
    }

    @PutMapping("/{id}")
    public Response<ThemeResponse> update(@NotNull(message = Message.NOT_NULL) @Min(value = 0, message = Message.MIN_ID) @PathVariable(name = "id") Integer id,
                                          @Valid @RequestBody ThemeRequest themeRequest) {
        return new Response<>(themeService.update(themeRequest, id), ResponseCode.R_200, Message.RESPONSE_OK);
    }

    @DeleteMapping("/{id}")
    public Response<ThemeResponse> delete(@NotNull(message = Message.NOT_NULL) @Min(value = 0, message = Message.MIN_ID) @PathVariable(name = "id") Integer id) {
//        themeService.deleteThemeById(id);
        return new Response<>(ResponseCode.R_200, Message.RESPONSE_OK);
    }


}
