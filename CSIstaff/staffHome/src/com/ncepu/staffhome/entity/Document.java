package com.ncepu.staffhome.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Document {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String docid;  //文件编号，用以区分文件
    private String doname;  //文件名
    private Date docretime;  //上传时间
    private String filename;  //文档名
    private String dodesc;
    private User uname;
    private String uuname;

    public Document() {
    }

    public Document(String docid, String doname, Date docretime, String dodesc, User uname, String filename) {
        this.docid = docid;
        this.doname = doname;
        this.docretime = docretime;
        this.dodesc = dodesc;
        this.uname = uname;
        this.filename = filename;
    }

    public String toDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (date == null) {
            return "";
        } else {
            return formatter.format(date);
        }
    }

    public String getUuname() {
        return uuname;
    }

    public void setUuname(String uuname) {
        this.uuname = uuname;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDoname() {
        return doname;
    }

    public void setDoname(String doname) {
        this.doname = doname;
    }

    public Date getDocretime() {
        return docretime;
    }

    public void setDocretime(Date docretime) {
        this.docretime = docretime;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }


    public String getDodesc() {
        return dodesc;
    }

    public void setDodesc(String dodesc) {
        this.dodesc = dodesc;
    }

    public User getUname() {
        return uname;
    }

    public void setUname(User uname) {
        this.uname = uname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(docid, document.docid) &&
                Objects.equals(doname, document.doname) &&
                Objects.equals(docretime, document.docretime) &&
                Objects.equals(dodesc, document.dodesc) &&
                Objects.equals(uname, document.uname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(docid, doname, docretime, dodesc, uname);
    }
}
