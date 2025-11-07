package com.example.petprojectspringboot.cache;

import com.example.petprojectspringboot.cache.manager.RedisKeyManager;
import com.example.petprojectspringboot.user.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CacheService {
    public void cachedUserToken(Long userId,String token) {
        if(userId==null) {
            throw new RuntimeException("Not allowed to set user token");
        }

        String key=RedisKeyManager.detailKey(RedisKeyTypes.USER_DETAIL.toString(),userId);


    }
}
