package com.ncepu.staffhome.service.serviceImpl;

import com.ncepu.staffhome.entity.Notice;
import com.ncepu.staffhome.mapper.TnotiMapper;
import com.ncepu.staffhome.service.NotiService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.apache.log4j.spi.Configurator.NULL;

@Service("notiservice")
@Transactional(readOnly = true)
public class NotiServiceImpl implements NotiService {

    @Autowired
    TnotiMapper tnotiMapper;

    @Override
    public List<Notice> getAllNoti() {
        return tnotiMapper.getAllNoti();
    }

    @Override
    public Notice getBackNoti(int notid) {
        return tnotiMapper.getBackNoti(notid);
    }

    @Override
    public Notice preNoti(int notid) {
        return tnotiMapper.preNoti(notid);
    }

    @Override
    public List<Notice> getSelNoti(String notname, String content) {
        return tnotiMapper.getSelNoti(notname,content);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)  //修改事务管理器
    public int updateNoti(Notice notice) {
        return tnotiMapper.updateNoti(notice);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)  //修改事务管理器
    public int delNoti(String ids) {
        String [] id=ids.split(",");
        int count=0;
        for (String i: id
        ) {
            if(i!=NULL && !i.equals("")){
                int notid=Integer.parseInt(i);
                int no=tnotiMapper.delNoti(notid);
                if(no!=0){
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)  //修改事务管理器
    public int addNoti(Notice notice) {
        return tnotiMapper.addNoti(notice);
    }
}
