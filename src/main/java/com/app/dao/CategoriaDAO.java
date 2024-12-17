package com.app.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.app.connection.ConnectionFactory;
import com.app.model.Categoria;

@Component
public class CategoriaDAO {
	
	public Categoria save(Categoria categoria) {
		Session session = new ConnectionFactory().getConnection();
		
		
		try {
			session.getTransaction().begin();
			if(categoria.getId() == null) {
				session.persist(categoria);
			} else {
				session.merge(categoria);
			}
			
			session.getTransaction().commit();
		} catch(Exception e) {
			session.getTransaction().rollback();
			System.err.println(e);
		} finally {
			session.close();
		}
		return categoria;
	}
	
	
	public Categoria findById(Integer id) {
		Session session = new ConnectionFactory().getConnection();
		Categoria categoria = null;
		try {
			categoria = session.find(Categoria.class, id);
			
		}catch(Exception e){
			System.err.println(e);
			
		}finally{
			session.close();
		}
		return categoria;
	}
	
	
	public List<Categoria> findAll(){
		Session session = new ConnectionFactory().getConnection();
		List<Categoria> categorias = null;
		
		try {
			
			TypedQuery<Categoria> query = session.createQuery("from Categoria c", Categoria.class);
			categorias = query.getResultList();
			
		}catch(Exception e) {
			System.err.println(e);
		}finally{
			session.close();
		}
		
		return categorias;
	}
	
	
	public Categoria removeById(Integer id) {
		Session session = new ConnectionFactory().getConnection();
		Categoria categoria = null;
		
		try {
			categoria = session.find(Categoria.class,id);
			
			session.getTransaction().begin();
			session.remove(categoria);
			session.getTransaction().commit();
			
		}catch(Exception e){
			System.err.println(e);
			session.getTransaction().rollback();
			
		}finally {
			session.close();
		}
		
		return categoria;
	}
	
	
	
}
