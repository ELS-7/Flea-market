<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<!DOCTYPE html>
<html>
<head>
	<title>Flea Market</title>
	<jsp:include page="./zHeaderInclude.jsp"/>
</head>

<body>
	<jsp:include page="./header.jsp"/>
	
	<div class="contant-wrap">
		<div class="content-info w1000">
		  	<h3 class="head-top">판매상품 등록</h3>
		  	
			<c:if test="${productIndex.equals('0')}">
				<!-- 입력 -->
				<form name="frm" id="productIns" method='post' action='/market/product/productIns'>
					<!-- params -->
					<input type="hidden" name="status" value="ready" />						<!-- 판매상태:ready, sold -->
					<input type="hidden" name="regId"  value="${user.id}" />				<!-- 판매자 아이디 -->
					<input type="hidden" name="image"  value="" id="image" />				<!-- 이미지 -->
					<!-- params -->
					
		    		<p>상품명</p>
		    		<input type="text" name="name" />
		    	
		    		<p>상품금액(원)</p>
		    		<input type="text" name="price" maxlength="8"/>
		    	
		    		<p>판매금액(원)</p>
		    		<input type="text" name="tradingPrice" maxlength="8" />
		    	
		    		<p>이미지</p>
		    		<img src="${products[0].image}" id="imageView" class="mB4"/>
		    		<input type="file" id="fileName" />
		    		<input type="button" value="업로드" onclick="uploadImage()" />
		    	
		    		<p>검색어</p>
		    		<input type="text" name="keywords" />
		    	
		    		<p>상품설명 및 거래 장소</p>
		    		<textarea name="description"></textarea>
		    	
		    		<p>판매자 연락처</p>
		    		<input type="text" name="phoneNumber" />
		    	</form>
	    	
		    	<div class="btn-pan">
					<span class="btn btn-cancel w50" onclick="goBack()">취소</span>
					<span class="btn btn-save w50"   onclick="productRegister()">저장</span>
		    	</div>
			</c:if>
		  	
			<c:if test="${!productIndex.equals('0')}">
				<!-- 입력 -->
				<form name="frm" id="productMod1" method='post' action='/market/product/productMod'>
					<!-- params -->
					<input type="hidden" name="productIndex" 	 value="${products[0].productIndex}" />		<!-- 판매자 아이디 -->
					<input type="hidden" name="image11" id="image" value="${products[0].image}" />			<!-- 이미지 -->
					<!-- params -->

		    		<p>상품명</p>
		    		<input type="text" name="name1" value="${products[0].name}" />
		    	
		    		<p>상품금액(원)</p>
		    		<input type="text" name="price" value="${products[0].price}" maxlength="8" />
		    	
		    		<p>판매금액(원)</p>
		    		<input type="text" name="tradingPrice" value="${products[0].tradingPrice}" maxlength="8" />
		    	
		    		<p>이미지</p>
		    		<img src='${contextPath}/market/downloadFile?fName=${products[0].image}' id="imageView" class="mB4"/>
		    		<input type="file" id="fileName" />
		    		<input type="button" value="업로드" onclick="uploadImage()" />
		    	
		    		<p>검색어</p>
		    		<input type="text" name="keywords" value="${products[0].keywords}" />
		    	
		    		<p>상품설명</p>
		    		<textarea name="description">${products[0].descriptionText}</textarea>
		    		
		    		<p>상품번호</p>
		    		<input type="text" name="productNumber" value="${products[0].productNumber}" />
		    	
		    		<p>판매자 연락처</p>
		    		<input type="text" name="phoneNumber" value="${products[0].phoneNumber}" />
		    	</form>
	    	
		    	<div class="btn-pan">
					<span class="btn btn-cancel w50" onclick="goBack()">취소</span>
					<span class="btn btn-save w50"   onclick="productModify()">저장</span>
		    	</div>
			</c:if>
    	</div>
    </div>
		
	<jsp:include page="./footer.jsp"/>
</body>
</html>