package com.mytime.controller;

import com.mytime.model.service.LoginOutService;
import com.mytime.model.service.UserService;
import com.mytime.utils.IPUtil;
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
    private UserService userService;

    @Resource
    private LoginOutService loginOutService;

    @RequestMapping(value = "/tologin.do", method = RequestMethod.GET)
    public ModelAndView toLogin() throws Exception {
        return new ModelAndView("user/login");
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(value = "account") String account,
                              @RequestParam(value = "pwd") String pwd,
                              @RequestParam(value = "validateCode") String validateCode,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        String customerIp = IPUtil.getRemoteIp(request);

        UserVO userVo = userService.login(account, pwd, validateCode, customerIp);
        if(!UserVO.RET_CODE_SUCCESS.equals(userVo.getRetCode())){
            //登录失败
            return new ModelAndView("user/login", "user", userVo);
        } else {
            //登录成功，产生ticket/csessionid,并放入cookie
            // TODO
            String ticketId = loginOutService.createTicket(userVo);
            loginOutService.buildCookie(request, response, userVo, ticketId, "");
            return new ModelAndView("user/loginSuccess", "user", userVo);
        }
    }

    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    public void logout() throws Exception {

    }

}

