package com.tlb.spring.mapper;

import com.tlb.spring.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectAllUser();

    @Select("SELECT * FROM T_USER WHERE PHONE = #{phone}")
    User findUserByPhone(@Param("phone") String phone);

    @Insert("INSERT INTO T_USER(USER_NAME, PASSWORD, PHONE) VALUES(#{name}, #{password}, #{phone})")
    int insert2(@Param("name") String name, @Param("password") String password, @Param("phone") String phone);

    @Select("select min(user_id) from t_user")
    int findMinUserId();

    @Select("select max(user_id) from t_user")
    int findMaxUserId();

    List<User> selectPartitionUser(User user);

}