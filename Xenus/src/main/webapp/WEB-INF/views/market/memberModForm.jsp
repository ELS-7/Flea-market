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
		<div class="content-info">
		  	<h3 class="head-top">회원 정보 수정</h3>
		  	<!-- marketController RequestMaaping을 부르는 post 방식 -->
			<form name="frm" id="memberMod" method='post' action='/market/member/memberMod'>
				<!-- params -->
				<input type="hidden" name="memIndex" id="memIndex" value="${members[0].memIndex}" />
				<!-- params -->
				
	    		<p>이름</p>
	    		<input type="text" name="name" value="${members[0].name}" />
	    	
	    		<p>아이디</p>
	    		<input type="text" name="id" value="${members[0].id}" />
	    	
	    		<p>유형</p>
	    		<select id="memberType" name="memberType">
					<option value="admin"  <c:if test="${members[0].memberType eq 'admin'  }">selected</c:if>>관리자</option>
					<option value="seller" <c:if test="${members[0].memberType eq 'seller' }">selected</c:if>>판매자</option>
					<option value="buyer"  <c:if test="${members[0].memberType eq 'buyer'  }">selected</c:if>>구매자</option>
				</select>
	    	
	    		<p>이메일</p>
	    		<input type="text" name="email" value="${members[0].email}" />
	    	</form>
	    	
	    	<div class="btn-pan">
				<span class="btn btn-cancel w50" onclick="goBack()">취소</span>
				<span class="btn btn-save w50"   onclick="memberModify()">저장</span>
	    	</div>
    	</div>
    </div>
		
	<jsp:include page="./footer.jsp"/>
</body>
</html>