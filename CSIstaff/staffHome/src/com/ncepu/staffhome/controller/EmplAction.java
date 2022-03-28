package com.ncepu.staffhome.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ncepu.staffhome.entity.Department;
import com.ncepu.staffhome.entity.Posts;
import com.ncepu.staffhome.entity.User;
import com.ncepu.staffhome.service.EmplService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class EmplAction {

    @Autowired
    EmplService emplservice;

    /**
     * 显示所有的员工信息
     *
     * @param model
     * @return
     */
    @RequestMapping("/getAllStaff")
    public String getAllStaff(Model model, @RequestParam(value = "p", defaultValue = "1") int page) {
        int itemsNum = emplservice.getAllStaff().size();
        //PageHelper一定要在获取数据库集合之前
        PageHelper.startPage(page, 5);
        List<User> staff = emplservice.getAllStaff();
        System.out.println("staff.toString() = " + staff.toString());
        List<Posts> allPo = emplservice.getPoName();
        List<Department> allDe = emplservice.getDeName();
        PageInfo<User> pi = new PageInfo<>(staff);
        //上一页
        int up = page - 1;
        int next = page + 1;
        long total = pi.getPages();
        model.addAttribute("itemsNum", itemsNum);
        model.addAttribute("up", up);
        model.addAttribute("p", page);
        model.addAttribute("next", next);
        model.addAttribute("total", total);
        model.addAttribute("staffs", staff);
        model.addAttribute("allPo", allPo);
        model.addAttribute("allDe", allDe);
        return "employee/employee.jsp";
    }

    /**
     * 修改回显员工信息
     *
     * @param uname
     * @param model
     * @return
     */
    @RequestMapping("/getBackStaff")
    public String getAllStaff(String uname, Model model) {
        User staff = emplservice.getBackStaff(uname);
        System.out.println("staff.getBirthday() = " + staff.getBirthday());
        model.addAttribute("staff", staff);
        List<Posts> allPo = emplservice.getPoName();
        List<Department> allDe = emplservice.getDeName();
        model.addAttribute("allPo", allPo);
        model.addAttribute("allDe", allDe);
        return "employee/showUpdateEmployee.jsp";
    }

    /**
     * 多条件查询员工信息
     *
     * @param pid
     * @param name
     * @param idnum
     * @param sex
     * @param mobie
     * @param did
     * @param model
     * @return
     */
    @RequestMapping("/getSelStaff")
    public String getAllStaff(@Param("pid") int pid, @Param("name") String name, @Param("idnum") String idnum, @Param("sex") String sex, @Param("tel") String mobie, @Param("did") int did, Model model, @RequestParam(value = "p", defaultValue = "1") int page) {
        int itemsNum = emplservice.getSelStaff(pid, name, idnum, sex, mobie, did).size();
        //PageHelper一定要在获取数据库集合之前
        PageHelper.startPage(page, 5);
        List<User> staffs = emplservice.getSelStaff(pid, name, idnum, sex, mobie, did);
        List<Posts> allPo = emplservice.getPoName();
        List<Department> allDe = emplservice.getDeName();
        PageInfo<User> pi = new PageInfo<>(staffs);
        //上一页
        int up = page - 1;
        int next = page + 1;
        long total = pi.getPages();
        model.addAttribute("itemsNum", itemsNum);
        model.addAttribute("up", up);
        model.addAttribute("p", page);
        model.addAttribute("next", next);
        model.addAttribute("total", total);
        model.addAttribute("allPo", allPo);
        model.addAttribute("allDe", allDe);
        model.addAttribute("staffs", staffs);
        return "employee/employee.jsp";
    }

    /**
     * 修改员工信息
     * @param user
     * @return
     */
    @RequestMapping(value="/updateStaff",method = RequestMethod.POST)
    public String updateStaff(User user){
        int no=emplservice.updateStaff(user);
        System.out.println("修改数据条数：" + no);
        return "forward:getAllStaff.action";
    }


    /**
     * 删除员工
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delStaff")
    public String delStaff(String ids)throws IOException {
        int count = emplservice.delStaff(ids);
        System.out.println("共删除了" + count + "条数据");
        return "forward:getAllStaff.action";
    }

    /**
     * 增加员工
     *
     * @param user
     * @return
     */
    @RequestMapping(value="/addStaff",method = RequestMethod.POST)
    public String addStaff(User user,Model model)throws IOException {
        int count = emplservice.addStaff(user);
        System.out.println("成功增加了" + count + "条数据");
        List<Posts> allPo = emplservice.getPoName();
        List<Department> allDe = emplservice.getDeName();
        model.addAttribute("allPo",allPo);
        model.addAttribute("allDe",allDe);
        return "forward:getAllStaff.action";
    }


    @RequestMapping("/getDP")
    public String getDP(Model model){
        List<Posts> allPo = emplservice.getPoName();
        List<Department> allDe = emplservice.getDeName();
        model.addAttribute("allPo",allPo);
        model.addAttribute("allDe",allDe);
        return "employee/showAddEmployee.jsp";
    }

}
