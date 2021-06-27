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

public class CadastroDeProduto2 {
	
	public static void main(String[] args) {
		
		Categoria celulares = new Categoria("CELULARES");		
				
		EntityManager em = JPAUtil.getEntityManager();				
		em.getTransaction().begin();
		
		em.persist(celulares);
		celulares.setNome("XPTO");
		
		em.flush();
		em.clear();
		
		celulares = em.merge(celulares);
		celulares.setNome("1234");
		em.flush();
		//em.clear();
		em.remove(celulares);
		em.flush();
		
		//em.getTransaction().commit();
		//em.close();		
	}
}
