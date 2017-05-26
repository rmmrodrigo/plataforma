package br.com.investimentos.core.apresentacao.fachada;

import java.io.Serializable;
import java.util.Date;

import br.com.investimentos.core.apresentacao.assembler.UsuarioAssembler;
import br.com.investimentos.core.apresentacao.dto.UsuarioDTO;
import br.com.investimentos.core.dominio.DomainStore;
import br.com.investimentos.core.dominio.entidade.Usuario;
import br.com.investimentos.core.dominio.repositorio.UsuarioRepositorio;
import br.com.investimentos.core.infraestrutura.HibernateUtil;
import br.com.investimentos.core.infraestrutura.anotacao.Transacional;

public class UsuarioFachada implements Serializable{
	private static final long serialVersionUID = 1257385759853388370L;

	private DomainStore domainStore = new HibernateUtil();
	private UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();

	public UsuarioDTO login(UsuarioDTO usuarioDTO){
		Usuario usuario = usuarioRepositorio.recuperarUsuario(usuarioDTO.getUsuario(), usuarioDTO.getSenha());
		
		if(usuario == null){
			return null;
		}
		
		return UsuarioAssembler.toDTO(usuario);
	}
	
	@Transacional
	public void salvar(UsuarioDTO usuarioDTO){
		Usuario usuario = UsuarioAssembler.fromDTO(usuarioDTO);
		usuario.setDataCadastro(new Date());
		
		domainStore.salvar(usuario);
	}
	
	@Transacional
	public void alterar(UsuarioDTO usuarioDTO){
		Usuario usuario = UsuarioAssembler.fromDTO(usuarioDTO);
		usuario.setDataCadastro(new Date());
		
		domainStore.alterar(usuario);
	}
}