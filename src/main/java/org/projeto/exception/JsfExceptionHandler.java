package org.projeto.exception;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

public class JsfExceptionHandler extends ExceptionHandlerWrapper {

	private ExceptionHandler wrapped;

	public JsfExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {

		return this.wrapped;
	}

	// Metodo responsavel por tratar a exception
	@Override
	public void handle() throws FacesException {
		Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents()
				.iterator();

		boolean handled = false;

		while (events.hasNext()) {
			ExceptionQueuedEvent event = events.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event
					.getSource();
			Throwable exception = context.getException();

			try {
				if (exception instanceof ViewExpiredException) {
					System.out.println("[DEBUG] Exception ViewExpired tratada");
					handled = true;
					redirect("/");
				} else { // ERRO DE SISTEMA
					handled = true;
					// add log
					// envia a pagina de erro
				}
			} finally {
				if (handled) {
					events.remove();
					handled = false;
				}
			}
		}

		getWrapped().handle();
	}

	// Redireciona Pagina
	private void redirect(String path) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			String requestContextPath = externalContext.getRequestContextPath();

			externalContext.redirect(requestContextPath + path);
			context.responseComplete();
		} catch (IOException e) {

			throw new RuntimeException(e);
		}
	}

}
