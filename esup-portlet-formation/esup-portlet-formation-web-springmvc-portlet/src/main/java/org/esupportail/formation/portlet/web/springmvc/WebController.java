package org.esupportail.formation.portlet.web.springmvc;

import java.util.List;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.esupportail.formation.domain.Task;
import org.esupportail.formation.portlet.domain.DomainService;
import org.esupportail.formation.portlet.domain.beans.User;
import org.esupportail.formation.portlet.services.auth.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

@Controller
public class WebController extends AbastractExceptionController {

	private static final String PREF_USERNAME = "usernamepref";
	
    private static final String DEFAULT_USERNAME = "JavaWorld";
    
    private static final String PREF_WSDL = "wsdlLocation";
	
    @Autowired
	private Authenticator authenticator;
    
    @Autowired
	private DomainService domainService;
    
    @RequestMapping("VIEW")
    protected ModelAndView renderView(RenderRequest request, RenderResponse response) throws Exception {
        
    	ModelMap model = new ModelMap();
    	
    	final PortletPreferences prefs = request.getPreferences();
    	String usernamePref = prefs.getValue(PREF_USERNAME, DEFAULT_USERNAME);
    	model.put("usernamePref", usernamePref);
    	
    	String remoteUser = request.getRemoteUser();
    	model.put("remoteUser", remoteUser);
    	
    	User userFromEC2 = authenticator.getUser();
    	model.put("userFromEC2", userFromEC2);

    	String wsdl = prefs.getValue(PREF_WSDL,null);
    	System.out.println("Tâche de "+remoteUser);
    	List<Task> list= domainService.getTasks(remoteUser, wsdl);
    	model.put("taskList", list);
    	for (Task task : list) {
    		System.out.println(task.getId()+" : "+task.getDescription());
		}
    	System.out.println("Toutes les tâches");
    	List<Task> list2= domainService.getAllTasks(wsdl);
    	for (Task task : list2) {
    		System.out.println(task.getId()+" : "+task.getDescription());
		}
    	
        return new ModelAndView("view", model);
    }

    @RequestMapping("ABOUT")
	public ModelAndView renderAboutView(RenderRequest request, RenderResponse response) throws Exception {
		ModelMap model = new ModelMap();
		return new ModelAndView("about", model);
	}
    

}
