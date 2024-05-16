package com.xenus.sts.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xenus.sts.service.StsService;
import com.xenus.sts.util.FileUploader;
import com.xenus.sts.util.JsonMapper;
import com.xenus.sts.vo.Returns;

//market controller

@Controller
@RequestMapping(value = {"/market"})
public class MarketController {
	
	//service
	private final StsService sts;

	@Autowired
	public MarketController(StsService sts) {
		this.sts = sts;
	}
	
	@Resource(name = "jsonMapper")
	private JsonMapper jsonMapper;
	private static final Logger logger = LoggerFactory.getLogger(MarketController.class);
	
	//home
	@RequestMapping(value = {"/home"})
    public ModelAndView processHome(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		logger.debug("1.processHome");
		sts.setModel(model, request);
		sts.getData(model, request, "home");
		sts.setMav(model, mav, "/market/home");
		System.out.println("1");
		return mav;
    }
	
	//singup
	
	@RequestMapping(value = {"/signup"})
    public ModelAndView processSignup(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		HashMap<String,Object> model = new HashMap<String,Object>();
		sts.setModel(model, request);
		sts.setMav(model, mav, "/market/signup");
		logger.debug(session.getId());
		
		return mav;
    }
	
	//addMember
	@RequestMapping(value = {"/addMember"})
    public ModelAndView processAddMember(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		logger.debug(request.getParameterMap().toString());
		
		//준비
		sts.setModel(model, request);
		
		//처리
		sts.setData(model, request, "memberAdd");
		
		//리턴
		// StsService 에서 setMav를 통해 데이터와 웹페이지 명을 mav에 저장. 
		sts.setMav(model, mav, "/market/signup");
		
		//이후 mav를 리턴( setViewName에 해당하는 web페이지가(view값) 로드되고 addObject로 저장한 모델 값을 key로 읽을 수 있음)
		return mav;
    }
	
	//memberList
	@RequestMapping(value = {"/memberList"})
    public ModelAndView processMemberList(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		sts.setModel(model, request);
		sts.getData(model, request, "memberList");
		sts.setMav(model, mav, "/market/memberList");

		return mav;
    }
	
	//memberModForm
	@RequestMapping(value = {"/member/memberModForm"})
    public ModelAndView processMemberModForm(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		sts.setModel(model, request);
		sts.getData(model, request, "memberOne");
		sts.setMav(model, mav, "/market/memberModForm");

		return mav;
    }
	
	//memberMod
	@RequestMapping(value = {"/member/memberMod"})
    public void processMemberSave(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		//준비
		sts.setModel(model, request);
		
		//처리
		sts.setData(model, request, "memberMod");
		
		//리턴
		ResponseJson(response, model.get("returns"));
    }

	
	//memberDel
	@RequestMapping(value = {"/member/memberDel"})
    public ModelAndView processMemberDel(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		sts.setModel(model, request);
		sts.setData(model, request, "memberDel");

		mav.setViewName("redirect:/market/memberList");
		return mav;
    }
	
	//productList
	@RequestMapping(value = {"/productList"})
    public ModelAndView processProductList(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		sts.setModel(model, request);
		sts.getData(model, request, "productList");
		sts.setMav(model, mav, "/market/productList");

		return mav;
    }
	
	//productAll
	@RequestMapping(value = {"/productAll"})
    public ModelAndView processProductAll(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		sts.setModel(model, request);
		sts.getData(model, request, "productAll");
		sts.setMav(model, mav, "/market/productAll");

		return mav;
    }
	
	//productSold
	@RequestMapping(value = {"/productSold"})
    public ModelAndView processProductSold(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		sts.setModel(model, request);
		sts.getData(model, request, "productSold");
		sts.setMav(model, mav, "/market/productSold");

		return mav;
    }
		
	//productUnSold
	@RequestMapping(value = {"/productUnSold"})
    public ModelAndView processProductUnSold(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		sts.setModel(model, request);
		sts.getData(model, request, "productUnSold");
		sts.setMav(model, mav, "/market/productUnSold");
		return mav;
    }
		
		
	
	//productView
	@RequestMapping(value = {"/productView"})
    public ModelAndView processProductView(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		sts.setModel(model, request);
		sts.getData(model, request, "productView");
		sts.setMav(model, mav, "/market/productView");

		return mav;
    }
	
	//productModForm
	@RequestMapping(value = {"/product/productModForm"})
    public ModelAndView processProductModForm(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		sts.setModel(model, request);
		sts.getData(model, request, "productOne");
		sts.setMav(model, mav, "/market/productModForm");

		return mav;
    }
	
