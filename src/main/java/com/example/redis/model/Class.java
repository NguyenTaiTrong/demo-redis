package com.example.redis.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Class {
    @Id
    private Long id;
    private String name;

}
