<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<title>Flea Market</title>
	
	<jsp:include page="./zHeaderInclude.jsp"/>
</head>

<body>
	<jsp:include page="./header.jsp"/>
	
	<!-- sign in form -->
	 <section>
		<div id="agileits-sign-in-page" class="sign-in-wrapper">
			<div class="agileinfo_signin">
				<h3>Sign In</h3>
			
				<form action="/login" method="post">
					<input type="text" 	   name="username" placeholder="Username" required=""> 
					<input type="password" name="password" placeholder="Password" required=""> 
					<input type="submit"   value="submit" onsubmit="chkForm()" >
				</form>
				
				<div class="forgot-grid">
					<label class="checkbox"><input type="checkbox" name="checkbox">Remember me</label>
					<div class="forgot">
						<a href="#" data-toggle="modal" data-target="#myModal2">Forgot Password?</a>
					</div>
					<!-- Modal -->
					<div class="modal fade" id="myModal2" role="dialog">
						<div class="modal-dialog">
						<!-- Modal content-->
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h3 class="w3ls-password">Get Password</h3>		
									<p class="get-pw">Enter your email address below and we'll send you an email with instructions.</p>
									<form action="#" method="post">
										<input type="text" class="user" name="email" placeholder="Email" required="">
										<input type="submit" value="Submit">
									</form>
								</div>
							</div>
						</div>
						</div>
					</div>
					<div class="clearfix"> </div>
				</div>
				
				<h6> Not a Member Yet? <a href="/market/signup">Sign Up Now</a> </h6>
			</div>
		</div>
	</section>
	<!-- //sign in form -->
	
	<jsp:include page="./footer.jsp"/>
</body>
</html>