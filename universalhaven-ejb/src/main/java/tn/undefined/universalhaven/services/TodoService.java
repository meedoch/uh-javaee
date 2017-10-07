package tn.undefined.universalhaven.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.undefined.universalhaven.persistence.Todo;

@Stateless
public class TodoService implements TodoServiceRemote, TodoServiceLocal {
	
	@PersistenceContext
	private EntityManager em;

    public TodoService() {
    }

	public void create(Todo todo) {
		em.persist(todo);
	}

	public List<Todo> findAll() {
		return em.createQuery("from Todo", Todo.class).getResultList();
	}

}
