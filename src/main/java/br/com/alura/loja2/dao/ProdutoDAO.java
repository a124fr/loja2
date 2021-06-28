package br.com.alura.loja2.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja2.modelo.Produto;

public class ProdutoDAO {
	
	private EntityManager em;
	
	public ProdutoDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}
	
	public Produto buscarPorId(Long id) {
		return this.em.find(Produto.class, id);		
	}
	
	public List<Produto> buscarTodos() {
		String jpql = "SELECT p FROM Produto p";
		return this.em.createQuery(jpql,Produto.class).getResultList();
	}
	
}
