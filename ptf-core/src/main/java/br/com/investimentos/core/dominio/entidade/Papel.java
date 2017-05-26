package br.com.investimentos.core.dominio.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="papel")
public class Papel implements Serializable{
	private static final long serialVersionUID = 5248819839938559910L;

	@Id
	@Column(nullable=false)
	private String codigo;
	
	@Column(nullable=false)
	private String descricao;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="papel")
	private List<Ordem> acoes = new ArrayList<Ordem>(0);

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

	public List<Ordem> getAcoes() {
		return acoes;
	}

	public void setAcoes(List<Ordem> acoes) {
		this.acoes = acoes;
	}
}