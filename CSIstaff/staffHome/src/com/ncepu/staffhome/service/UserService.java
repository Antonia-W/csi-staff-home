package com.ncepu.staffhome.service;

import com.ncepu.staffhome.entity.Department;
import com.ncepu.staffhome.entity.Facekey;
import com.ncepu.staffhome.entity.Posts;
import com.ncepu.staffhome.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserService {

    //实现登录功能
    public User login(String uname, String pwd);

    //显示所有用户
    public List<User> getAllUser();

    //选择查询用户
    public List<User> getSUser(@Param("uname") String uname, @Param("state") int state);

    //回显某个用户信息
    public User getBackUser(String uname);

    public int updateUser(User user);

    public int delUser(String ids);

    void upDataFaceUrlByName(String loginnamme, String urlPath, String path);

    Facekey selectFaceKye();

    public int addUser(User user);

    public int editPwd(User user);

}
