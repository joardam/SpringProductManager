package com.app;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.app.config.AppConfig;
import com.app.controller.ProdutoController;

import com.app.model.Categoria;
import com.app.model.Produto;


public class Application {

public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		
		
		ProdutoController listProdutoController = (ProdutoController) context.getBean("produtoController");
		
		
		
		for(Produto p : listProdutoController.getListProduto() ) {
			System.out.println(p.getDescricao());
		}
			
		
		
		System.out.println("print");
	
		((AbstractApplicationContext) context).close();
		
	}
}

