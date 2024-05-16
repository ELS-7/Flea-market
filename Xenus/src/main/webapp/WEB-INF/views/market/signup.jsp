<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<title>Flea Market</title>
	<jsp:include page="./zHeaderInclude.jsp"/>
	
	<!-- returns -->
	<!-- mav를 리턴( setViewName에 해당하는 web페이지가(view값 :/market/signup.jsp) 로드되고 addObject로 저장한 모델 값()을 key로 읽을 수 있음) -->
	<!-- returns : com.xenus.sts.vo.Returns@19db8d2 형태로 표기 -->
	<!-- 오류 확인후 home으로 보내되, ContextPath가 바뀔수 있기에 model 값 ContextPath로 (서버에서 받아온 ContextPath값) 보냄 -->
	<c:if test="${not empty returns}">
		<script>
			<c:if test="${returns.code eq '0'}">
				alert("중복된 아이디 입니다.")
			</c:if>
				
			<c:if test="${returns.code eq '-1'}">
				alert("DB 입력 오류가 발생했습니다.")
			</c:if>
				
			<c:if test="${returns.code eq '1'}">
				alert("회원 가입이 완료되었습니다.")
				location.href="${ContextPath}/market/home";
			</c:if>
		</script>
	</c:if>
	
	<script>
		function chkForm() {
			var f = document.frm;
			
			if (f.id.value == "") {
			    alert("아이디를 입력해주세요.");
			    f.id.focus();
			    return false;
			}
			if (f.password.value == "") {
			    alert("비밀번호를 입력해주세요.");
			    f.password.focus();
			    return false;
			}
			if (f.confirmPassword.value == "") {
			    alert("비밀번호 재확인을 입력해주세요.");
			    f.confirmPassword.focus();
			    return false;
			}
			if (f.confirmPassword.value != f.password.value) {
			    alert("비밀번호가 재확인과 일치하지 않습니다.");
			    f.password.focus();
			    return false;
			}
			if (f.name.value == "") {
			    alert("이름을 입력해주세요.");
			    f.name.focus();
			    return false;
			}
			if (!document.getElementById('checkbox').checked) {
			    alert("정보 수집에 동의해주세요.");
			    return false;
			}
			
			return true;
		} 
	</script>
</head>

<body>
	<jsp:include page="./header.jsp"/>
	
	 <!-- sign up form -->
	 <section>
		<div id="agileits-sign-in-page" class="sign-in-wrapper">
			<div class="agileinfo_signin">
				<h3>회원가입</h3>
				<form name="frm" action="/market/addMember" method="post" onsubmit="return chkForm()">
					<input type="text"     name="id"        		placeholder="아이디">
					<input type="password" name="password"  		placeholder="비밀번호"> 
					<input type="password" name="confirmPassword" 	placeholder="비밀번호 재확인">  
					<input type="text" 	   name="name" 				placeholder="이름">
					<input type="email"    name="email" 			placeholder="이메일">
					
					<div style="padding:10px; margin:0 0 10px 0">
						<input type="radio" name="memberType" id="type1" value="seller" checked ><label for="type1" style="margin:4px 10px 4px 4px"> Seller</label>
						<input type="radio" name="memberType" id="type2" value="buyer" ><label for="type2" style="margin-left:4px" > Buyer</label>
					</div>
					
					<div class="signin-rit">
						<span class="agree-checkbox">
							<label class="checkbox"><input type="checkbox" id="checkbox" value="">개인정보 수집 및 이용에 동의합니다.</label>
						</span>
					</div>
					<input type="submit" value="가입하기">
				</form>
			</div>
		</div>
	</section>
	<!-- //sign up form -->
	
	<jsp:include page="./footer.jsp"/>
</body>
</html>