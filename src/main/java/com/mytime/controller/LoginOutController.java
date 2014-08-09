package com.mytime.controller;

import com.mytime.model.service.LoginOutService;
import com.mytime.model.service.UserService;
import com.mytime.utils.MapUtil;
import com.mytime.view.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginOutController {

    @Resource
    private LoginOutService loginOutService;

    @RequestMapping(value = "/toLogin.do", method = RequestMethod.GET)
    public ModelAndView toLogin() throws Exception {
        return new ModelAndView("user/login");
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(value = "account") String account,
                              @RequestParam(value = "pwd") String pwd,
                              @RequestParam(value = "validateCode") String validateCode,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {

        UserVO userVo = loginOutService.login(account, pwd, validateCode, request);
        if(!UserVO.RET_CODE_SUCCESS.equals(userVo.getRetCode())){
            //登录失败
            request.getSession().removeAttribute("LoginUser");
            return new ModelAndView("user/login", "user", userVo);
        } else {
            //登录成功后保存session，并产生cookie
            boolean isCreated = loginOutService.createSessionAndCookie(request, response, userVo);
            if(isCreated){
                return new ModelAndView("user/loginSuccess", "user", userVo);
            } else {
                userVo.setRetCode(UserVO.RET_CODE_LOGIC_ERROR);
                userVo.setRetErrorMap(MapUtil.buildMap("login", "用户登录失败"));
                return new ModelAndView("user/login", "user", userVo);
            }
        }
    }

    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request,
                       HttpServletResponse response) throws Exception {
        request.getSession().removeAttribute("LoginUser");
        return new ModelAndView("user/login");
    }

}

