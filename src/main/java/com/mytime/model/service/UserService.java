package com.mytime.model.service;

import com.mytime.model.dao.UserDao;
import com.mytime.model.dto.UserDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    public UserDTO getUserById(Long userId){
        UserDTO userDto = userDao.selectById(userId);
        return userDto;
    }
}
