package com.ncepu.staffhome.service.serviceImpl;

import com.ncepu.staffhome.entity.Department;
import com.ncepu.staffhome.entity.Posts;
import com.ncepu.staffhome.entity.User;
import com.ncepu.staffhome.mapper.TempMapper;
import com.ncepu.staffhome.service.EmplService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.apache.log4j.spi.Configurator.NULL;

@Service("emplservice")
@Transactional(readOnly = true)
public class EmplServiceImpl implements EmplService {

    @Autowired
    TempMapper tempMapper;

    @Override
    public List<User> getAllStaff() {
        return tempMapper.getAllStaff();
    }

    @Override
    public User getBackStaff(String uname) {
        return tempMapper.getBackStaff(uname);
    }

    @Override
    public List<User> getSelStaff(@Param("pid") int pid, @Param("name") String name, @Param("idnum") String idnum, @Param("sex") String sex, @Param("mobie") String mobie, @Param("did") int did)
    {
        return tempMapper.getSelStaff(pid,name,idnum,sex,mobie,did);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)  //修改事务管理器
    public int delStaff(String ids) {
        String [] unames  =ids.split(",");
        int count=0;
        for (String uname: unames
        ) {
            if(uname!=NULL && !uname.equals("")){
                int no=tempMapper.delStaff(uname);
                if(no!=0){
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)  //修改事务管理器
    public int updateStaff(User user) {
        return tempMapper.updateStaff(user);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)  //修改事务管理器
    public int addStaff(User user) {
        return tempMapper.addStaff(user);
    }


    @Override
    public List<Posts> getPoName() {
        return tempMapper.getPoName();
    }

    @Override
    public List<Department> getDeName() {
        return tempMapper.getDeName();
    }

}
