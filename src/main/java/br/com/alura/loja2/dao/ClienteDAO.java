package br.com.alura.loja2.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja2.modelo.Cliente;

public class ClienteDAO {
	private EntityManager em;
	
	public ClienteDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Cliente cliente) {
		this.em.persist(cliente);
	}

	public Cliente buscarPorId(long id) {		
		return em.find(Cliente.class, id);
	}
}
