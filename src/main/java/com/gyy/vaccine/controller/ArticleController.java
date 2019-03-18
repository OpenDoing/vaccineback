package com.gyy.vaccine.controller;

import com.gyy.vaccine.entity.Article;
import com.gyy.vaccine.repository.ArticleRepo;
import com.gyy.vaccine.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleRepo articleRepo;

    @PostMapping("/add")
    public Object addArticle(@RequestBody Article article) {
        LocalDateTime now = LocalDateTime.now();
        article.setCtime(now);
        articleRepo.save(article);
        return ResponseUtil.ok();
    }

    @PostMapping("/update")
    public Object updateArticle(@RequestParam String title, @RequestParam String content,
                                @RequestParam Integer id) {
        articleRepo.updateArticle(title, content, id);
        return ResponseUtil.ok();
    }

    @GetMapping("/get")
    public Object getArticle(@RequestParam Integer id) {
        return ResponseUtil.ok(articleRepo.findById(id));
    }

    @DeleteMapping("/del")
    public Object delArticle(@RequestParam Integer id) {
        articleRepo.deleteById(id);
        return ResponseUtil.ok();
    }

    @GetMapping("/all")
    public Object getAll() {
        return ResponseUtil.ok(articleRepo.findAll());
    }
}
