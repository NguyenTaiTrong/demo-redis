package com.example.redis.repository;

import com.example.redis.model.Class;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClassRepository extends CrudRepository<Class, Long> {

}
