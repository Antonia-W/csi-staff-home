package com.ncepu.staffhome.service;

import com.ncepu.staffhome.entity.Notice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotiService {
    public List<Notice> getAllNoti();

        public Notice getBackNoti(int notid);

        public Notice preNoti(int notid);

        public List<Notice> getSelNoti(@Param("notname") String notname, @Param("content") String content);

        public int updateNoti(Notice notice);

        public int delNoti(String ids);

        public int addNoti(Notice notice);
}
