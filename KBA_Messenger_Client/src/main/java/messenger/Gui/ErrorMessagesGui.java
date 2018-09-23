package messenger.Gui;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ErrorMessagesGui {
	
	public void warn(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warnung!", message));
    }
  	
  	public void error(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler!", message));
    }

}
