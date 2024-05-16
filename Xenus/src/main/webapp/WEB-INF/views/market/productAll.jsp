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
		  	<h3>
		  		<span class="title">전체상품</span>
		  	</h3>
		
			<div class="scroll-wrap">
			    <table class="table table-responsive">
					<colgroup> 
						<col width="60px">
						<col width="180px">	
						<col width="160px">	
						<col width="140px">
							
						<col width="100px">	
						<col width="100px">	
						<col width="*">	
						
						<col width="160px">	
						<col width="120px">	
						<col width="100px">	
						<col width="140px">			
					</colgroup>
					
			    	<thead>
						<tr>
							<th>순번</th>
							<th>이미지</th>
							<th>상품명</th>
							<th>판매자명</th>
							
							<th>상품금액</th>
							<th>판매금액</th>
							<th>검색어</th>
							
							<th>판매자 연락처</th>
							<th>구매희망자 수</th>
							<th>판매상태</th>
							<th>관리</th>
						</tr>
					</thead>
					
			   		<tbody>	
						<c:forEach var="data" items="${products}"> 
							<tr>
								<td>${data.no}</td>
								<td class="img cursor" onclick="productView('${data.productIndex}')">
									<img src='${contextPath}/market/downloadFile?fName=${data.image}'>
								</td>
								
								<td>${data.name}</td>
								<td>${data.sellerName}</td>
								<td class="taR">${data.price} 원</td>
								<td class="taR">${data.tradingPrice} 원</td>
								<td>${data.keywords}</td>
								
								<td>${data.phoneNumber}</td>
								
								<c:if test="${data.status ne 'sold'}">
									<td>${data.bidderCount} 명</td>
								</c:if>
								<c:if test="${data.status eq 'sold'}">
									<td>.</td>
								</c:if>
								
								<c:if test="${data.status ne 'sold'}">
									<td style="color:red !important" class="bold">${data.statusName}</td>
								</c:if>
								
								<c:if test="${data.status eq 'sold'}">
									<td style="color:blue !important" class="bold">${data.statusName}</td>
								</c:if>
								
								<td>
									<c:if test="${data.status ne 'sold'}">
										<a href="/market/product/productModForm?productIndex=${data.productIndex}"><span class="mR4">수정</span></a>
										<a href="javascript:productDelete('${data.productIndex}')"><span class="mL4">삭제</span></a>
									</c:if>
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