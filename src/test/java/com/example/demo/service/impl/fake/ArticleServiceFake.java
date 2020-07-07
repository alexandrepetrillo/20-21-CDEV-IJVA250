package com.example.demo.service.impl.fake;

import com.example.demo.controller.clientsidetemplating.dto.ArticleDto;
import com.example.demo.entity.Article;
import com.example.demo.service.ArticleService;

import java.util.List;

public class ArticleServiceFake implements ArticleService {
    @Override
    public List<Article> findAll() {
        return null;
    }

    @Override
    public Article findById(long articleId) {
        Article articleFake = new Article();
        return articleFake;
    }

    @Override
    public ArticleDto create(ArticleDto dto) {
        return null;
    }
}
