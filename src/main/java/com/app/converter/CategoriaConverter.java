package com.app.converter;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import javax.inject.Inject;

import com.app.controller.CategoriaController;
import com.app.model.Categoria;

@ManagedBean(name = "categoriaConverter")
@RequestScoped
public class CategoriaConverter implements Converter, Serializable {

    private static final long serialVersionUID = 1L;
    
    
   CategoriaController categoriaController;
    
    @PostConstruct
    public void init() {
    	
    }

    @Override
    public Categoria getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
        	
        	 CategoriaController categoriaController = (CategoriaController) context.getApplication()
                     .evaluateExpressionGet(context, "#{categoriaController}", CategoriaController.class);

             List<Categoria> listCategoria = categoriaController.getListCategoria();
             
    
            return listCategoria.stream()
                    .filter(categoria -> categoria.getId().equals(Integer.parseInt(value)))
                    .findFirst()
                    .orElse(null);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID da categoria inválido: " + value, e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof Categoria) {
            return String.valueOf(((Categoria) value).getId());
        } else {
            throw new IllegalArgumentException("Objeto inválido: " + value);
        }
    }
}
