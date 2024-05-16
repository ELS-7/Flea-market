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
	  		<h3 class="head-top">회원목록</h3>
		
			<div class="scroll-wrap">
			    <table class="table table-responsive">
					<colgroup> 
						<col width="100px">		
					</colgroup>
					
			    	<thead>
						<tr>
							<th>순번</th>
							
							<th>이름</th>
							<th>아이디</th>
							<th>유형</th>
							<th>이메일</th>
							<th>최근 접속일</th>
							
							<th>회원 가입일</th>
							<th>회원 관리</th>
						</tr>
					</thead>
					
			   		<tbody>	
		 
						<c:forEach var="member" items="${memberList}"> 
							<tr>
								<td>${member.no}</td>
								<td>${member.name}</td>
								<td>${member.id}</td>
								<td>${member.memberType}</td>
								<td>${member.email}</td>
								<td>${member.lastLogin}</td>
								<td>${member.regDate}</td>
								<td>
									<a href="/market/member/memberModForm?memIndex=${member.memIndex}"><span class="mR4">수정</span></a>
									<a href="javascript:memberDelete('${member.memIndex}')"><span class="mL4">삭제</span></a>
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