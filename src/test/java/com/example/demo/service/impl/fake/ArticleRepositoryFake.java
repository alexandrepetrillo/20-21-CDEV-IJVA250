package com.example.demo.service.impl.fake;

import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class ArticleRepositoryFake implements ArticleRepository {

    @Override
    public Optional<Article> findById(Long aLong) {
        return Optional.of(new Article());
    }

    @Override
    public List<Article> findTopArticleAchete() {
        return null;
    }

    @Override
    public List<Article> findAll() {
        return null;
    }

    @Override
    public List<Article> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Article> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Article> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Article article) {

    }

    @Override
    public void deleteAll(Iterable<? extends Article> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Article> S save(S s) {
        return null;
    }

    @Override
    public <S extends Article> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Article> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Article> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Article getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Article> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Article> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Article> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Article> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Article> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Article> boolean exists(Example<S> example) {
        return false;
    }
}
