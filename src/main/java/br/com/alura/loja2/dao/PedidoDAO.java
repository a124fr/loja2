package br.com.alura.loja2.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja2.modelo.Pedido;
import br.com.alura.loja2.vo.RelatorioDeVendasVo;

public class PedidoDAO {
	private EntityManager em;
	
	public PedidoDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}
	
	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p ";
		return em.createQuery(jpql, BigDecimal.class)
				.getSingleResult();
	}
	
	public List<Object[]> relatorioDeVendas() {
		String jpql = "SELECT produto.nome, "
				+ "SUM(item.quantidade), "
				+ "MAX(pedido.data) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.itens item "
				+ "JOIN item.produto produto "
				+ "GROUP BY produto.nome "
				+ "ORDER BY item.quantidade DESC";
		return em.createQuery(jpql, Object[].class)
				.getResultList();
	}
	
	public List<RelatorioDeVendasVo> relatorioDeVendas2() {
		String jpql = "SELECT new br.com.alura.loja2.vo.RelatorioDeVendasVo("
				+ "produto.nome, "
				+ "SUM(item.quantidade), "
				+ "MAX(pedido.data)) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.itens item "
				+ "JOIN item.produto produto "
				+ "GROUP BY produto.nome "
				+ "ORDER BY item.quantidade DESC";
		return em.createQuery(jpql, RelatorioDeVendasVo.class)
				.getResultList();
	}
	
	public Pedido buscarPedidoComCliente(Long id) { /* faz carregamento de cliente nessa consulta devido o relacionamento ser Lazy  */
		return em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id", Pedido.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}
