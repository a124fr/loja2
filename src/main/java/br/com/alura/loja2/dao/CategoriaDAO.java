package br.com.alura.loja2.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja2.modelo.Categoria;

public class CategoriaDAO {
	
	private EntityManager em;
	
	public CategoriaDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Categoria produto) {
		this.em.persist(produto);
	}
}
