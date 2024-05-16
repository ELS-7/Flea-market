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
			
		  	<h3 class="head-top">거래요청을 거래확정으로 변경<span class="mL4 font13 fR">거래가 확정된 구입요청은 보이지 않습니다.</span></h3>
			
			<div class="scroll-wrap">
				<table class="table table-responsive">
					<colgroup> 
						<col width="100px">		
					</colgroup>
					
			    	<thead>
						<tr>
							<th>순번</th>
							<th>이미지</th>
							
							<th>상품명</th>
							<th>상품금액</th>
							
							<th>구매자</th>
							<th>상품 수령장소</th>
							<th>구매 희망금액</th>
							<th>구매자 연락처</th>
							
							<th>거래관리</th>
						</tr>
					</thead>
					
			   		<tbody>	
						<c:forEach var="data" items="${products}"> 
							<tr>
								<td>${data.no}</td>
								<td class="img cursor" onclick="productView('${data.productIndex}')">
									<img src='${contextPath}/market/downloadFile?fName=${data.image}'>
								</td>
								
								<td>${data.productName}</td>
								<td class="taR">${data.price} 원</td>
								
								<td>${data.buyerName}</td>
								<td>${data.tradingPlace}</td>
								<td class="taR">${data.biddingPrice} 원</td>
								<td>${data.phoneNumber}</td>
								
								<td>
									<a href="javascript:cartSold('${data.cartIndex}')"><span class="mL4">거래확정</span></a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
		    	</table>
			</div>
    	</div>
    </div>
		
	<jsp:include page="./footer.jsp"/>
	
</body>
</html>