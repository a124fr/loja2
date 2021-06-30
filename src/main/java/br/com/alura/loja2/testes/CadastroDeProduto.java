package br.com.alura.loja2.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja2.dao.CategoriaDAO;
import br.com.alura.loja2.dao.ProdutoDAO;
import br.com.alura.loja2.modelo.Categoria;
import br.com.alura.loja2.modelo.CategoriaId;
import br.com.alura.loja2.modelo.Produto;
import br.com.alura.loja2.util.JPAUtil;

public class CadastroDeProduto {
	
	public static void main(String[] args) {		
		cadastrarProduto();	
		
		EntityManager em = JPAUtil.getEntityManager();		
		ProdutoDAO pDAO = new ProdutoDAO(em);
		
		Produto p = pDAO.buscarPorId(1l);
		System.out.println(p.getPreco());
		
		List<Produto> todos = pDAO.buscarPorNomeDaCategoria("CELULARES");
		todos.forEach(p2 -> System.out.println(p2.getNome()));
		
		BigDecimal precoDoProduto = pDAO.buscarPorPrecoDoProdutoComNome("Xiaomi Redmi");
		System.out.println("-------------------------------------------");
		System.out.println("Preço do Produto: " + precoDoProduto);
		
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");		
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
				
		EntityManager em = JPAUtil.getEntityManager();
		CategoriaDAO cDAO = new CategoriaDAO(em);
		ProdutoDAO pDAO = new ProdutoDAO(em);
		
		em.getTransaction().begin();
		
		cDAO.cadastrar(celulares);
		pDAO.cadastrar(celular);
		
		em.find(Categoria.class, new CategoriaId("CELULARES", "XPTO"));
		
		em.getTransaction().commit();
		em.close();
	}
}
