package com.ncepu.staffhome.mapper;

import com.ncepu.staffhome.entity.Posts;

import java.util.List;

public interface TpostMapper {

    public List<Posts> getAllPost();

    List<Posts> getSelPost(String poname);

    Posts getBackPost(int pid);

    int updatePo(Posts posts);

    int delPo(int pid);

    int addPo(Posts posts);
}
