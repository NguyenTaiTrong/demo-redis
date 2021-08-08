package com.example.redis.service;

import com.example.redis.model.Class;
import com.example.redis.model.request.AddClassRequest;
import com.example.redis.model.request.UpdateClassRequest;
import com.example.redis.model.response.BaseResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClassService {
    BaseResponse<String> createdClass(AddClassRequest request);
    BaseResponse<Class> getClassById(long id);
    BaseResponse<String> deleteClassById(long id);
    BaseResponse<List<Class>> getAll();
    BaseResponse<Class> updatedClass(UpdateClassRequest request, long id);
}
