package com.example.redis.controller;

import com.example.redis.model.Class;
import com.example.redis.model.request.AddClassRequest;
import com.example.redis.model.request.UpdateClassRequest;
import com.example.redis.model.response.BaseResponse;
import com.example.redis.model.utils.ApiException;
import com.example.redis.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/class")
public class ClassController {
    @Autowired
    private ClassService classService;

    @GetMapping
    public BaseResponse<List<Class>> getAllClass() throws ApiException {
        return classService.getAll();
    }

    @GetMapping("/{id}")
    public BaseResponse<Class> getClass(@PathVariable long id) throws ApiException{
        return classService.getClassById(id);
    }

    @PostMapping()
    public BaseResponse<String> createdClass(@RequestBody AddClassRequest request) throws ApiException {
        return classService.createdClass(request);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<String> deleleClass(@PathVariable long id) throws ApiException{
        return classService.deleteClassById(id);
    }

    @PutMapping("/{id}")
    public BaseResponse<Class> updatedClass(@PathVariable long id, @RequestBody UpdateClassRequest request) throws ApiException{
        return classService.updatedClass(request,id);
    }

}