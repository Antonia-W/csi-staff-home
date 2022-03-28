package com.ncepu.staffhome.controller;

import com.ncepu.staffhome.entity.Facekey;
import com.ncepu.staffhome.entity.User;
import com.ncepu.staffhome.service.UserService;
import com.ncepu.staffhome.util.FaceClient;
import com.ncepu.staffhome.util.HrmConstants;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

@Controller
public class FaceAction extends BasicController {

    @Autowired
    UserService tsshservice;

    @RequestMapping("/user/faceRegister")
    public void faceRegister(HttpServletResponse response, HttpServletRequest request, @RequestParam("base") String base) {
        //springMVC支持servlet中的session，通过以下方式获取session对象
        HttpSession session = request.getSession();
        JSONObject json = new JSONObject();
        json.put("message", "");
        //从session中读取tuser用户的名称，所以在用户登录之后必然要将登录的用户信息存储到session,并且存储名称为HrmConstants.USER_SESSION
        User user = (User) session.getAttribute(HrmConstants.USER_SESSION);

        //HrmConstants.JOBTABLE:当使用类的时候，属性就为常量的表示方式，如果使用接口，那么只需要直接定义即可
        //System.out.println(Hrm.USERNAME); ;
        //照片的存储路径
        String path = request.getServletContext().getRealPath("/") + "headPhoto\\";
        //存储的照片路径+照片的名称
        String urlPath = request.getContextPath() + "/headPhoto/" + user.getUname() + ".jpg";
        File uploadDir = new File(path);
        if (!uploadDir.exists() && !uploadDir.isDirectory()) {// 检查目录
            uploadDir.mkdirs();//如果没有以上目录，则创建此目录
        }
        path += user.getUname() + ".jpg";
        OutputStream out = null;
        InputStream is = null;
        System.out.println("aaaaa==" + path);
        try {
            byte[] imgByte = new BASE64Decoder().decodeBuffer(base);
            for (int i = 0; i < imgByte.length; ++i) {
                if (imgByte[i] < 0) {// 调整异常数据
                    imgByte[i] += 256;
                }
            }
            out = new FileOutputStream(path);
            is = new ByteArrayInputStream(imgByte);
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = is.read(buff)) != -1) {
                out.write(buff, 0, len);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            json.put("message", "注册失败");
            e.printStackTrace();
            this.writeJson(json.toString(), response);
        } finally {
            if (is != null) {
                try {
                    is.close();

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        //将以上获取到的照片信息存储到数据库中
        System.out.println(user.getUname() + "===" + urlPath + " ===" + path);
        tsshservice.upDataFaceUrlByName(user.getUname(), urlPath, path);
        json.put("message", "注册成功");
        this.writeJson(json.toString(), response);
    }

    @RequestMapping("/user/faceLogin")
    public void faceLogin(HttpServletResponse response, HttpServletRequest request, @RequestParam("base") String base) {
        System.out.println(base);
        HttpSession session = request.getSession();
        JSONObject json = new JSONObject();
        json.put("message", "");
        //获取到所有的用户信息，可以直接调用之前的getAll方法
        List<User> list = tsshservice.getAllUser();
        //从数据库中读取到连接的百度云应用信息
        Facekey Facekey = tsshservice.selectFaceKye();
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if (user.getFacepath() != null && (!user.getFacepath().trim().equals(""))) {
                String base1 = this.getImageStr(user.getFacepath());
                //将登录拍下的图片与数据库中已经存储的注册的图片进行比对
                FaceClient faceClient = FaceClient.getInstance(Facekey.getAppID(), Facekey.getApiKey(), Facekey.getSecretKey());
                //登录图片与注册图片的比对
                boolean loginBool = faceClient.faceContrast(base, base1);
                System.out.println(loginBool);
                if (loginBool) {
                    json.put("message", "登录成功");
                    //当使用人脸登录成功的时候与密码登录原理相同，依然需要将用户信息存储到session中
                    session.setAttribute(HrmConstants.USER_SESSION, user);
                    break;
                } else {
                    if(i==list.size()-1){
                        json.put("message", "");
                        break;
                    }else{
                        continue;
                    }
                }
            }
        }
        this.writeJson(json.toString(), response);
    }


    @RequestMapping("/user/gotoFaceRegister")
    public String gotoFaceRegister() {
        return "user/face.jsp";
    }
}
