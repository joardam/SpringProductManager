package com.app.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.app.connection.ConnectionFactory;
import com.app.model.Produto;

@Component
public class ProdutoDAO {
	
	public Produto save(Produto produto) {
		Session session = new ConnectionFactory().getConnection();
		
		try {
			session.getTransaction().begin();
			if(produto.getId() == null) {
				session.persist(produto);
			}else {
				session.merge(produto);
			}
			
			
			session.getTransaction().commit();;
		}catch(Exception e){
			System.err.println(e);
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		
		return produto;
	}
	
	public Produto findById(Integer id) {
		Session session = new ConnectionFactory().getConnection();
		Produto produto = null;
		
		try {
			produto = session.find(Produto.class, id);
			
		} catch(Exception e) {
			System.out.println(e);
		}finally {
			session.close();
		}
		return produto;
		
	}
	
	public List<Produto> findAll(){
		Session session = new ConnectionFactory().getConnection();
		List<Produto> produtos = null;

		
		try {
			
			TypedQuery<Produto> query = session.createQuery("from Produto p", Produto.class);
			produtos = query.getResultList();
			
		}catch(Exception e) {
			System.err.println(e);
		}finally {
			session.close();
		}
		
		return produtos;
	}
	
	
	public Produto remove(Integer id) {
		Session session = new ConnectionFactory().getConnection();
		Produto produto = null;
		
		try {
			
			
			produto = session.find(Produto.class, id);
			
			session.getTransaction().begin();
			session.remove(produto);
			
			session.getTransaction().commit();
			
		}catch(Exception e) {
			System.err.println(e);
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		
		
		return produto;
	}
	
	
}
