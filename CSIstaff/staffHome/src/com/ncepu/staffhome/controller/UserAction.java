package com.ncepu.staffhome.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ncepu.staffhome.entity.User;
import com.ncepu.staffhome.service.UserService;
import com.ncepu.staffhome.util.HrmConstants;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class UserAction extends BasicController{

    @Autowired
    UserService tsshservice;


    /**
     * 显示所有用户信息
     *
     * @param model
     * @return
     */
    @RequestMapping("/getAllUser")
    public String getAllUser(Model model, @RequestParam(value = "p", defaultValue = "1") int page) {
        int itemsNum = tsshservice.getAllUser().size();
        //PageHelper一定要在获取数据库集合之前
        PageHelper.startPage(page, 5);
        List<User> users = tsshservice.getAllUser();
        //PageInfo一定要在获取到数据库
        PageInfo<User> pi = new PageInfo<>(users);
        //上一页
        int up = page - 1;
        int next = page + 1;
        long total = pi.getPages();
        model.addAttribute("itemsNum", itemsNum);
        model.addAttribute("up", up);
        model.addAttribute("p", page);
        model.addAttribute("next", next);
        model.addAttribute("total", total);
        model.addAttribute("users", users);
        return "user/user.jsp";
    }

    /**
     * 多条件查询
     *
     * @param uname
     * @param state
     * @param model
     * @return
     */
    @RequestMapping("/getSUer")
    public String getSUer(String uname, int state, Model model, @RequestParam(value = "p", defaultValue = "1") int page) {
        int itemsNum = tsshservice.getSUser(uname, state).size();
        //PageHelper一定要在获取数据库集合之前
        PageHelper.startPage(page, 5);
        List<User> suers = tsshservice.getSUser(uname, state);
        //PageInfo一定要在获取到数据库
        PageInfo<User> pi = new PageInfo<>(suers);
        //上一页
        int up = page - 1;
        int next = page + 1;
        long total = pi.getPages();
        model.addAttribute("itemsNum", itemsNum);
        model.addAttribute("up", up);
        model.addAttribute("p", page);
        model.addAttribute("next", next);
        model.addAttribute("total", total);
        model.addAttribute("users", suers);
        return "user/user.jsp";
    }

    /**
     * 回显用户信息
     *
     * @param uname
     * @param model
     * @return
     */
    @RequestMapping("/getBackUser")
    public String getBackUser(String uname, Model model) {
        User user = tsshservice.getBackUser(uname);
        model.addAttribute("user", user);
        return "user/showUpdateUser.jsp";
    }


    /**
     * 页面跳转
     *
     * @param go
     * @return
     */
    @RequestMapping("/go")
    public String go(String go) {
        return go;
    }

    /**
     * 完成登录
     *
     * @param uname
     * @param pwd
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String toLogin(String uname, String pwd,  HttpServletRequest request,Model model) {
        User user = tsshservice.login(uname, pwd);
        String result = "";
        if (user == null) {
            result = "login.jsp";
        } else {
            request.getSession().setAttribute(HrmConstants.USER_SESSION, user);
            result = "forward:loginShowUser.action";
        }
        model.addAttribute("user",user);
        return result;
    }


    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUser(User user) {
        int no = tsshservice.updateUser(user);
        String inform = "";
        if (no != 0) {
            inform = "成功修改了" + no + "条数据";
        } else {
            inform = "修改失败";
        }
        System.out.println(inform);
        return "forward:getAllUser.action";
    }

    /**
     * 删除用户
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delUser")
    public String delUser(String ids) throws IOException{
        int count = tsshservice.delUser(ids);
        System.out.println("共删除了" + count + "条数据");
        return "forward:getAllUser.action";
    }

    /**
     * 增加用户
     * @param user
     * @return
     */
    @RequestMapping(value="/addUser",method = RequestMethod.POST)
    public String addUser(User user) throws IOException {
        int count=tsshservice.addUser(user);
        System.out.println("成功增加了" + count + "条数据");
        return "forward:getAllUser.action";
    }

    /**
     * 显示登录的用户名在main界面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/loginShowUser")
    public String loginShowUser(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        JSONObject json = new JSONObject();
        User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
        model.addAttribute("user",user);
        return "main.jsp";
    }


    /**
     * 修改密码
     * @param request
     * @param response
     * @param oldPassword
     * @param newPassword
     * @throws JSONException
     */
    @RequestMapping(value="/editPwd",method = RequestMethod.POST)
    public void updatePassWord(HttpServletRequest request, HttpServletResponse response, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) throws JSONException {
        HttpSession session = request.getSession();
        JSONObject json=new JSONObject();
        json.put("message", "");
        User user= (User) session.getAttribute(HrmConstants.USER_SESSION);
        if(oldPassword.equals(user.getPwd())){
            json.put("message", "修改成功");
            user.setPwd(newPassword);
            tsshservice.editPwd(user);
        } else {
            json.put("message" , "") ;
        }
        this.writeJson(json.toString(), response);
    }

}
