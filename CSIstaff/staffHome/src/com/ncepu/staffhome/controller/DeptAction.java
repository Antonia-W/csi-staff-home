package com.ncepu.staffhome.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ncepu.staffhome.entity.Department;
import com.ncepu.staffhome.entity.User;
import com.ncepu.staffhome.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class DeptAction {

    @Autowired
    DeptService deptservice;

    /**
     * 显示所有的部门信息
     * @param model
     * @return
     */
    @RequestMapping("/getAllDep")
    public String getAllDep(Model model, @RequestParam(value = "p",defaultValue ="1" )int page){
        int itemsNum=deptservice.getAllDep().size();
        PageHelper.startPage(page,5);
        //PageInfo一定要在获取到数据库
        List<Department> depts=deptservice.getAllDep();
        PageInfo<Department> pi=new PageInfo<>(depts);
        //上一页
        int up=page-1;
        int next=page+1;
        long total=pi.getPages();
        model.addAttribute("itemsNum",itemsNum);
        model.addAttribute("up",up);
        model.addAttribute("p",page);
        model.addAttribute("next",next);
        model.addAttribute("total",total);
        //System.out.println("depts.toString() = " + depts.toString());
        model.addAttribute("depts",depts);
        return "dept/dept.jsp";
    }

    /**
     * 多条件查询部门信息
     * @param model
     * @param dename
     * @return
     */
    @RequestMapping("/getSelDep")
    public String getSelDep(Model model,String dename, @RequestParam(value = "p",defaultValue ="1" )int page){
        int itemsNum=deptservice.getSelDep(dename).size();
        PageHelper.startPage(page,5);
        List<Department> depts=deptservice.getSelDep(dename);
        PageInfo<Department> pi=new PageInfo<>(depts);
        //上一页
        int up=page-1;
        int next=page+1;
        long total=pi.getPages();
        model.addAttribute("itemsNum",itemsNum);
        model.addAttribute("up",up);
        model.addAttribute("p",page);
        model.addAttribute("next",next);
        model.addAttribute("total",total);
        model.addAttribute("depts",depts);
        return "dept/dept.jsp";
    }

    /**
     * 修改回显
     * @param model
     * @param did
     * @return
     */
    @RequestMapping("/getBackDep")
    public String getBackDep(Model model,int did){
        Department dept=deptservice.getBackDep(did);
        //System.out.println("dept.toString() = " + dept.toString());
        model.addAttribute("dept",dept);
        return "dept/showUpdateDept.jsp";
    }

    /**
     * 修改部门信息
     * @param department
     * @return
     */
    @RequestMapping(value="/updateDep",method = RequestMethod.POST)
    public String updateDep(Department department){
        int no=deptservice.updateDep(department);
        System.out.println("成功修改"+no+"条数据");
        return "forward:getAllDep.action";
    }

    /**
     * 删除部门
     * @param ids
     * @return
     */
    @RequestMapping("/delDep")
    public String delDep(String ids)throws IOException {
        int no=deptservice.delDep(ids);
        System.out.println("成功修改"+no+"条数据");
        return "forward:getAllDep.action";
    }
    /**
     * 添加部门
     * @param department
     * @return
     */
    @RequestMapping(value = "/addDep",method = RequestMethod.POST)
    public String addDep(Department department){
        int no=deptservice.addDep(department);
        System.out.println("成功修改"+no+"条数据");
        return "forward:getAllDep.action";
    }

}
