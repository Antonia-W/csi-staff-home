package com.ncepu.staffhome.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ncepu.staffhome.entity.Notice;
import com.ncepu.staffhome.entity.Posts;
import com.ncepu.staffhome.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class PostAction {

    @Autowired
    PostService postservice;

    /**
     * 获取所有职位信息
     *
     * @param model
     * @return
     */
    @RequestMapping("/getAllPost")
    public String getAllPost(Model model, @RequestParam(value = "p",defaultValue ="1" )int page) {
        int itemsNum= postservice.getAllPost().size();
        PageHelper.startPage(page,5);
        List<Posts> posts = postservice.getAllPost();
        PageInfo<Posts> pi=new PageInfo<>(posts);
        //上一页
        int up=page-1;
        int next=page+1;
        long total=pi.getPages();
        model.addAttribute("itemsNum",itemsNum);
        model.addAttribute("up",up);
        model.addAttribute("p",page);
        model.addAttribute("next",next);
        model.addAttribute("total",total);
        model.addAttribute("posts", posts);
        return "job/job.jsp";
    }

    /**
     * 多条件查询职位信息
     *
     * @param model
     * @param poname
     * @return
     */
    @RequestMapping("/getSelPost")
    public String getAllPost(Model model, String poname, @RequestParam(value = "p",defaultValue ="1" )int page) {
        int itemsNum=postservice.getSelPost(poname).size();
        PageHelper.startPage(page,5);
        List<Posts> posts = postservice.getSelPost(poname);
        PageInfo<Posts> pi=new PageInfo<>(posts);
        //上一页
        int up=page-1;
        int next=page+1;
        long total=pi.getPages();
        model.addAttribute("itemsNum",itemsNum);
        model.addAttribute("up",up);
        model.addAttribute("p",page);
        model.addAttribute("next",next);
        model.addAttribute("total",total);
        model.addAttribute("posts", posts);
        return "job/job.jsp";
    }

    /**
     * 修改回显职位信息
     *
     * @param model
     * @param pid
     * @return
     */
    @RequestMapping("/getBackPost")
    public String getBackPost(Model model, int pid) {
        Posts posts = postservice.getBackPost(pid);
        System.out.println("posts.toString() = " + posts.toString());
        model.addAttribute("posts", posts);
        return "job/showUpdateJob.jsp";
    }

    /**
     * 修改职位信息
     *
     * @param posts
     * @return
     */
    @RequestMapping(value = "/updatePo", method = RequestMethod.POST)
    public String updatePo(Posts posts) {
        postservice.updatePo(posts);
        return "forward:getAllPost.action";
    }

    /**
     * 删除职位
     * @param ids
     * @return
     */
    @RequestMapping("/delPo")
    public String delPo(String ids) throws IOException {
        int count = postservice.delPo(ids);
        System.out.println("共删除了" + count + "条数据");
        return "forward:getAllPost.action";
    }

    /**
     * 增加职位
     * @param posts
     * @return
     */
    @RequestMapping(value="/addPo",method = RequestMethod.POST)
    public String addPo(Posts posts) {
        int count = postservice.addPo(posts);
        System.out.println("成功增加了" + count + "条数据");
        return "forward:getAllPost.action";
    }

}
