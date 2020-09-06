package com.example.websystem.mapper;

import com.example.websystem.model.Knowledge;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface KnowledgeMapper {
    @Select("select * from knowledge where knowledgename=#{knowledgename}")
    Knowledge getknowledge(String knowledgename);
    @Insert("insert into knowledge (knowledgename,definition,feature) values (#{knowledgename},#{definition},#{feature})")
    void addknowledge(Knowledge knowledge);
    @Update("update knowledge set definition=#{definition},feature=#{feature} where knowledgename=#{knowledgename}")
    void updateknowledge(String knowledgename,String definition,String feature);
    @Delete("delete from knowledge where knowledgename=#{knowledgename}")
    void deleteknowledge(String knowledgename);
    @Select("select count(*) from knowledge")
    int knowledgecount();
    @Select("select * from knowledge")
    public List<Knowledge> getAllKnowledge();
}
