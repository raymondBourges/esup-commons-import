package org.esupportail.blankmvcservlet.web;

import org.esupportail.blankmvcservlet.beans.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

    private static final String DEFAULT_USERNAME = "world";
    
    @RequestMapping("/")
    public ModelAndView renderView(User user) {
    	if(user.getUsername() == null)
    		user.setUsername(DEFAULT_USERNAME);
    	ModelMap model = new ModelMap("user", user);
        return new ModelAndView("view", model);
    }
    
    @RequestMapping("/ajax")
    public @ResponseBody String renderView(String username) {
        return "Salut " + username + ", c'est dynamique aujourd'hui !";
    }
    
    @RequestMapping("/about")
	public ModelAndView renderAboutView() {
		ModelMap model = new ModelMap();
		return new ModelAndView("about", model);
	}
    
    @RequestMapping("/ajaxUser")
    public @ResponseBody User getUser(String username) {
        return new User(username);
    }
    
    @RequestMapping("/ajaxUserPut")
    public @ResponseBody User getUser(User user) {
        return user;
    }
    
}
