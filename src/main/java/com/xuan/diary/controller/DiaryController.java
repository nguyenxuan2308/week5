package com.xuan.diary.controller;

import com.xuan.diary.common.message.Message;
import com.xuan.diary.common.response.Response;
import com.xuan.diary.common.response.ResponseCode;
import com.xuan.diary.model.request.ContentDiaryRequest;
import com.xuan.diary.model.request.DiaryRequest;
import com.xuan.diary.model.response.DiaryResponse;
import com.xuan.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/diaries")
@Validated
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping()
    public Response<List<DiaryResponse>> findAll() {
        List<DiaryResponse> diaryResponse = diaryService.findAllByStatus();
        return new Response<>(diaryResponse, ResponseCode.R_200, Message.RESPONSE_OK);
    }

    @GetMapping("/users/{user-id}")
    public Response<List<DiaryResponse>> findByIdUser(@NotNull(message = Message.NOT_NULL) @Min(value = 0, message = Message.MIN_ID) @PathVariable(name = "user-id") Integer userId) {
        List<DiaryResponse> diaryResponses = diaryService.findByIdUser(userId);
        return new Response<>(diaryResponses, ResponseCode.R_200, Message.RESPONSE_OK);
    }

    @GetMapping("/{diary-id}")
    public Response<DiaryResponse> findByIdDiary(@NotNull(message = Message.NOT_NULL) @Min(value = 0, message = Message.MIN_ID) @PathVariable(name = "diary-id") Integer id) {
        DiaryResponse diaryResponse = diaryService.findByIdDiary(id);
        return new Response<>(diaryResponse, ResponseCode.R_200, Message.RESPONSE_OK);
    }

    @PostMapping()
    public Response<DiaryResponse> create(@Valid @RequestBody DiaryRequest diaryRequest) {
        DiaryResponse diaryResponse = diaryService.create(diaryRequest);
        return new Response<>(diaryResponse, ResponseCode.R_201, Message.RESPONSE_CREATE);
    }

    @DeleteMapping("/{id}")
    public Response<DiaryResponse> deleteDiaryById(@NotNull(message = Message.NOT_NULL) @Min(value = 0, message = Message.MIN_ID) @PathVariable(name = "id") Integer id) {
        diaryService.deleteDiaryById(id);
        return new Response<>(ResponseCode.R_200, Message.RESPONSE_OK);
    }

}
