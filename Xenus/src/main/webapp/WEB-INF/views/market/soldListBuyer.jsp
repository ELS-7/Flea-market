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
		
		  	<h3 class="head-top">구매상품</h3>
	
			<div class="scroll-wrap">
				<c:forEach var="data" items="${products}"> 
					<div class="left img cursor" onclick="productView('${data.productIndex}')">
						<img src='${contextPath}/market/downloadFile?fName=${data.image}'>
					</div>
					
					<table class="table right">
						<tr>
							<th>판매자</th>
							<td>${data.sellerName}</td>
						</tr>
						<tr>
							<th>상품명</th>
							<td>${data.productName} 원</td>
						</tr>
						<tr>
							<th>상품금액</th>
							<td>${data.price} 원</td>
						</tr>
						<tr>
							<th>구입금액</th>
							<td>${data.biddingPrice} 원</td>
						</tr>
						<tr>
							<th>구입일</th>
							<td>${data.regDate}</td>
						</tr>	
					</table>
				</c:forEach>
			</div>
    	</div>
    </div>
		
	<jsp:include page="./footer.jsp"/>
	
</body>
</html>