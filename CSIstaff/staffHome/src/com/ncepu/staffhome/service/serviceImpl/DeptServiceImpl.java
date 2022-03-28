package com.ncepu.staffhome.service.serviceImpl;

import com.ncepu.staffhome.entity.Department;
import com.ncepu.staffhome.mapper.TdeptMapper;
import com.ncepu.staffhome.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.apache.log4j.spi.Configurator.NULL;

@Service("deptservice")
@Transactional(readOnly = true)
public class DeptServiceImpl implements DeptService {

    @Autowired
    TdeptMapper tdeptMapper;

    @Override
    public List<Department> getAllDep() {
        return tdeptMapper.getAllDep();
    }

    @Override
    public List<Department> getSelDep(String dename) {
        return tdeptMapper.getSelDep(dename);
    }

    @Override
    public Department getBackDep(int did) {
        return tdeptMapper.getBackDep(did);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)  //修改事务管理器
    public int updateDep(Department department) {
        return tdeptMapper.updateDep(department);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)  //修改事务管理器
    public int delDep(String ids) {
        String [] id=ids.split(",");
        int count=0;
        for (String i: id
        ) {
            if(i!=NULL && !i.equals("")){
                int did=Integer.parseInt(i);
                int no=tdeptMapper.delDep(did);
                if(no!=0){
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)  //修改事务管理器
    public int addDep(Department department) {
        return tdeptMapper.addDep(department);
    }
}
