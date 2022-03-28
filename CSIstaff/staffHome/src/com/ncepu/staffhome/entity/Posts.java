package com.ncepu.staffhome.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Posts {

    /*
     postname             varchar(20) not null,
   postdetial           varchar(100),
     */

    private  String poname;  //职位名称
    private String podetial;  //职位详情
    private int pid;

    private List<User> users;

    public Posts() {
        users=new ArrayList<>();
    }

    public Posts(String poname, String podetial, int pid) {
        this.poname = poname;
        this.podetial = podetial;
        this.pid = pid;
        users=new ArrayList<>();
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPoname() {
        return poname;
    }

    public void setPoname(String poname) {
        this.poname = poname;
    }

    public String getPodetial() {
        return podetial;
    }

    public void setPodetial(String podetial) {
        this.podetial = podetial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Posts posts = (Posts) o;
        return pid == posts.pid &&
                Objects.equals(poname, posts.poname) &&
                Objects.equals(podetial, posts.podetial) &&
                Objects.equals(users, posts.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(poname, podetial, pid, users);
    }
}
