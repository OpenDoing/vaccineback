package com.gyy.vaccine.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {
    /**
     * 更新文章
     * @param title 文章标题
     * @param content 文章内容
     * @param id 文章标识
     * @return 1 is success
     */
    int updateArticle(String title, String content, Integer id);

}
