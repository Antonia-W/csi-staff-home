package com.ncepu.staffhome.service;

import com.ncepu.staffhome.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptService {
     public List<Department> getAllDep();

     public List<Department> getSelDep(String dename);

     public Department getBackDep(int did);

     public int updateDep(Department department);

     public int delDep(String ids);

     public int addDep(Department department);
}
