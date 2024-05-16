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
		<div class="content-list percent90">
			 <h3 class="head-top">위시리스트<span class="mL4 font13 fR">거래가 확정된 구입요청은 보이지 않습니다.</span></h3>
				
			<div class="scroll-wrap">
				<c:forEach var="data" items="${products}"> 
					<div class="left img cursor" onclick="productView('${data.productIndex}')">
						<img src='${contextPath}/market/downloadFile?fName=${data.image}'>
					</div>
					
					<table class="table right">
						<tr>
							<th>상품명</th>
							<td>${data.productName}</td>
						</tr>
						<tr>
							<th>상품금액</th>
							<td class="blue bold font16">${data.tradingPrice} 원</td>
						</tr>
						<tr>
							<th>판매금액</th>
							<td class="blue bold font16">${data.price} 원</td>
						</tr>
						<tr>
							<th>등록일</th>
							<td>${data.regDate}</td>
						</tr>
						<tr>
							<th>관리</th>
							<td>
								<a class="btn" href="javascript:wishDelete('${data.wishIndex}')">삭제</a>
							</td>
						</tr>		
					</table>
				</c:forEach>
			</div>
    	</div>
    </div>
		
	<jsp:include page="./footer.jsp"/>
	
</body>
</html>