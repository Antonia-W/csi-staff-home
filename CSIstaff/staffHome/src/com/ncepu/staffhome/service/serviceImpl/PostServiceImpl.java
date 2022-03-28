package com.ncepu.staffhome.service.serviceImpl;

import com.ncepu.staffhome.entity.Posts;
import com.ncepu.staffhome.mapper.TpostMapper;
import com.ncepu.staffhome.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.apache.log4j.spi.Configurator.NULL;

@Service("postservice")
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

    @Autowired
    TpostMapper tpostMapper;

    @Override
    public List<Posts> getAllPost() {
        return tpostMapper.getAllPost();
    }

    @Override
    public List<Posts> getSelPost(String poname) {
        return tpostMapper.getSelPost(poname);
    }

    @Override
    public Posts getBackPost(int pid) {
        return tpostMapper.getBackPost(pid);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)  //修改事务管理器
    public int updatePo(Posts posts) {
        return tpostMapper.updatePo(posts);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)  //修改事务管理器
    public int delPo(String ids)  {
        String [] id=ids.split(",");
        int count=0;
        for (String i: id
        ) {
            if(i!=NULL && !i.equals("")){
                int pid=Integer.parseInt(i);
                int no=tpostMapper.delPo(pid);
                if(no!=0){
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)  //修改事务管理器
    public int addPo(Posts posts) {
        return tpostMapper.addPo(posts);
    }


}
