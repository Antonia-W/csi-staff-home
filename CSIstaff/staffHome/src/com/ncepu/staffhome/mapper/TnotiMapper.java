package com.ncepu.staffhome.mapper;

import com.ncepu.staffhome.entity.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TnotiMapper {
    public List<Notice> getAllNoti() ;

    Notice getBackNoti(int notid);

    Notice preNoti(int notid);

    List<Notice> getSelNoti(@Param("notname") String notname, @Param("content") String content);

    int updateNoti(Notice notice);

    int delNoti(int notid);

    int addNoti(Notice notice);
}
