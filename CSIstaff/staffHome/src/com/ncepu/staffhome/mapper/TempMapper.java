package com.ncepu.staffhome.mapper;

import com.ncepu.staffhome.entity.Department;
import com.ncepu.staffhome.entity.Posts;
import com.ncepu.staffhome.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TempMapper {
    List<User> getAllStaff();

    User getBackStaff(String uname);

    List<User> getSelStaff(@Param("pid") int pid, @Param("name") String name, @Param("idnum") String idnum, @Param("sex") String sex, @Param("mobie") String mobie, @Param("did") int did);

    List<Posts> getPoName();

    List<Department> getDeName();

    int delStaff(String uname);

    int updateStaff(User user);

    int addStaff(User user);
}
