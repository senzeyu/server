package com.ece477.project.mapper;
import org.apache.ibatis.annotations.*;
import com.ece477.project.entity.userEntity;
import java.util.List;

public interface
userMapper {
    @Select("select * from login.login where binary LastName='Zhang' ;")
    @Results({
            @Result(property = "LastName", column = "LastName"),
            @Result(property = "FirstName", column = "FirstName"),
            @Result(property = "Email", column = "Email"),

    })
    List<userEntity> getTest();


}
