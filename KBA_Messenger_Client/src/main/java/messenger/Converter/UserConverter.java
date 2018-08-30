package messenger.Converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import messenger.Domain.User;
import messenger.Gui.UserBean;

@FacesConverter("userConverter")
public class UserConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String userId) {
    	if(userId == "")
    	{ 
    		return null;
    	}
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{userBean}", UserBean.class);

        UserBean userBean = (UserBean)vex.getValue(ctx.getELContext());
        return userBean.getUserManagement().getUserById(Long.parseLong(userId));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object user) {
    	if(user == null || ((User)user).getUserId() == null) {
    		return "";
    	}
        return ((User)user).getUserId().toString();
    }

}
