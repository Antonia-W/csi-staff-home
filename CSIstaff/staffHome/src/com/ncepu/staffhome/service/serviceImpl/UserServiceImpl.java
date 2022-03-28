package com.ncepu.staffhome.service.serviceImpl;


import com.ncepu.staffhome.entity.Department;
import com.ncepu.staffhome.entity.Facekey;
import com.ncepu.staffhome.entity.Posts;
import com.ncepu.staffhome.entity.User;
import com.ncepu.staffhome.mapper.TuserMapper;
import com.ncepu.staffhome.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.apache.log4j.spi.Configurator.NULL;

@Service("tsshservice")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    TuserMapper tuserMapper;

    /**
     * 实现登录功能
     * @param uname
     * @param pwd
     * @return
     */
    @Override
    public User login(String uname, String pwd) {
        return tuserMapper.login(uname,pwd);
    }

    /**
     * 显示所有用户信息
     * @return
     */
    @Override
    public List<User> getAllUser() {
        return tuserMapper.getAllUser();
    }

    /**
     * 用户多条件查询
     * @param uname
     * @param state
     * @return
     */
    @Override
    public List<User> getSUser(@Param("uname") String uname, @Param("state") int state) {
        return tuserMapper.getSUser(uname,state);
    }

    /**
     * 回显某个用户的信息
     * @param uname
     * @return
     */
    @Override
    public User getBackUser(String uname) {
        return tuserMapper.getBackUser(uname);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)  //修改事务管理器
    public int updateUser(User user) {
        return tuserMapper.updateUser(user);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)  //修改事务管理器
    public int delUser(String ids) {
        String [] unames  =ids.split(",");
        int count=0;
        for (String uname: unames
        ) {
            if(uname!=NULL && !uname.equals("")){
                int no=tuserMapper.delUser(uname);
                if(no!=0){
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void upDataFaceUrlByName(String uname, String urlPath, String path) {
        System.out.println("login:" + uname + "urlPath:" + urlPath + " path:" + path);
        tuserMapper.upDataFaceUrlByName(uname , urlPath , path) ;
    }

    @Override
    public Facekey selectFaceKye() {
        return tuserMapper.selectFaceKye();
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int addUser(User user) {
        return tuserMapper.addUser(user);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int editPwd(User user) {
        return tuserMapper.editPwd(user);
    }
}
