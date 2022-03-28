package com.ncepu.staffhome.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Notice {

    /*
    notid                varchar(10) not null,
   ulogname             varchar(20),
   notname              varchar(20) not null,
   content              varchar(50) not null,
   nottime              date not null,
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String notid; //公告编号，用以区分公告
    private String notname; //公告名
    private String content;  //公告内容
    private Date nottime;  //公告时间
    private User uname;

    private String cuname;

    public Notice() {
    }

    public Notice(String notid, String notname, String content, Date nottime) {
        this.notid = notid;
        this.notname = notname;
        this.content = content;
        this.nottime = nottime;
    }

    public Notice(String notid, String notname, String content, Date nottime, User uname) {
        this.notid = notid;
        this.notname = notname;
        this.content = content;
        this.nottime = nottime;
        this.uname = uname;
    }

    public String toDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (date == null) {
            return "";
        } else {
            return formatter.format(date);
        }
    }

    public String getCuname() {
        return cuname;
    }

    public void setCuname(String cuname) {
        this.cuname = cuname;
    }

    public User getUname() {
        return uname;
    }

    public void setUname(User uname) {
        this.uname = uname;
    }

    public String getNotid() {
        return notid;
    }

    public void setNotid(String notid) {
        this.notid = notid;
    }

    public String getNotname() {
        return notname;
    }

    public void setNotname(String notname) {
        this.notname = notname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getNottime() {
        return nottime;
    }

    public void setNottime(Date nottime) {
        this.nottime = nottime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notice notice = (Notice) o;
        return Objects.equals(notid, notice.notid) &&
                Objects.equals(notname, notice.notname) &&
                Objects.equals(content, notice.content) &&
                Objects.equals(nottime, notice.nottime) &&
                Objects.equals(uname, notice.uname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notid, notname, content, nottime, uname);
    }


}
