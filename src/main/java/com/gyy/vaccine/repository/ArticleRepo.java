package com.gyy.vaccine.repository;


import com.gyy.vaccine.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update article set title= ?1 , content=?2 where id= ?3", nativeQuery = true)
    int updateArticle(String title, String content, Integer id);
}
