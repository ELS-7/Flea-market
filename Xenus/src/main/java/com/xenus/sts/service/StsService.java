package com.xenus.sts.service;
// StsService는 함수들의 집합 
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xenus.sts.dao.StsDao;
import com.xenus.sts.util.FileUploader;
import com.xenus.sts.util.JsonMapper;
import com.xenus.sts.vo.CommMap;
import com.xenus.sts.vo.Returns;
import com.xenus.sts.web.BaseController;

@Service
public class StsService extends BaseController {

	//DAO
	private	StsDao		 stsDao;
	
	@Autowired
	public StsService(StsDao stsDao) {
		this.stsDao = stsDao;
	}
	
	//variables
	@Resource(name = "jsonMapper")
	private JsonMapper jsonMapper;	
	private static final Logger logger = LoggerFactory.getLogger(StsService.class);

	//setModel은 model을 세팅 (request 내용중 contextPath, user, css를 가져오기 위한 resources)
	public void setModel(Map<String, Object> model, HttpServletRequest request) {
		model.put("contextPath", request.getContextPath());
		model.put("user",        getLoginUser());
		
		//resources
		model.put("resources",  request.getContextPath() + "/resources");
		logger.debug("ContextPath :", request.getContextPath());
		
		if (logger.isInfoEnabled()) {
			logger.info ("\n");
			logger.info ("-->     Paging: {} {}", request.getRemoteAddr(), request.getRequestURI());
		}

		logger.debug("2.setModel");
	}

	
	public void setModel(HttpServletRequest request) {
		if (logger.isInfoEnabled()) {
			logger.info ("\n");
			logger.info ("-->    Ajaxing: {} {}", request.getRemoteAddr(), request.getRequestURI());
		}
	}
	
