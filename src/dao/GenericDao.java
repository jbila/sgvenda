package com.meldev.dao;

import java.util.List;

public interface GenericDao<T> {
    T save(T entity);
    List<T> saveAll(List<T> entities);
    void deleteById(Long id);
    List<T> findAll(Long empresaId);
    List<T> findAll(String nome,Long empresaId);
  //  T findById(Long idEntity);
}