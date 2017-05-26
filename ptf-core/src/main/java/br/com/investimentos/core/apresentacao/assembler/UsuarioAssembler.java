package br.com.investimentos.core.apresentacao.assembler;

import br.com.investimentos.core.apresentacao.dto.UsuarioDTO;
import br.com.investimentos.core.dominio.entidade.Usuario;

public final class UsuarioAssembler {
	private UsuarioAssembler(){}
	
	public static UsuarioDTO toDTO(Usuario usuario){
		UsuarioDTO dto = new UsuarioDTO();
		
		dto.setId(usuario.getId());
		dto.setDataCadastro(usuario.getDataCadastro());
		dto.setEmail(usuario.getEmail());
		dto.setNome(usuario.getNome());
		dto.setSenha(usuario.getSenha());
		dto.setSobreNome(usuario.getSobreNome());
		dto.setUsuario(usuario.getUsuario());
		
		return dto;
	}
	
	public static Usuario fromDTO(UsuarioDTO dto){
		Usuario usuario = new Usuario();
		
		usuario.setId(dto.getId());
		usuario.setDataCadastro(dto.getDataCadastro());
		usuario.setEmail(dto.getEmail());
		usuario.setNome(dto.getNome());
		usuario.setSenha(dto.getSenha());
		usuario.setSobreNome(dto.getSobreNome());
		usuario.setUsuario(dto.getUsuario());
		
		return usuario;
	}
}