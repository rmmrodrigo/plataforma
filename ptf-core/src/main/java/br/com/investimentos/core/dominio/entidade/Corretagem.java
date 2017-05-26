package br.com.investimentos.core.dominio.entidade;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="corretagem")
public class Corretagem extends Entidade{
	private static final long serialVersionUID = -5502495967673894550L;

	@Column(nullable=false)
	private BigDecimal valorInicial;
	
	@Column
	private BigDecimal valorFinal;
	
	@Column
	private BigDecimal taxa;
	
	@Column
	private BigDecimal fixo;
	
	@Column
	private Boolean ativa;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private Corretora corretora;

	public BigDecimal getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(BigDecimal valorInicial) {
		this.valorInicial = valorInicial;
	}

	public BigDecimal getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(BigDecimal valorFinal) {
		this.valorFinal = valorFinal;
	}

	public BigDecimal getTaxa() {
		return taxa;
	}

	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}

	public BigDecimal getFixo() {
		return fixo;
	}

	public void setFixo(BigDecimal fixo) {
		this.fixo = fixo;
	}

	public Corretora getCorretora() {
		return corretora;
	}

	public void setCorretora(Corretora corretora) {
		this.corretora = corretora;
	}

	public Boolean getAtiva() {
		return ativa;
	}

	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}
}