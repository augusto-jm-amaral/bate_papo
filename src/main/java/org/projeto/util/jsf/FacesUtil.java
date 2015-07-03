package org.projeto.util.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public class FacesUtil {

	public static boolean isPostback() {
		return FacesContext.getCurrentInstance().isPostback();
	}

	public static boolean isNotPostback() {
		return !isPostback();
	}

	public static void addErrorMessage(String message) {
		FacesContext.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, message,
								message));
	}

	public static void addInfoMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}

	public static void addMessageFlashScope(String key, String message) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
		flash.put(key, message);
		flash.setKeepMessages(true);
	}

	public static String getMessageFlashScope(String key) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
		if (flash.containsKey(key)) {

			Object object = flash.get(key);

			return (String) object;
		}

		return null;
	}

	public static void clearFashScope() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.clear();
	}

}
