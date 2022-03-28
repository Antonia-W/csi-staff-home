package com.ncepu.staffhome.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ncepu.staffhome.entity.Department;
import com.ncepu.staffhome.entity.Document;
import com.ncepu.staffhome.entity.User;
import com.ncepu.staffhome.service.DocuService;
import com.ncepu.staffhome.util.HrmConstants;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class DocuAction {

    @Autowired
    DocuService docuservice;

    /**
     * 显示所有文档
     *
     * @param model
     * @return
     */
    @RequestMapping("/getAllDoc")
    public String getAllDoc(Model model, @RequestParam(value = "p",defaultValue ="1" )int page) {
        int itemsNum=docuservice.getAllDoc().size();
        PageHelper.startPage(page,5);
        List<Document> docs = docuservice.getAllDoc();
        PageInfo<Document> pi=new PageInfo<>(docs);
        //上一页
        int up=page-1;
        int next=page+1;
        long total=pi.getPages();
        model.addAttribute("itemsNum",itemsNum);
        model.addAttribute("up",up);
        model.addAttribute("p",page);
        model.addAttribute("next",next);
        model.addAttribute("total",total);
        model.addAttribute("docs", docs);
        return "document/document.jsp";
    }

    /**
     * 多条件查询文档
     *
     * @param doname
     * @param model
     * @return
     */
    @RequestMapping("/getSelDoc")
    public String getSelDoc(@Param("doname") String doname, Model model, @RequestParam(value = "p",defaultValue ="1" )int page) {
        int itemsNum= docuservice.getSelDoc(doname).size();
        PageHelper.startPage(page,5);
        List<Document> docs = docuservice.getSelDoc(doname);
        PageInfo<Document> pi=new PageInfo<>(docs);
        //上一页
        int up=page-1;
        int next=page+1;
        long total=pi.getPages();
        model.addAttribute("itemsNum",itemsNum);
        model.addAttribute("up",up);
        model.addAttribute("p",page);
        model.addAttribute("next",next);
        model.addAttribute("total",total);
        model.addAttribute("docs", docs);
        return "document/document.jsp";
    }

    /**
     * 修改回显文档
     *
     * @param docid
     * @param modedl
     * @return
     */
    @RequestMapping("/getBackDoc")
    public String getBackDoc(int docid, Model modedl) {
        Document doc = docuservice.getBackDoc(docid);
        modedl.addAttribute("doc", doc);
        return "document/showUpdateDocument.jsp";
    }

    /**
     * 上传文件
     *
     * @param document
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "addDoc", method = RequestMethod.POST)
    public String upload(Document document, @RequestParam(value = "file", required = true) MultipartFile file) throws IOException {
        File address = new File("E:\\CSIstaff\\staffHome\\web\\document\\" + file.getOriginalFilename());
        file.transferTo(address);
        document.setFilename(file.getOriginalFilename());
        docuservice.upload(document);
        return "forward:getAllDoc.action";
    }

    /**
     * 修改文件的信息
     * @param document
     * @return
     */
    @RequestMapping(value="/updateDoc",method = RequestMethod.POST)
    public String updateDoc(Document document){
        docuservice.updateDoc(document);
        return "forward:getAllDoc.action";
    }

    /**
     * 删除公告
     * @param ids
     * @return
     */
    @RequestMapping("/delDoc")
    public String delDoc(String ids){
        int count=docuservice.delDoc(ids);
        System.out.println("共删除了"+count+"条数据");
        return "forward:getAllDoc.action";
    }

    /**
     * 跳转到添加界面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/toupload")
    public String toupload(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        JSONObject json = new JSONObject();
        User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
        model.addAttribute("user",user);
        return "document/showAddDocument.jsp";
    }

    @RequestMapping("/download")
    public String downloadFile(HttpServletRequest request, int docid, HttpServletResponse response) throws UnsupportedEncodingException {
        //获取文件名
        String fileName = docuservice.getOne(docid).getFilename();
        System.out.println(fileName);
        // 如果文件名不为空，则进行下载
        if (fileName != null) {
            //设置文件路径
            String realPath = "E:\\CSIstaff\\staffHome\\web\\document";
            File file = new File(realPath, fileName);
            // 如果文件名存在，则进行下载
            if (file.exists()) {
                // 配置文件下载
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                // 下载文件能正常显示中文
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                // 实现文件下载
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("下载成功");
                }
                catch (Exception e) {
                    System.out.println("下载失败");
                }
                finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        /*return "forward:getAllDoc.action";*/
        return null;
    }
}
