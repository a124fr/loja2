package br.com.alura.loja2.testes;

import java.math.BigDecimal;
import java.util.List;

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

public class CadastroDePedido {

	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		
		ProdutoDAO pDAO = new ProdutoDAO(em);
		ClienteDAO clienteDAO = new ClienteDAO(em);
		
		Produto produto = pDAO.buscarPorId(1l);
		Cliente cliente = clienteDAO.buscarPorId(1l);
		
		em.getTransaction().begin();
						
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, produto));
		
		PedidoDAO pedidoDAO = new PedidoDAO(em);
		pedidoDAO.cadastrar(pedido);
		em.getTransaction().commit();
		
		BigDecimal totalVendido = pedidoDAO.valorTotalVendido();
		System.out.println("VALOR TOTAL " + totalVendido);
		
		List<Object[]> relatorio = pedidoDAO.relatorioDeVendas();
		
		for (Object[] obj : relatorio) {
			System.out.println(obj[0]);
			System.out.println(obj[1]);
			System.out.println(obj[2]);
		}
		
		
		em.close();
	}
	
	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");		
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
		
		Cliente cliente = new Cliente("Rodrigo", "123456");
		
		EntityManager em = JPAUtil.getEntityManager();
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		ClienteDAO clienteDAO = new ClienteDAO(em);
		
		em.getTransaction().begin();
		
		categoriaDAO.cadastrar(celulares);
		produtoDAO.cadastrar(celular);
		clienteDAO.cadastrar(cliente);
		
		em.getTransaction().commit();
		em.close();
	}

}
