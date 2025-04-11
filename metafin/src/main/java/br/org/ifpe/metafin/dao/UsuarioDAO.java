package br.org.ifpe.metafin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.ifpe.metafin.model.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Integer>{

	Usuario findByEmailAndSenha(String email, String senha);

	Usuario findByEmail(String email);

}
