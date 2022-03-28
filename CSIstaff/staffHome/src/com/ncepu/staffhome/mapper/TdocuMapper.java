package com.ncepu.staffhome.mapper;

import com.ncepu.staffhome.entity.Document;
import com.ncepu.staffhome.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TdocuMapper {
    List<Document> getAllDoc();

    List<Document> getSelDoc(@Param("doname")  String doname);

    Document getBackDoc(int docid);

    int upload(Document document);

    int updateDoc(Document document);

    int delDoc(int docid);

    Document getOne(int docid);
}
