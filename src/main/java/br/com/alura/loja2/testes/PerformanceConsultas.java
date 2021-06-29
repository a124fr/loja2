package br.com.alura.loja2.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja2.dao.CategoriaDAO;
import br.com.alura.loja2.dao.ClienteDAO;
import br.com.alura.loja2.dao.PedidoDAO;
import br.com.alura.loja2.dao.ProdutoDAO;
import br.com.alura.loja2.modelo.Categoria;
import br.com.alura.loja2.modelo.Cliente;
import br.com.alura.loja2.modelo.ItemPedido;
import br.com.alura.loja2.modelo.Pedido;
import br.com.alura.loja2.modelo.Produto;
import br.com.alura.loja2.util.JPAUtil;

public class PerformanceConsultas {

	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		
//		Pedido pedido = em.find(Pedido.class, 1l);
		PedidoDAO pedidoDAO = new PedidoDAO(em); 
		Pedido pedido = pedidoDAO.buscarPedidoComCliente(1l);
		
		System.out.println(pedido.getData());
		System.out.println(pedido.getItens().size());
		
		em.close(); /* teste para caso onde não temos os controle de quando o entitymanager esteja fechado.*/
		System.out.println(pedido.getCliente().getNome());		
	}
	
	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");		
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");
		
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);		
		Produto videogame = new Produto("PS5", "PLASTATION 5", new BigDecimal("4000"), videogames);
		Produto macbook = new Produto("Macbook", "Macboo pro retina", new BigDecimal("10000"), informatica);
		
		Cliente cliente = new Cliente("Rodrigo", ")123456");
		
		EntityManager em = JPAUtil.getEntityManager();
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		ProdutoDAO produtoDAO = new ProdutoDAO(em);		
		ClienteDAO clienteDAO = new ClienteDAO(em);
		PedidoDAO pedidoDAO = new PedidoDAO(em);
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, celular));
		pedido.adicionarItem(new ItemPedido(2, pedido, videogame));
		
		Pedido pedido2 = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(1, pedido2, macbook));
		
		
		em.getTransaction().begin();
		
		categoriaDAO.cadastrar(celulares);
		categoriaDAO.cadastrar(videogames);
		categoriaDAO.cadastrar(informatica);
		
		produtoDAO.cadastrar(celular);		
		produtoDAO.cadastrar(videogame);
		produtoDAO.cadastrar(macbook);
		
		clienteDAO.cadastrar(cliente);
				
		pedidoDAO.cadastrar(pedido);
		pedidoDAO.cadastrar(pedido2);
		
		em.getTransaction().commit();
		em.close();
	}


}
