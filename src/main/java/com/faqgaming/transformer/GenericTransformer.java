package com.faqgaming.transformer;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericTransformer<ENTITY, DTO> {
	public abstract DTO  toDto(ENTITY entity);
	
	public List<DTO> toDto(List<ENTITY> entities){
	    List<DTO> dtos = new ArrayList<>();
	    for (ENTITY entity : entities) {
	      dtos.add(toDto(entity));
	    }
	    return dtos;
		
	}
	

}
