package com.faqgaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.faqgaming.domain.AbstractMongoEntity;
import com.faqgaming.exception.FaqGamingException;

public abstract class AbstractService<T extends AbstractMongoEntity> implements
		GenericService<T> {

	@Autowired
	private MongoRepository<T, String> mongoRepository;

	@Override
	public T findOne(String id) throws FaqGamingException {
		T entity =  mongoRepository.findOne(id);
		if(entity == null){
			throw notFoundException();
		}
		return entity;
	}


	@Override
	public T save(T domain) throws FaqGamingException {

		return mongoRepository.save(domain);
	}

	@Override
	public List<T> save(List<T> domains) throws FaqGamingException {
		return mongoRepository.save(domains);
	}

	@Override
	public Page<T> findAll(Pageable page) {
		
		return mongoRepository.findAll(page);
	}

	@Override
	public List<T> findAll() {
		return mongoRepository.findAll();
	}

	@Override
	public void delete(String id) throws FaqGamingException {
		T entity =  mongoRepository.findOne(id);
		if(entity == null){
			throw notFoundException();
		}
		mongoRepository.delete(entity);
	}

	@Override
	public T update(T domain) throws FaqGamingException {
		domain.copyEntityFrom(domain);
	    return save(domain);
		
	}
	
	protected abstract FaqGamingException notFoundException();

}
