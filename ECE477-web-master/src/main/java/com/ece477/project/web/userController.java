package com.ece477.project.web;
import java.util.List;

import com.ece477.project.ProjectApplication;
import com.ece477.project.TCP_server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


import com.ece477.project.entity.userEntity;
import com.ece477.project.mapper.userMapper;
import java.util.UUID;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import redis.clients.jedis.Jedis;
import org.junit.Test;

@Controller
@CrossOrigin(origins = {"http://localhost:8080", "null"})
public class userController {
    @Autowired
    private userMapper UserMapper;//might can ignore

    @Test
    @ResponseBody
    @RequestMapping(value="/test")
    public List<userEntity> test(){

//        System.out.println("test request: "+ test.request);
        return UserMapper.getTest();
    }

}
