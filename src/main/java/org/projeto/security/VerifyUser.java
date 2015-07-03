package org.projeto.security;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;

import org.projeto.annotation.Protected;

@Named
@RequestScoped
@Protected
public class VerifyUser {
	
	public void verificaUsuarioLogado(ComponentSystemEvent e){
		
	}
}
