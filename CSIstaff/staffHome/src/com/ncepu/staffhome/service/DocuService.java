package com.ncepu.staffhome.service;

import com.ncepu.staffhome.entity.Document;
import com.ncepu.staffhome.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocuService {
    public List<Document> getAllDoc();

    public List<Document> getSelDoc(@Param("doname") String doname);

    public Document getBackDoc(int docid);

    public int upload(Document document);

    public int updateDoc(Document document);

    public int delDoc(String ids);

    public Document getOne(int docid);
}
