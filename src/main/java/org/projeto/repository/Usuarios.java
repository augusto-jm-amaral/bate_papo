package org.projeto.repository;

import java.io.Serializable;

import javax.inject.Inject;

import org.projeto.model.Usuario;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;

public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Jedis jedis;

	@Inject
	private Gson gson;

	public Usuario findUserForEmail(String email) {

		String usuarioJson = jedis.get("usuario:" + email);
		Usuario usuario = null;

		if (usuarioJson != null) {

			usuario = gson.fromJson(usuarioJson, Usuario.class);
		}

		return usuario;
	}

	// if(email.equals("jao@email.com")){
	//
	// Usuario usuarioMock = new Usuario();
	// usuarioMock.setNome("Jao");
	// usuarioMock.setEmail("jao@email.com");
	// usuarioMock.setSenha("123");
	// usuarioMock.setId(1);
	//
	// return usuarioMock;
	// }

}
