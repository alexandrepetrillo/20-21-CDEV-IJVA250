package com.example.demo.service.impl;

import com.example.demo.controller.clientsidetemplating.dto.ArticleDto;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.service.ArticleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Service contenant les actions métiers liées aux articles.
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream().map(this::toDto).collect(toList());
    }

    @Override
    public ArticleDto findById(long articleId) {
        Optional<Article> article = articleRepository.findById(articleId);
        return toDto(article.get());
    }

    @Override
    public ArticleDto create(ArticleDto dto) {
        Article article = new Article();
        article.setLibelle(dto.getLibelle());
        article.setPrix(dto.getPrix());
        articleRepository.save(article);
        return toDto(article);
    }

    @Override
    public ArticleDto modifierStock(Long id, Integer quantity) {
        Article article = articleRepository.findById(id).get();
        article.setStock(quantity);
        return toDto(article);
    }

    private ArticleDto toDto(Article article) {
        return new ArticleDto(article.getId(), article.getLibelle(), article.getPrix(), article.getStock());
    }
}
