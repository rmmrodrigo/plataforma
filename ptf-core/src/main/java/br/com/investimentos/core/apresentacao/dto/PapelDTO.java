package br.com.investimentos.core.apresentacao.dto;

import java.io.Serializable;

public class PapelDTO implements Serializable{
	private static final long serialVersionUID = -7491570691119222270L;
	private String codigo;
	private String descricao;

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}