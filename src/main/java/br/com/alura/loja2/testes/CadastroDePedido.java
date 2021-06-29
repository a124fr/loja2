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
import br.com.alura.loja2.vo.RelatorioDeVendasVo;

public class CadastroDePedido {

	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		
		ProdutoDAO pDAO = new ProdutoDAO(em);
		ClienteDAO clienteDAO = new ClienteDAO(em);
		
		List<Produto> listaTodosProdutos = pDAO.buscarTodos();
		
		System.out.println("---------------LISTA DE PRODUTOS--------------------------");
		listaTodosProdutos.forEach(prod->System.out.println("Produto - Id: " + prod.getId() + " - Nome: " + prod.getNome()));
		System.out.println("----------------------------------------------------------");
		
		Produto produto = pDAO.buscarPorId(1l);
		Produto produto2 = pDAO.buscarPorId(2l);
		Produto produto3 = pDAO.buscarPorId(4l);
		Cliente cliente = clienteDAO.buscarPorId(1l);
		
		em.getTransaction().begin();
						
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, produto));
		pedido.adicionarItem(new ItemPedido(2, pedido, produto2));
		
		Pedido pedido2 = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(2, pedido2, produto3));
		
		PedidoDAO pedidoDAO = new PedidoDAO(em);
		pedidoDAO.cadastrar(pedido);
		pedidoDAO.cadastrar(pedido2);
		em.getTransaction().commit();
		
		BigDecimal totalVendido = pedidoDAO.valorTotalVendido();
		System.out.println("VALOR TOTAL " + totalVendido);
		
//		List<Object[]> relatorio = pedidoDAO.relatorioDeVendas();
//		for (Object[] obj : relatorio) {
//			System.out.print(obj[0] + " - ");
//			System.out.print(obj[1] + " - ");
//			System.out.print(obj[2]);
//			System.out.println();
//		}
		
		List<RelatorioDeVendasVo> relatorio = pedidoDAO.relatorioDeVendas2();
		relatorio.forEach(System.out::println);
		
		
		em.close();
	}
	
	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");		
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");
		
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
		Produto celular2 = new Produto("Galaxy Samsung", "Muit bom", new BigDecimal("500"), celulares);
		Produto videogame = new Produto("PS5", "PLASTATION 5", new BigDecimal("4000"), videogames);
		Produto macbook = new Produto("Macbook", "Macboo pro retina", new BigDecimal("10000"), informatica);
		
		Cliente cliente = new Cliente("Rodrigo", "123456");
		
		EntityManager em = JPAUtil.getEntityManager();
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		ClienteDAO clienteDAO = new ClienteDAO(em);
		
		em.getTransaction().begin();
		
		categoriaDAO.cadastrar(celulares);
		categoriaDAO.cadastrar(videogames);
		categoriaDAO.cadastrar(informatica);
		produtoDAO.cadastrar(celular);
		produtoDAO.cadastrar(celular2);
		produtoDAO.cadastrar(videogame);
		produtoDAO.cadastrar(macbook);
		clienteDAO.cadastrar(cliente);
		
		em.getTransaction().commit();
		em.close();
	}

}
