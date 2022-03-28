package com.ncepu.staffhome.entity;


import net.sf.json.JSONObject;
import org.apache.ibatis.jdbc.Null;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class User {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String uname;  //登录名
    private String name;  //用户名
    private String pwd;   //密码
    private String state;  //状态
    private Date ucretime;  //用户创建时间
    private String sex;  //性别
    private String tel;  //电话
    private String mobie;  //手机
    private String email;  //邮箱
    private String edubg;  //学历
    private String idnum;  //身份证号
    private String address;  //地址
    private Date stafftime;  //员工建档时间
    private String nation;  //民族
    private String polista;  //政治面貌
    private String postcode;  //邮政编码
    private String major;  //专业
    private String hobie;  //兴趣爱好
    private String ps;  //备注
    private String qqnum;  //qq号
    private String birthday;
    private Posts pid;
    private Department did;

    private String faceurl ;
    private String facepath ;

    private int poid;//职位
    private int deid;//部门

    public User() {
    }

    public User(String uname, String pwd) {
        this.uname = uname;
        this.pwd = pwd;

    }

    ;

    public User(String uname, String name, String state) {
        this.uname = uname;
        this.name = name;
        this.state = state;
    }

    public User(String uname, String name, String pwd, String state, Date ucretime, String sex, String tel, String mobie, String email, String edubg, String idnum, String address, Date stafftime, String nation, String polista, String postcode, String major, String hobie, String ps, String qqnum, String birthday, int poid, int deid) {
        this.uname = uname;
        this.name = name;
        this.pwd = pwd;
        this.state = state;
        this.ucretime = ucretime;
        this.sex = sex;
        this.tel = tel;
        this.mobie = mobie;
        this.email = email;
        this.edubg = edubg;
        this.idnum = idnum;
        this.address = address;
        this.stafftime = stafftime;
        this.nation = nation;
        this.polista = polista;
        this.postcode = postcode;
        this.major = major;
        this.hobie = hobie;
        this.ps = ps;
        this.qqnum = qqnum;
        this.birthday = birthday;
        this.poid = poid;
        this.deid = deid;
    }

    public User(String uname, String name, String pwd, String state, Date ucretime, String sex, String tel, String mobie, String email, String edubg, String idnum, String address, Date stafftime, String nation, String polista, String postcode, String major, String hobie, String ps, String qqnum, String birthday, Posts pid, Department did) {
        this.uname = uname;
        this.name = name;
        this.pwd = pwd;
        this.state = state;
        this.ucretime = ucretime;
        this.sex = sex;
        this.tel = tel;
        this.mobie = mobie;
        this.email = email;
        this.edubg = edubg;
        this.idnum = idnum;
        this.address = address;
        this.stafftime = stafftime;
        this.nation = nation;
        this.polista = polista;
        this.postcode = postcode;
        this.major = major;
        this.hobie = hobie;
        this.ps = ps;
        this.qqnum = qqnum;
        this.birthday = birthday;
        this.pid = pid;
        this.did = did;
    }


    public String toDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (date == null) {
            return "";
        } else {
            return formatter.format(date);
        }
    }

    public String getFaceurl() {
        return faceurl;
    }

    public void setFaceurl(String faceurl) {
        this.faceurl = faceurl;
    }

    public String getFacepath() {
        return facepath;
    }

    public void setFacepath(String facepath) {
        this.facepath = facepath;
    }

    public int getPoid() {
        return poid;
    }

    public void setPoid(int poid) {
        this.poid = poid;
    }

    public int getDeid() {
        return deid;
    }

    public void setDeid(int deid) {
        this.deid = deid;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getUcretime() {
        return ucretime;
    }

    public void setUcretime(Date ucretime) {
        this.ucretime = ucretime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEdubg() {
        return edubg;
    }

    public void setEdubg(String edubg) {
        this.edubg = edubg;
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getStafftime() {
        return stafftime;
    }

    public void setStafftime(Date stafftime) {
        this.stafftime = stafftime;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPolista() {
        return polista;
    }

    public void setPolista(String polista) {
        this.polista = polista;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getHobie() {
        return hobie;
    }

    public void setHobie(String hobie) {
        this.hobie = hobie;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getQqnum() {
        return qqnum;
    }

    public void setQqnum(String qqnum) {
        this.qqnum = qqnum;
    }

    public String getMobie() {
        return mobie;
    }

    public void setMobie(String mobie) {
        this.mobie = mobie;
    }

    public Posts getPid() {
        return pid;
    }

    public void setPid(Posts pid) {
        this.pid = pid;
    }

    public Department getDid() {
        return did;
    }

    public void setDid(Department did) {
        this.did = did;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(uname, user.uname) &&
                Objects.equals(name, user.name) &&
                Objects.equals(pwd, user.pwd) &&
                Objects.equals(state, user.state) &&
                Objects.equals(ucretime, user.ucretime) &&
                Objects.equals(sex, user.sex) &&
                Objects.equals(tel, user.tel) &&
                Objects.equals(mobie, user.mobie) &&
                Objects.equals(email, user.email) &&
                Objects.equals(edubg, user.edubg) &&
                Objects.equals(idnum, user.idnum) &&
                Objects.equals(address, user.address) &&
                Objects.equals(stafftime, user.stafftime) &&
                Objects.equals(nation, user.nation) &&
                Objects.equals(polista, user.polista) &&
                Objects.equals(postcode, user.postcode) &&
                Objects.equals(major, user.major) &&
                Objects.equals(hobie, user.hobie) &&
                Objects.equals(ps, user.ps) &&
                Objects.equals(qqnum, user.qqnum) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(pid, user.pid) &&
                Objects.equals(did, user.did);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uname, name, pwd, state, ucretime, sex, tel, mobie, email, edubg, idnum, address, stafftime, nation, polista, postcode, major, hobie, ps, qqnum, birthday, pid, did);
    }
}
