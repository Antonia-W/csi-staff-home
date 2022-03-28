package com.ncepu.staffhome.service.serviceImpl;

import com.ncepu.staffhome.entity.Document;
import com.ncepu.staffhome.entity.User;
import com.ncepu.staffhome.mapper.TdocuMapper;
import com.ncepu.staffhome.service.DocuService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.apache.log4j.spi.Configurator.NULL;

@Service("docuservice")
@Transactional(readOnly = true)
public class DocuServiceImpl implements DocuService {

    @Autowired
    TdocuMapper tdocumapper;

    @Override
    public List<Document> getAllDoc() {
        return tdocumapper.getAllDoc();
    }

    @Override
    public List<Document> getSelDoc(@Param("doname") String doname) {
        return tdocumapper.getSelDoc(doname);
    }

    @Override
    public Document getBackDoc(int docid) {
        return tdocumapper.getBackDoc(docid);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)  //修改事务管理器
    public int upload(Document document) {
        return tdocumapper.upload(document);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)  //修改事务管理器
    public int updateDoc(Document document) {
        return tdocumapper.updateDoc(document);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)  //修改事务管理器
    public int delDoc(String ids) {
        String [] id=ids.split(",");
        int count=0;
        for (String i: id
        ) {
            if(i!=NULL && !i.equals("")){
                int docid=Integer.parseInt(i);
                int no=tdocumapper.delDoc(docid);
                if(no!=0){
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public Document getOne(int docid) {
        return tdocumapper.getOne(docid);
    }


}
