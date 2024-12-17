package com.app;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.app.config.AppConfig;
import com.app.controller.ProdutoController;
import com.app.model.Categoria;


public class Application {

public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		ProdutoController produtoController = (ProdutoController)context.getBean("produtoController");
		
		Categoria categoria = new Categoria();
		categoria.setId(2);
		
		
		
		
		produtoController.getProduto().setDescricao("sambitas");
		produtoController.getProduto().setQtd(10);
		produtoController.getProduto().setValor(4.6);
		produtoController.getProduto().setCategoria(categoria);
		
		
		System.out.println("print");
	
		((AbstractApplicationContext) context).close();
		
	}
}

