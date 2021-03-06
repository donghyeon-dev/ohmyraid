package com.ohmyraid.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohmyraid.dto.login.LoginOutpDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Slf4j
@Component
public class RedisUtils {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * Redis Session에 값을 넣는다.
     * @param key
     * @param vo
     */
    public void putSession(String key, LoginOutpDto vo) throws JsonProcessingException {
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        String value = objectMapper.writeValueAsString(vo);
        valueOperations.set(key,value);
        redisTemplate.expireAt(key, Date.from(ZonedDateTime.now().plusHours(1).toInstant()));
        log.debug("putSession() && Key is {}, Value is {}", key, value);
    }

    /**
     * Redis Session에서 Key에 해당하는 값을 추출한다.
     * @param key
     * @return
     * @throws JsonProcessingException
     */
    public LoginOutpDto getSession(String key) throws JsonProcessingException {
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        String value = valueOperations.get(key);
        LoginOutpDto outpVo = objectMapper.readValue(value, LoginOutpDto.class);

        log.debug("getSession() Return is {}", outpVo);
        return outpVo;
    }


}
