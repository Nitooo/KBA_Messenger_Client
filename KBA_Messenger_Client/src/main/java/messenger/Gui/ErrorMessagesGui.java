package messenger.Gui;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ErrorMessagesGui {

	/**
	 * generates a warning message on page
	 * 
	 * @param message that should be shown own page
	 */
	public void warn(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Warnung!", message));
	}

	/**
	 * generates a error message on page
	 * 
	 * @param message that should be shown own page
	 */
	public void error(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler!", message));
	}

}