	//uploadFile
	@RequestMapping(value = {"/product/uploadFile"})
    public void uploadImage(@ModelAttribute("fileUploader") FileUploader fileUploader, 
    						HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IllegalStateException, IOException {
		
		HashMap<String,Object> model = new HashMap<String,Object>();
		sts.setModel(model, request);
		
		Returns returns  = sts.setFile(model, fileUploader);
		ResponseJson(response, returns);
    }
	
	
	//productIns
	@RequestMapping(value = {"/product/productIns"})
    public void processProductIns(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		//준비
		sts.setModel(model, request);
		
		//처리
		sts.setData(model, request, "productIns");
		
		//리턴
		ResponseJson(response, model.get("returns"));
    }
	
	//productMod
	@RequestMapping(value = {"/product/productMod"})
    public void processProductMod(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		sts.setModel(model, request);
		sts.setData(model, request, "productMod");
		ResponseJson(response, model.get("returns"));
    }

	//productDel
	@RequestMapping(value = {"/product/productDel"})
    public ModelAndView processProductDel(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		sts.setModel(model, request);
		sts.setData(model, request, "productDel");
		
		mav.setViewName("redirect:/market/productList");
		return mav;
    }
	
	//productBuy
	@RequestMapping(value = {"/product/productBuy"})
    public void processProductBuy(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		sts.setModel(model, request);
		sts.setData(model, request, "productBuy");
		ResponseJson(response, model.get("returns"));
    }
	
	//cartList
	@RequestMapping(value = {"/cartList"})
    public ModelAndView processCartList(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		sts.setModel(model, request);
		sts.getData(model, request, "cartList");
		sts.setMav(model, mav, "/market/cartList");

		return mav;
    }
	
	//cartSold
	@RequestMapping(value = {"/cart/cartSold"})
    public ModelAndView processCartSold(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		HashMap<String,Object> model = new HashMap<String,Object>();

		sts.setModel(model, request);
		sts.setData(model, request, "cartSold");
		mav.setViewName("redirect:/market/cartList");

		return mav;
    }
		
	//cartModPrice
	@RequestMapping(value = {"/cart/cartModPrice"})
    public void processCartModPrice(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		sts.setModel(model, request);
		sts.setData(model, request, "cartModPrice");
		ResponseJson(response, model.get("returns"));
    }
	
	//cartDel
	@RequestMapping(value = {"/cart/cartDel"})
    public ModelAndView processCartDel(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		HashMap<String,Object> model = new HashMap<String,Object>();

		sts.setModel(model, request);
		sts.setData(model, request, "cartDel");
		
		mav.setViewName("redirect:/market/cartList");
		return mav;
    }
	
	//soldList
	@RequestMapping(value = {"/soldList"})
    public ModelAndView processSoldList(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		sts.setModel(model, request);
		sts.getData(model, request, "soldList");
		sts.setMav(model, mav, "/market/soldList");

		return mav;
    }
	
	//wishList
	@RequestMapping(value = {"/wishList"})
    public ModelAndView processWishList(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		sts.setModel(model, request);
		sts.getData(model, request, "wishList");
		sts.setMav(model, mav, "/market/wishList");

		return mav;
    }
		
	//wishIns
	@RequestMapping(value = {"/wish/wishIns"})
    public void processWishIns(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		//준비
		sts.setModel(model, request);
		
		//처리
		sts.setData(model, request, "wishIns");
		
		//리턴
		ResponseJson(response, model.get("returns"));
    }
		
	//wishDel
	@RequestMapping(value = {"/wish/wishDel"})
    public ModelAndView processWishDel(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		sts.setModel(model, request);
		sts.setData(model, request, "wishDel");
		
		mav.setViewName("redirect:/market/wishList");
		return mav;
    }

	//downloadFile
    @RequestMapping("/downloadFile")
	public ResponseEntity<InputStreamResource> downloadFile(@RequestParam String fName) throws IOException {
		String fileDir = "D:/DevJava/updn/";
		
        File   file     = new File(fileDir + fName);
        String fileName = file.getName();

	    HttpHeaders header = new HttpHeaders();
	    
	    try {
	        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName);
	        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
	        header.add("Pragma", "no-cache");
	        header.add("Expires", "0");
	        
		    InputStreamResource resource3 = new InputStreamResource(new FileInputStream(file));
		        
	        return ResponseEntity.ok()
	                .headers(header)
	                .contentLength(file.length())
	                .contentType(MediaType.parseMediaType("application/octet-stream"))
	                .body(resource3);
	    
	    } catch(Exception e) {
	    	return  null;
	    }
	}
	
	//responseJson
	private void ResponseJson(HttpServletResponse response, Object pojo) throws JsonMappingException, JsonGenerationException, IOException {
		ByteArrayOutputStream outjson = new ByteArrayOutputStream();
		jsonMapper.toJson(pojo, outjson, true);
		
		response.setContentType("application/json ");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String OutJson = outjson.toString("UTF-8");
		out.println(OutJson);
		
		if (logger.isDebugEnabled())
			logger.debug("==> ResponseJson: JsonData {}", OutJson.substring(0, OutJson.indexOf("}") + 1));
	}
}

//End Of This

