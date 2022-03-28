package com.ncepu.staffhome.mapper;

import com.ncepu.staffhome.entity.Department;
import com.ncepu.staffhome.entity.Facekey;
import com.ncepu.staffhome.entity.Posts;
import com.ncepu.staffhome.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TuserMapper {

    //实现登录功能
    public User login(String uname,String pwd);

    //显示所有用户信息
    public List<User> getAllUser();

    //多条件查询用户信息
    public List<User> getSUser(@Param("uname") String uname, @Param("state") int state);

    //回显用户信息
    public User getBackUser(String uname);

    int updateUser(User user);

    int delUser(String uname);

    void upDataFaceUrlByName(String uname, String urlPath, String path);

    List<User> selectUser();

    Facekey selectFaceKye();

    int addUser(User user);

    int editPwd(User user);
}
