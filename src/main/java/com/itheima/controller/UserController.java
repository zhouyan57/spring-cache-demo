package com.itheima.controller;

import com.itheima.entity.User;
import com.itheima.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping
    // spEl spring Expression Language
    @CachePut(cacheNames = "userCache",key = "#user.id") // 如果使用Spring Cache缓存数据，key的生成：userCache::2
    //@CachePut(cacheNames = "userCache",key = "#result.id") // . 对象导航
    //@CachePut(cacheNames = "userCache",key = "#p0.id") // p0 第一个参数
    //@CachePut(cacheNames = "userCache",key = "#a0.id") // a0 第一个参数
    //@CachePut(cacheNames = "userCache",key = "#root.args[0].id")

    public User save(@RequestBody User user){
        userMapper.insert(user);
        return user;
    }

    @DeleteMapping
    public void deleteById(Long id){
        userMapper.deleteById(id);
    }

	@DeleteMapping("/delAll")
    public void deleteAll(){
        userMapper.deleteAll();
    }

    @GetMapping
    public User getById(Long id){
        User user = userMapper.getById(id);
        return user;
    }

}
