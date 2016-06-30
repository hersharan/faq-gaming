package com.faqgaming.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.faqgaming.domain.AbstractMongoEntity;
import com.faqgaming.exception.EntityNotFoundException;
import com.faqgaming.exception.FaqGamingException;

public interface GenericService<T extends AbstractMongoEntity> {

	T findOne(String id) throws EntityNotFoundException, FaqGamingException;

	T save(T domain) throws FaqGamingException;

	List<T> save(List<T> domains) throws FaqGamingException;

	Page<T> findAll(Pageable page);

	List<T> findAll();

	void delete(String id) throws EntityNotFoundException, FaqGamingException;

	T update(String id, T domain) throws EntityNotFoundException;

	T update(T domain) throws FaqGamingException;

}
