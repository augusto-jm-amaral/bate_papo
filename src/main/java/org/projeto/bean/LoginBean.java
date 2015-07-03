package org.projeto.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.projeto.model.Usuario;
import org.projeto.repository.Usuarios;
import org.projeto.util.jsf.FacesProducerRequest;
import org.projeto.util.jsf.FacesUtil;

@Named
@RequestScoped
public class LoginBean implements Serializable {

	private static final String MENSSAGEM_ERRO_LOGIN_INCORRETO = "menssagem_erro";
	private static final long serialVersionUID = 1L;

	private String email;
	private String senha;

	@FacesProducerRequest
	@Inject
	private HttpSession session;

	@Inject
	private Usuarios usuarios;

	public void preRender(ComponentSystemEvent componentSystemEvent) {

		String messageErroLogin = FacesUtil
				.getMessageFlashScope(MENSSAGEM_ERRO_LOGIN_INCORRETO);

		if (messageErroLogin != null) {

			FacesUtil.addErrorMessage(messageErroLogin);
		}

		FacesUtil.clearFashScope();

	}

	public String logout() {

		System.out.println("[DEBUG] LoginBean logout()");

		this.session.removeAttribute("USUARIO_LOGADO");

		return "login.xhtml?faces-redirect=true";
	}

	public String login() {

		Usuario userFind = this.usuarios.findUserForEmail(this.email);

		if (userFind != null) {

			if (userFind.getSenha().equals(this.senha)) {

				session.setAttribute("USUARIO_LOGADO", userFind);
				this.senha = "";
				this.email = "";
				return "home.xhtml?faces-redirect=true";
			}

		}

		this.senha = "";

		FacesUtil.addMessageFlashScope(MENSSAGEM_ERRO_LOGIN_INCORRETO,
				"E-mail ou senha estão incorretos");

		return "login.xhtml?faces-redirect=true";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
