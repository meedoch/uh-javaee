package tn.undefined.universalhaven.services;

import java.util.List;

import javax.ejb.Local;

import tn.undefined.universalhaven.persistence.Todo;

@Local
public interface TodoServiceLocal {
	
	void create(Todo todo);
	List<Todo> findAll();

}
