package com.ncepu.staffhome.service;

import com.ncepu.staffhome.entity.Department;
import com.ncepu.staffhome.entity.Posts;
import com.ncepu.staffhome.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmplService {

    public List<User> getAllStaff();

    public User getBackStaff(String uname);

    public List<User> getSelStaff(@Param("pid") int pid, @Param("name") String name, @Param("idnum") String idnum, @Param("sex") String sex, @Param("mobie") String mobie, @Param("did") int did);

    public List<Posts> getPoName();

    public List<Department> getDeName();

    int delStaff(String ids);

    int updateStaff(User user);

    int addStaff(User user);
}
