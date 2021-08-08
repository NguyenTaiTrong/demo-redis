package com.example.redis.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

public class MessagePublisherImpl implements MessagePublisher {
    @Autowired
    RedisTemplate<Long, Object> redisTemplate;

    @Autowired
    ChannelTopic channelTopic;
    @Override
    public void publish(String message) {
        redisTemplate.convertAndSend(channelTopic.getTopic(), message);
    }
    public MessagePublisherImpl( RedisTemplate<Long, Object> redisTemplate,  ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.channelTopic = topic;
    }
}
