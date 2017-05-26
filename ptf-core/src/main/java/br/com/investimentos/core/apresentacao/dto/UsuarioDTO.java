package br.com.investimentos.core.apresentacao.dto;

import java.util.Date;

public class UsuarioDTO extends AbstractDTO{
	private static final long serialVersionUID = -3878295398778136444L;

	private String nome;
	private String sobreNome;
	private String email;
	private String usuario;
	private String senha;
	private Date dataCadastro;
	private Boolean aceite;
	private Boolean lembrar = Boolean.FALSE;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Boolean getAceite() {
		return aceite;
	}

	public void setAceite(Boolean aceite) {
		this.aceite = aceite;
	}

	public Boolean getLembrar() {
		return lembrar;
	}

	public void setLembrar(Boolean lembrar) {
		this.lembrar = lembrar;
	}
}