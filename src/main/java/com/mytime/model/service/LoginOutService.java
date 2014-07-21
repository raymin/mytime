package com.mytime.model.service;

import com.mytime.view.utils.CookieWrapper;
import com.mytime.view.vo.UserVO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginOutService {

    /**
     * 登录产生令牌
     *
     * @param userVo
     * @return
     */
    public String createTicket(UserVO userVo) {

        String ticket = "";

        return ticket;
    }

    /**
     * 创建登录后cookie
     *
     * @param request
     * @param response
     * @param userVo
     * @param ticketId
     * @param domain
     */
    public void buildCookie(HttpServletRequest request, HttpServletResponse response,
                            UserVO userVo, String ticketId, String domain) {

        try {
            CookieWrapper cookieWrapper = new CookieWrapper(request, response);
            cookieWrapper.setCookie("ut", ticketId, domain, 100);
            cookieWrapper.setCookie("un", userVo.getNameDisp(), domain, 100);
        } catch (Exception e) {
            //
        }
    }

}
