package com.example.redis.service;

import com.example.redis.model.Class;
import com.example.redis.model.request.AddClassRequest;
import com.example.redis.model.request.UpdateClassRequest;
import com.example.redis.model.response.BaseResponse;
import com.example.redis.queue.MessagePublisher;
import com.example.redis.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService{

    @Autowired
    ClassRepository classRepository;

    @Autowired
    MessagePublisher messagePublisher;
//
//    @Autowired
//    RedisTemplate<Long, Object> redisTemplate;

    public static long id =0;

    @Override
    public BaseResponse<String> createdClass(AddClassRequest request) {
        BaseResponse<Class> response = new BaseResponse<>();

        Class aClass = new Class();
        aClass.setName(request.getName());
        aClass.setId(this.id + 1);
        classRepository.save(aClass);

        response.setData(aClass);
        this.id += 1;
        messagePublisher.publish("You created class:"+  request.getName());

        return new BaseResponse<>(201,"Created class successfully");
    }

    @Override
    public BaseResponse<Class> getClassById(long id) {
        Class aClass = classRepository.findById(id).get();
        if (aClass == null)
        {
            return new BaseResponse<>(409,"Class name is exist");
        }

        BaseResponse<Class> response = new BaseResponse<>();
        messagePublisher.publish("Get class detail by Id :"+  id);
        if (aClass != null)
        {
            response.setData(aClass);
        }
        return response;
    }

    @Override
    public BaseResponse<String> deleteClassById(long id) {
        Class aClass = classRepository.findById(id).get();
        if (aClass == null)
        {
            return new BaseResponse<>(404,"Class not found");
        }
        classRepository.deleteById(id);
        BaseResponse<String> response = new BaseResponse<>();
        return response;
    }

    @Override
    public BaseResponse<List<Class>> getAll() {
        Iterable<Class> aClass = classRepository.findAll();
        BaseResponse<List<Class>> response = new BaseResponse<>();
        response.setData((List<Class>) aClass);
        return response;
    }

    @Override
    public BaseResponse<Class> updatedClass(UpdateClassRequest request, long id) {
        BaseResponse<Class> response = new BaseResponse<>();
        Class aClass = classRepository.findById(id).get();
        if (aClass == null)
        {
            return new BaseResponse<>(404,"Class not found");
        }

        aClass.setName(request.getNameUpdate());
        classRepository.save(aClass);
        response.setData(aClass);
        return response;
    }
}