	//model의 내용을 읽어서 view에 저장  - 데이터를 받아와서 model에 넣어주는 역할
	public void setMav(Map<String, Object> model, ModelAndView mav, String viewName) {
		model.put("view", viewName);

		logger.debug("5.stsService.setMav");
		for (String key : model.keySet()) {
			//web페이지에서 사용할 return 값들(.stsService부분에서 채워진것들) - 데이터와 웹페이지 명을 던져줌
			// 데이터(addObjectfh)
	    	mav.addObject(key, model.get(key));
	    	
	    	//if (logger.isDebugEnabled()) {
	    		logger.debug("model map {} : {}", key, model.get(key));
	    	//}
	    }
		
		if (viewName.contains("cartList")) viewName += getLoginUser().getMemberType().equals("seller") ? "Seller" : "Buyer";
		if (viewName.contains("soldList")) viewName += getLoginUser().getMemberType().equals("seller") ? "Seller" : "Buyer";
		// 웹페이지 명
		mav.setViewName(viewName);
	}
	
	
	//getData
	public int getData(Map<String, Object> model, HttpServletRequest request, String processmode) {
		CommMap param = new CommMap(request);
		model.put("param", param);

		logger.debug("3.stsService.getData");
		
		try {
			switch(processmode) {
				case "home":			param.put("kind",		"list");
										model.put("products", 	stsDao.stsGetProduct(param));
										break;
										
				case "memberList":		param.put("kind",		"list");
										model.put("memberList", 	stsDao.stsGetMember(param));
										break;
										
				case "memberOne":		param.put("kind",		"one");
										model.put("members", 	stsDao.stsGetMember(param));
										break;
										
				case "productList":		param.put("kind",		"list");
										param.put("sellerId",	getLoginUser().getId());
										model.put("products", 	stsDao.stsGetProduct(param));
										break;
										
				case "productAll":		param.put("kind",		"list");
										param.put("sellerId",	getLoginUser().getId());
										model.put("products", 	stsDao.stsGetProductAll(param));
										break;
										
				case "productOne":		param.put("kind",		"one");
										model.put("products", 	stsDao.stsGetProduct(param));
										model.put("productIndex",param.get("productIndex"));
										break;										
										
				case "productView":		param.put("kind",		"one");
										param.put("sellerId",	getLoginUser().getMemberType().equals("seller") ? getLoginUser().getId() : "");
										param.put("buyerId",	getLoginUser().getMemberType().equals("buyer")  ? getLoginUser().getId() : "");
										model.put("products", 	stsDao.stsGetProduct(param));
										break;
										
				case "cartList":		param.put("kind",		"list");
										param.put("sellerId",	getLoginUser().getMemberType().equals("seller") ? getLoginUser().getId() : "");
										param.put("buyerId",	getLoginUser().getMemberType().equals("buyer")  ? getLoginUser().getId() : "");
										model.put("products", 	stsDao.stsGetCart(param));
										break;
										
				case "soldList":		param.put("kind",		"list");
										param.put("sellerId",	getLoginUser().getMemberType().equals("seller") ? getLoginUser().getId() : "");
										param.put("buyerId",	getLoginUser().getMemberType().equals("buyer")  ? getLoginUser().getId() : "");
										model.put("products", 	stsDao.stsGetSold(param));
										break;
										
				case "productSold":		param.put("kind",		"list");
										model.put("products", 	stsDao.stsGetSold(param));
										break;
										
				case "productUnSold":	param.put("kind",		"list");
										model.put("products", 	stsDao.stsGetProdUnSold(param));
										break;
										
				case "wishList":		param.put("kind",		"list");
										param.put("buyerId",	getLoginUser().getId());
										model.put("products", 	stsDao.stsGetWish(param));
										break;
			}
			
			logger.debug("3.stsService.getData: param-->" + param);
			
			return 0;
			
		} catch (Exception e) {	
			e.printStackTrace();
			return -1;
		}
	}
	
	
	//setData
	// database를 읽어서 model에 return 값을 저장
	public int setData(Map<String, Object> model, HttpServletRequest request, String processmode) {
		// request에서 들어온 parameter(변수값들)을 CommMap 클래스의 param에 저장
		CommMap param = new CommMap(request);
		//request에서 받은 값을 param을 param이란 이름으로 다시 model에 저장 - 클라이언트가 보낸값을, 재사용하기 위해서
		model.put("param", param);
		
		try {
			switch(processmode) {
				case "memberAdd":		model.put("returns", stsDao.stsSetMemberIns(param));
										break;
				
				case "memberMod":		param.put("kind", "mod");
										model.put("returns", stsDao.stsSetMemberMod(param));
										break;
										
				case "memberDel":		param.put("kind", "del");
										model.put("returns", stsDao.stsSetMemberMod(param));
										break;
										
				case "productIns":		model.put("returns", stsDao.stsSetProductIns(param));
										break;
						
				case "productMod":		param.put("kind", "mod");
										model.put("returns", stsDao.stsSetProductMod(param));
										break;
										
				case "productDel":		param.put("kind", "del");
										model.put("returns", stsDao.stsSetProductMod(param));
										break;
										
				case "productBuy":		model.put("returns", stsDao.stsSetCartIns(param));
										break;
										
				case "cartSold":		model.put("returns", stsDao.stsSetSoldIns(param));
										break;
										
				case "cartModPrice":	param.put("kind", "mod");
										model.put("returns", stsDao.stsSetCartMod(param));
										break;
										
				case "cartDel":			param.put("kind", "del");
										model.put("returns", stsDao.stsSetCartMod(param));
										break;
										
				case "wishIns":			param.put("memberIndex", getLoginUser().getMemIndex());
										model.put("returns", stsDao.stsSetWishIns(param));
										break;
										
				case "wishDel":			param.put("kind", "del");
										model.put("returns", stsDao.stsSetWishMod(param));
										break;
			}

			return 0;
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	
	//setFile
    public Returns setFile(Map<String, Object> model, FileUploader fileUploader) throws ServletException, IllegalStateException, IOException {
		String fileDir = "D:/DevJava/updn/";
		
		List<MultipartFile> uploadfiles = fileUploader.getFiles();
		
		//directory
    	File ppdir = new File(fileDir);
		ppdir.mkdirs();
							
		for (MultipartFile multipartFile : uploadfiles) {
			if (multipartFile != null) {
				String fileName = multipartFile.getOriginalFilename();
				String surFix   = fileName.substring(fileName.lastIndexOf("."));
				
				//newFileName
				fileName = UUID.randomUUID().toString() + surFix;
				 	   
				//del
		    	File file = new File(fileDir + fileName);
		    	file.delete();

		    	//upload
				multipartFile.transferTo(new File(fileDir + fileName));

				//variables
				model.put("fileUrl", fileName);
				
				if (logger.isDebugEnabled()) { 
					logger.debug("uploadfile --> {}{}", fileDir, fileName);
				}
			}
		}

		//리턴
		Returns rt = new Returns();
				rt.setCode(1);
				rt.setText("업로드 완료");
				rt.setKeyStr(model.get("fileUrl").toString());
		return  rt;
    }
}

//End Of This



