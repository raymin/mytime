package com.mytime.model.dao;


import com.mytime.model.dto.UserDTO;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends BaseDao {

    public UserDTO selectById(Long userId){
        return (UserDTO)queryOne("User.selectById", userId);
    }

}
