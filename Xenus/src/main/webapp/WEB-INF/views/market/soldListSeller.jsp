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
	
	  		<h3 class="head-top">판매완료</h3>
	
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
								<th>판매금액</th>
								
								<th>구매자</th>
								<th>구매자 연락처</th>
								<th>판매장소</th>
								<th>판매일</th>
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
									<td>${data.price} 원</td>
									<td>${data.biddingPrice} 원</td>
									
									<td>${data.buyerName}</td>
									<td>${data.phoneNumber}</td>
									<td>${data.tradingPlace}</td>
									<td>${data.regDate}</td>
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