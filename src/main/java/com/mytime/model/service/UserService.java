package com.mytime.model.service;

import com.mytime.model.dao.LoginLogDao;
import com.mytime.model.dao.UserDao;
import com.mytime.model.dto.LoginLogDTO;
import com.mytime.model.dto.UserDTO;
import com.mytime.model.dto.enums.LoginType;
import com.mytime.model.dto.enums.UserEnum;
import com.mytime.utils.CipherUtil;
import com.mytime.utils.Constants;
import com.mytime.utils.MapUtil;
import com.mytime.utils.MyString;
import com.mytime.view.vo.UserVO;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private LoginLogDao loginlogDao;

    /**
     * 用户注册处理
     *
     * @param userDto
     * @return
     */
    public UserVO addUser(UserDTO userDto) {

        //输入check
        //用户名/手机/邮箱不能全为空
        if (MyString.isBlank(userDto.getName()) && MyString.isBlank(userDto.getEmail()) && MyString.isBlank(userDto.getMobile())) {
            UserVO userVo = new UserVO();
            userVo.setRetCode(UserVO.RET_CODE_CHECK_ERROR);
            userVo.setRetErrorMap(MapUtil.buildMap("name_email_mobile", "用户名/手机/邮箱不能全为空"));
            return userVo;
        }

        //用户名规则check
        if (MyString.isNotBlank(userDto.getName()) && !MyString.isMatch(userDto.getName(), Constants.REGEX_USER_NAME_FORMAT)) {
            UserVO userVo = new UserVO();
            userVo.setRetCode(UserVO.RET_CODE_CHECK_ERROR);
            userVo.setRetErrorMap(MapUtil.buildMap("name", "用户名不符合规则"));
            return userVo;
        }

        //手机规则check
        if (MyString.isNotBlank(userDto.getMobile()) && !MyString.isMatch(userDto.getMobile(), Constants.REGEX_USER_MOBILE_FORMAT)) {
            UserVO userVo = new UserVO();
            userVo.setRetCode(UserVO.RET_CODE_CHECK_ERROR);
            userVo.setRetErrorMap(MapUtil.buildMap("mobile", "手机号码不符合规则"));
            return userVo;
        }

        //邮箱规则check
        if (MyString.isNotBlank(userDto.getEmail()) && !MyString.isMatch(userDto.getEmail(), Constants.REGEX_USER_EMAIL_FORMAT)) {
            UserVO userVo = new UserVO();
            userVo.setRetCode(UserVO.RET_CODE_CHECK_ERROR);
            userVo.setRetErrorMap(MapUtil.buildMap("mobile", "邮箱不符合规则"));
            return userVo;
        }

        //密码规则check
        if (MyString.isBlank(userDto.getPwd()) || !MyString.isMatch(userDto.getPwd(), Constants.REGEX_USER_PWD_FORMAT)) {
            UserVO userVo = new UserVO();
            userVo.setRetCode(UserVO.RET_CODE_CHECK_ERROR);
            userVo.setRetErrorMap(MapUtil.buildMap("pwd", "密码不符合规则"));
            return userVo;
        }

        if (userDto.getName() != null) {
            userDto.setNameDisp(userDto.getName());
        }

        if (userDto.getEmail() != null) {
            userDto.setEmailDisp(userDto.getEmail());
        }

        //加密密码
        userDto.setPwd(CipherUtil.generatePassword(userDto.getPwd()));
        userDto.setCreatedAt(new Date());
        userDto.setCreatedBy("app");

        try{
            userDao.insert(userDto);
        } catch (DuplicateKeyException e){
            e.printStackTrace();
            UserVO userVo = new UserVO();
            userVo.setRetCode(UserVO.RET_CODE_LOGIC_ERROR);
            userVo.setRetErrorMap(MapUtil.buildMap("logic", "唯一约束字段重复"));
            return userVo;
        } catch (Exception e){
            e.printStackTrace();
            UserVO userVo = new UserVO();
            userVo.setRetCode(UserVO.RET_CODE_SYS_ERROR);
            userVo.setRetErrorMap(MapUtil.buildMap("system", "系统异常"));
            return userVo;
        }

        UserVO userVo = new UserVO(userDto);
        userVo.setRetCode(UserVO.RET_CODE_SUCCESS);
        userVo.setRetMsg("注册成功");

        //注册成功后异步处理事件可以加在这里

        return userVo;
    }

    /**
     * 用户登录处理
     *
     * @param account
     * @param pwd
     * @param validateCode
     * @param customerIp
     * @return
     */
    public UserVO login(String account, String pwd, String validateCode, String customerIp) {

        //check输入内容
        //登录名/密码/验证码不能为空
        if (MyString.isBlank(account)) {
            UserVO userVo = new UserVO();
            userVo.setRetCode(UserVO.RET_CODE_CHECK_ERROR);
            userVo.setRetErrorMap(MapUtil.buildMap("account", "登录名不能为空"));
            return userVo;
        }

        if (MyString.isBlank(pwd)) {
            UserVO userVo = new UserVO();
            userVo.setRetCode(UserVO.RET_CODE_CHECK_ERROR);
            userVo.setRetErrorMap(MapUtil.buildMap("pwd", "密码不能为空"));
            return userVo;
        }

        if (MyString.isBlank(validateCode)) {
            UserVO userVo = new UserVO();
            userVo.setRetCode(UserVO.RET_CODE_CHECK_ERROR);
            userVo.setRetErrorMap(MapUtil.buildMap("validateCode", "验证码不能为空"));
            return userVo;
        }

        //校验验证码
        //TODO

        //根据account判断是哪种类型的登录
        UserDTO cndDto = new UserDTO();
        LoginLogDTO loginLogDto = new LoginLogDTO();
        if (MyString.isMatch(account, Constants.REGEX_USER_MOBILE_FORMAT)) {
            cndDto.setMobile(account);
            loginLogDto.setLoginType(LoginType.MOBILE.name());
        } else if (MyString.isMatch(account, Constants.REGEX_USER_EMAIL_FORMAT)) {
            cndDto.setEmail(account.toLowerCase());
            loginLogDto.setLoginType(LoginType.EMAIL.name());
        } else {
            cndDto.setName(account.toLowerCase());
            loginLogDto.setLoginType(LoginType.NAME.name());
        }

        UserDTO userDto = userDao.selectByUniqueKey(cndDto);
        if (userDto == null) {
            UserVO userVo = new UserVO();
            userVo.setRetCode(UserVO.RET_CODE_LOGIC_ERROR);
            userVo.setRetErrorMap(MapUtil.buildMap("login", "登录用户名不存在，请核对！"));
            return userVo;

        } else if (!CipherUtil.validatePassword(userDto.getPwd(), pwd)) {
            UserVO userVo = new UserVO();
            userVo.setRetCode(UserVO.RET_CODE_LOGIC_ERROR);
            userVo.setRetErrorMap(MapUtil.buildMap("login", "密码错误，请核对！"));
            return userVo;

        } else if (UserEnum.UserStatus.INVALID.name().equals(userDto.getStatus())) {
            UserVO userVo = new UserVO();
            userVo.setRetCode(UserVO.RET_CODE_LOGIC_ERROR);
            userVo.setRetErrorMap(MapUtil.buildMap("login", "此用户已注销，请核对！"));
            return userVo;

        } else {
            //登录成功，更新登录日志
            loginLogDto.setLoginAccount(account);
            loginLogDto.setLoginPwd(CipherUtil.generatePassword(pwd));
            loginLogDto.setCustomerIp(customerIp);
            loginLogDto.setLoginTime(new Date());
            loginLogDto.setCreatedAt(new Date());
            loginLogDto.setCreatedBy("app");

            //后续需要异步，可以在此加上其它异步事件
            loginlogDao.insert(loginLogDto);
        }

        UserVO userVo = new UserVO(userDto);
        userVo.setRetCode(UserVO.RET_CODE_SUCCESS);
        userVo.setRetMsg("登录成功");

        return userVo;
    }

    /**
     * 根据id更新用户
     *
     * @param userDto
     * @return
     */
    public int updateUserById(UserDTO userDto) {
        return userDao.update(userDto);
    }

    /**
     * 按id查找用户
     *
     * @param id
     * @return
     */
    public UserDTO getUserById(Long id) {
        return userDao.selectById(id);
    }

}
