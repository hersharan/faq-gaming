package com.faqgaming.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.faqgaming.domain.AbstractMongoEntity;
import com.faqgaming.exception.EntityNotFoundException;

public interface GenericService<T extends AbstractMongoEntity> {

  T findOne(String id) throws EntityNotFoundException;

  T save(T domain);

  List<T> save(List<T> domains);

  Page<T> findAll(Pageable page);

  List<T> findAll();

  void delete(String id) throws EntityNotFoundException;

  T update(String id, T domain) throws EntityNotFoundException;

}
