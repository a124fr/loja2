package br.com.alura.loja2.vo;

import java.time.LocalDate;

public class RelatorioDeVendasVo {
	private String nomeProduto;
	private Long quantidadeVendida;
	private LocalDate dataUltimavenda;

	public RelatorioDeVendasVo(String nomeProduto, Long quantidadeVendida, LocalDate dataUltimavenda) {
		super();
		this.nomeProduto = nomeProduto;
		this.quantidadeVendida = quantidadeVendida;
		this.dataUltimavenda = dataUltimavenda;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public Long getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public LocalDate getDataUltimavenda() {
		return dataUltimavenda;
	}

	@Override
	public String toString() {
		return "RelatorioDeVendasVo [nomeProduto=" + this.nomeProduto + ", quantidadeVendida=" + this.quantidadeVendida
				+ ", dataUltimavenda=" + this.dataUltimavenda + "]";
	}
}
