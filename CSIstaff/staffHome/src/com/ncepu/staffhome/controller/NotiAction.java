package com.ncepu.staffhome.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ncepu.staffhome.entity.Department;
import com.ncepu.staffhome.entity.Notice;
import com.ncepu.staffhome.entity.User;
import com.ncepu.staffhome.service.NotiService;
import com.ncepu.staffhome.util.HrmConstants;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class NotiAction {

    @Autowired
    NotiService notiservice;

    /**
     * 显示所有公告信息
     *
     * @param model
     * @return
     */
    @RequestMapping("/getAllNoti")
    public String getAllNoti(Model model, @RequestParam(value = "p",defaultValue ="1" )int page) {
        int itemsNum=notiservice.getAllNoti().size();
        PageHelper.startPage(page,5);
        List<Notice> notices = notiservice.getAllNoti();
        PageInfo<Notice> pi=new PageInfo<>(notices);
        //上一页
        int up=page-1;
        int next=page+1;
        long total=pi.getPages();
        model.addAttribute("itemsNum",itemsNum);
        model.addAttribute("up",up);
        model.addAttribute("p",page);
        model.addAttribute("next",next);
        model.addAttribute("total",total);
        model.addAttribute("notices", notices);
        return "notice/notice.jsp";
    }

    /**
     * 多条件查询公告信息
     *
     * @param notname
     * @param content
     * @param model
     * @return
     */
    @RequestMapping("/getSelNoti")
    public String getSelNoti(@Param("notname") String notname, @Param("content") String content, Model model, @RequestParam(value = "p",defaultValue ="1" )int page) {
        int itemsNum=notiservice.getSelNoti(notname, content).size();
        PageHelper.startPage(page,5);
        List<Notice> notices = notiservice.getSelNoti(notname, content);
        PageInfo<Notice> pi=new PageInfo<>(notices);
        //上一页
        int up=page-1;
        int next=page+1;
        long total=pi.getPages();
        model.addAttribute("itemsNum",itemsNum);
        model.addAttribute("up",up);
        model.addAttribute("p",page);
        model.addAttribute("next",next);
        model.addAttribute("total",total);
        model.addAttribute("notices", notices);
        return "notice/notice.jsp";
    }

    /**
     * 修改回显公告
     *
     * @param notid
     * @param model
     * @return
     */
    @RequestMapping("/getBackNoti")
    public String getBackNoti(int notid, Model model) {
        Notice notice = notiservice.getBackNoti(notid);
        System.out.println("notice.toString() = " + notice.getUname());
        model.addAttribute("notice", notice);
        return "notice/showUpdateNotice.jsp";
    }

    /**
     * 修改公告信息
     * @param notice
     * @return
     */
    @RequestMapping(value = "/updateNoti",method = RequestMethod.POST)
    public String updateNoti(Notice notice) {
        int no=notiservice.updateNoti(notice);
        System.out.println("成功修改"+no+"条数据");
        return "forward:getAllNoti.action";
    }

    /**
     * 预览公告
     *
     * @param notid
     * @param model
     * @return
     */
    @RequestMapping("/preNoti")
    public String preNoti(int notid, Model model) {
        Notice notice = notiservice.preNoti(notid);
        //System.out.println("notice.toString() = " + notice.getUname());
        model.addAttribute("notice", notice);
        return "notice/previewNotice.jsp";
    }

    /**
     * 删除公告
     * @param ids
     * @return
     */
    @RequestMapping("/delNoti")
    public String delNoti(String ids){
        int count=notiservice.delNoti(ids);
        System.out.println("共删除了"+count+"条数据");
        return "forward:getAllNoti.action";
    }
    /**
     * 增加公告
     * @param notice
     * @return
     */
    @RequestMapping(value="/addNoti",method = RequestMethod.POST)
    public String addNoti(Notice notice){
        int count=notiservice.addNoti(notice);
        System.out.println("成功增加了"+count+"条数据");
        return "forward:getAllNoti.action";
    }

    /**
     * 跳转到添加界面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/toaddNotice")
    public String toaddNotice(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        JSONObject json = new JSONObject();
        User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
        model.addAttribute("user",user);
        return "notice/showAddNotice.jsp";
    }
}
