package com.ncepu.staffhome.mapper;

import com.ncepu.staffhome.entity.Department;

import java.util.List;

public interface TdeptMapper {

    public List<Department> getAllDep() ;

    List<Department> getSelDep(String dename);

    Department getBackDep(int did);

    int updateDep(Department department);

    int delDep(int did);

    int addDep(Department department);
}
