package com.example.demo.service.impl.fake;

import com.example.demo.entity.Facture;
import com.example.demo.repository.FactureRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class FactureRepositoryFake implements FactureRepository {

    // on utilse que la méthode save

    @Override
    public <S extends Facture> S save(S s) {
        return null;
    }

    @Override
    public List<Facture> findAll() {
        return null;
    }

    @Override
    public List<Facture> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Facture> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Facture> findAllById(Iterable<Long> iterable) {
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
    public void delete(Facture facture) {

    }

    @Override
    public void deleteAll(Iterable<? extends Facture> iterable) {

    }

    @Override
    public void deleteAll() {

    }


    @Override
    public <S extends Facture> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Facture> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Facture> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Facture> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Facture getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Facture> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Facture> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Facture> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Facture> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Facture> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Facture> boolean exists(Example<S> example) {
        return false;
    }
}
