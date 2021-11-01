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

    @GetMapping("/filter")
    public Response<List<DiaryResponse>> filter(
            @RequestParam(name = "theme-ids", required = false) List<Integer> themeIds,
            @RequestParam(name = "time-start", required = false) Timestamp timeStart,
            @RequestParam(name = "time-end", required = false) Timestamp timeEnd,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "favorite-list", required = false) boolean favoritesList) {
        List<DiaryResponse> diaryResponses = diaryService.filter(themeIds, title, favoritesList, timeStart, timeEnd);
        return new Response<>(diaryResponses, ResponseCode.R_200, Message.RESPONSE_OK);
    }

    @PostMapping()
    public Response<DiaryResponse> create(@Valid @RequestBody DiaryRequest diaryRequest) {
        DiaryResponse diaryResponse = diaryService.create(diaryRequest);
        return new Response<>(diaryResponse, ResponseCode.R_201, Message.RESPONSE_CREATE);
    }

    @PutMapping("/{id}")
    public Response<DiaryResponse> update(@NotNull(message = Message.NOT_NULL) @Min(value = 0, message = Message.MIN_ID) @PathVariable(name = "id") Integer id,
                                          @Valid @RequestBody DiaryRequest diaryRequest) {
        DiaryResponse diaryResponse = diaryService.update(diaryRequest, id);
        return new Response<>(diaryResponse, ResponseCode.R_200, Message.RESPONSE_OK);
    }

    @PutMapping("/themes/{id}")
    public Response<DiaryResponse> updateTheme(@NotNull(message = Message.NOT_NULL) @Min(value = 0, message = Message.MIN_ID) @PathVariable(name = "id") Integer id,
                                               @RequestParam(name = "theme-ids", required = false) List<Integer> themeIds) {
        DiaryResponse diaryResponse = diaryService.updateTheme(themeIds, id);
        return new Response<>(diaryResponse, ResponseCode.R_200, Message.RESPONSE_OK);
    }

    @PutMapping("/favorite-list/{id}")
    public Response<DiaryResponse> updateFavoriteList(@NotNull(message = Message.NOT_NULL) @Min(value = 0, message = Message.MIN_ID) @PathVariable(name = "id") Integer id) {
        DiaryResponse diaryResponse = diaryService.updateFavoriteList(id);
        return new Response<>(diaryResponse, ResponseCode.R_200, Message.RESPONSE_OK);
    }

    @PutMapping("/content/{id}")
    public Response<DiaryResponse> updateContent(@NotNull(message = Message.NOT_NULL) @Min(value = 0, message = Message.MIN_ID) @PathVariable(name = "id") Integer id,
                                                 @Valid @RequestBody ContentDiaryRequest contentRequest) {
        DiaryResponse diaryResponse = diaryService.updateContent(contentRequest, id);
        return new Response<>(diaryResponse, ResponseCode.R_200, Message.RESPONSE_OK);
    }

    @DeleteMapping("/{id}")
    public Response<DiaryResponse> deleteDiaryById(@NotNull(message = Message.NOT_NULL) @Min(value = 0, message = Message.MIN_ID) @PathVariable(name = "id") Integer id) {
        diaryService.deleteDiaryById(id);
        return new Response<>(ResponseCode.R_200, Message.RESPONSE_OK);
    }

}
