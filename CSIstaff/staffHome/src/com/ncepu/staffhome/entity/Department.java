package com.ncepu.staffhome.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Department {

/*
    dename              varchar(20) not null,
   dedetail            varchar(50),
   primary key (depname)
 */


    private String dename;  //部门名
    private String dedetail;  //部门详情
    private int did;

    private List<User> users;

    public Department() {
        users=new ArrayList<>();
    }

    public Department(String dename, String dedetail, int did) {
        this.dename = dename;
        this.dedetail = dedetail;
        this.did = did;
        users=new ArrayList<>();
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getDename() {
        return dename;
    }

    public void setDename(String dename) {
        this.dename = dename;
    }

    public String getDedetail() {
        return dedetail;
    }

    public void setDedetail(String dedetail) {
        this.dedetail = dedetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return did == that.did &&
                Objects.equals(dename, that.dename) &&
                Objects.equals(dedetail, that.dedetail) &&
                Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dename, dedetail, did, users);
    }
}
