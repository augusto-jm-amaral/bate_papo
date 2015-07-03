package org.projeto.exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * Fabrica para gerar a classe JsfExceptionHandlerFacroty esta deve ser setada
 * no faces config.
 * 
 * @author Augusto
 *
 */
public class JsfExceptionHandlerFactory extends ExceptionHandlerFactory {

	private ExceptionHandlerFactory parent;

	public JsfExceptionHandlerFactory(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}

	@Override
	public ExceptionHandler getExceptionHandler() {

		return new JsfExceptionHandler(parent.getExceptionHandler());
	}

}
