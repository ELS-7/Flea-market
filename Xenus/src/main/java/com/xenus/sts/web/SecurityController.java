package com.xenus.sts.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xenus.sts.service.StsService;

//Security controller

@Controller
public class SecurityController extends BaseController {
	
	//Service
	private final StsService sts;

	@Autowired
	public SecurityController(StsService sts) {
		this.sts = sts;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);
	
	//login
	@RequestMapping(value = "/com/login")
	public ModelAndView comlogin(HttpSession session, HttpServletRequest request, @RequestParam(value = "param",  required = false) String param) {
		ModelAndView mav = new ModelAndView();
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		String viewPath = "/market/login";
		String redirect = null;
		
		//check parameter
		if (param != null) {
			switch (param) {
				case "authSuccess"	: 	redirect = "/market/home"; break;
				case "needLogin"  	:	break;
				case "authFail"   	: 	model.put("error", "아이디와 비밀번호를 확인하시고 다시 입력하여 주시기 바랍니다.");	break;
				case "concurrent" 	: 	model.put("error", "다른곳에서 동시접속 되었습니다. 다시 로그인 하시기 바랍니다.");		break;	//invoked when page call, use filter-chain when AJax-call 
			}
		}
		
		if (redirect != null) {
			mav.setViewName("redirect:" + redirect);
			return mav;
		}
		
		sts.setModel(model, request);
    	sts.setMav(model, mav, viewPath);
    	logger.debug("login");
	    return mav;
	}


	//loginPage
	@RequestMapping(value = "/com/loginPage")	
	public ModelAndView loginPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		sts.setModel(model, request);
		sts.setMav(model, mav, "/market/login");
		return mav;
	}
	

	
	//logout
	@RequestMapping(value = "/com/logout", method = RequestMethod.GET)
	public ModelAndView logoutPage(HttpSession session, HttpServletRequest request, @RequestParam(value = "param",  required = false) String param) {
		ModelAndView mav = new ModelAndView();

		if (session != null)
			session.invalidate();
		
		mav.setViewName("redirect:/market/home");
		logger.debug("logout");
		return mav;
	}


	//denied
	@RequestMapping(value = "/com/403")	
	public ModelAndView accesssDenied(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		model.put("error", "Invalid authority.");
		sts.setMav(model, mav, "/com/403");
		return mav;
	}


	//page not found
	@RequestMapping(value = "/com/404")	
	public ModelAndView pageNorFound(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		model.put("error", "Invalid page.");
		sts.setMav(model, mav, "/com/404");
		return mav;
	}
	
}

//End Of This

