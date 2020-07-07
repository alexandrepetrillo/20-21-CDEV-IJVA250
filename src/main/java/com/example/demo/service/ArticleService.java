package com.example.demo.service;

import com.example.demo.controller.clientsidetemplating.dto.ArticleDto;
import com.example.demo.entity.Article;

import java.util.List;

public interface ArticleService {
    List<ArticleDto> findAll();

    ArticleDto findById(long articleId);

    ArticleDto create(ArticleDto dto);

    ArticleDto modifierStock(Long id, Integer quantity);
}
