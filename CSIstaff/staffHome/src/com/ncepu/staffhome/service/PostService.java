package com.ncepu.staffhome.service;

import com.ncepu.staffhome.entity.Posts;

import java.util.List;

public interface PostService {

    public List<Posts> getAllPost();

    public List<Posts> getSelPost(String poname);

    public Posts getBackPost(int pid);

    public int updatePo(Posts posts);

    public int delPo(String ids);

    public int addPo(Posts posts);
}