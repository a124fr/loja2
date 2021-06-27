package br.com.alura.loja2.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja2.modelo.Categoria;

public class CategoriaDAO {
	
	private EntityManager em;
	
	public CategoriaDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Categoria categoria) {
		this.em.persist(categoria);
	}
	
	public void atualizar(Categoria categoria) {
		this.em.merge(categoria);
	}
	
	public void remover(Categoria categoria) {
		categoria = this.em.merge(categoria);// garantir que o estado da entity esteja manager
		this.em.remove(categoria);
	}
}
