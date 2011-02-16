package org.esupportail.blankmvcportlet.web;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.esupportail.blankmvcportlet.beans.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;

@Controller
public class WebController {

	private static final String PREF_USERNAME = "username";
    private static final String DEFAULT_USERNAME = "world";
    
    @RequestMapping("VIEW")
    protected ModelAndView renderView(RenderRequest request, RenderResponse response) throws Exception {
        
    	ModelMap model = new ModelMap();
    	
    	final PortletPreferences prefs = request.getPreferences();
    	String username = prefs.getValue(PREF_USERNAME, DEFAULT_USERNAME);
    	
    	// is username == "error" we return an error view !
    	if(username != "error") {
    		User user = new User(username);
    		model.put("user", user);
    	} else {
    	    return new ModelAndView("error", model);
    	}
        return new ModelAndView("view", model);
    }


    @RequestMapping("EDIT")
	public ModelAndView renderEditView(RenderRequest request, RenderResponse response) throws Exception {
        final PortletPreferences prefs = request.getPreferences();
		ModelMap model = new ModelMap("username", prefs.getValue(PREF_USERNAME, DEFAULT_USERNAME));
		return new ModelAndView("edit", model);
	}
    

    @RequestMapping(value = {"EDIT"}, params = {"action=setUsername"})
    public void setUsername(
            @RequestParam(value = "username", required = true) String username,
            ActionRequest request, ActionResponse response) throws Exception {
    	
        PortletPreferences prefs = request.getPreferences();
       
        ModelMap model = new ModelMap();
        
        // validate the submitted data
        if (StringUtils.hasText(username) && StringUtils.hasLength(username)) {
        	prefs.setValue(PREF_USERNAME, username);
            prefs.store();
        }

        return;
	}
    
    @RequestMapping("ABOUT")
	public ModelAndView renderAboutView(RenderRequest request, RenderResponse response) throws Exception {
		ModelMap model = new ModelMap();
		return new ModelAndView("about", model);
	}
    
    @RequestMapping("HELP")
	public ModelAndView renderHelpView(RenderRequest request, RenderResponse response) throws Exception {
		ModelMap model = new ModelMap();
		return new ModelAndView("help", model);
	}
    
}
