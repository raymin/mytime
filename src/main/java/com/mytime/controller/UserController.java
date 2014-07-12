package com.mytime.controller;

import com.mytime.model.dto.UserDTO;
import com.mytime.model.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping(value="/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value="/showUser.do")
    public ModelAndView handleRequest(@Param(value = "id") Long id) throws Exception {
        UserDTO userDto = userService.getUserById(id);
        return new ModelAndView("user/showUser","user", userDto);
    }

}

