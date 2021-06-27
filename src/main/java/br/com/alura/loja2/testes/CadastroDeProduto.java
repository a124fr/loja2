package br.com.alura.loja2.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.loja2.dao.CategoriaDAO;
import br.com.alura.loja2.dao.ProdutoDAO;
import br.com.alura.loja2.modelo.Categoria;
import br.com.alura.loja2.modelo.Produto;
import br.com.alura.loja2.util.JPAUtil;

public class CadastroDeProduto {
	
	public static void main(String[] args) {
		
		Categoria celulares = new Categoria("CELULARES");		
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
				
		EntityManager em = JPAUtil.getEntityManager();
		CategoriaDAO cDAO = new CategoriaDAO(em);
		ProdutoDAO pDAO = new ProdutoDAO(em);
		
		em.getTransaction().begin();
		
		cDAO.cadastrar(celulares);
		pDAO.cadastrar(celular);
		
		em.getTransaction().commit();
		em.close();		
	}
}
