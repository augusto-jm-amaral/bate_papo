package org.projeto.interceptor;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;

import org.projeto.annotation.Protected;
import org.projeto.model.Usuario;
import org.projeto.util.jsf.FacesProducerRequest;

@Interceptor
@Protected
public class CheckProtection implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String MENSAGEM_PRECISALOGAR = "messagePrecisaLogar";

	@FacesProducerRequest
	@Inject
	private HttpServletRequest request;

	@Inject
	private ExternalContext externalContext;

	@AroundInvoke
	public Object check(InvocationContext context) throws Exception {

		System.out.println("[DEBUG] FILTRO USUARIO");

		Usuario usuario = (Usuario) request.getSession().getAttribute(
				"USUARIO_LOGADO");

		if (usuario != null) {

			Object proceed = context.proceed();
			System.out.println("[DEBUG] " + usuario.getEmail());

			return proceed;

		} else {

			System.out.println("[DEBUG] Usuario null");

			externalContext.redirect("login.xhtml");

			return null;
		}

	}
}
