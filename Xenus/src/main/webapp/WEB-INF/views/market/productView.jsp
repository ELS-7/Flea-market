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
	
	<!-- Slider -->
	<div class="slider">
		<ul class="rslides" id="slider">
			<li>
				<div class="w3ls-slide-text">
					<h3>Sell or Advertise anything online</h3>
					<a href="Catagorty.jsp" class="w3layouts-explore-all">Browse all Categories</a>
				</div>
			</li>
			<li>
				<div class="w3ls-slide-text">
					<h3>Find the Best Deals Here</h3>
					<a href="Catagorty.jsp" class="w3layouts-explore">Explore</a>
				</div>
			</li>
			<li>
				<div class="w3ls-slide-text">
					<h3>Lets build the home of your dreams</h3>
					<a href="Product?p=realstate" class="w3layouts-explore">Explore</a>
				</div>
			</li>
			<li>
				<div class="w3ls-slide-text">
					<h3>Find your dream ride</h3>
					<a href="Product?p=bike" class="w3layouts-explore">Explore</a>
				</div>
			</li>
			<li>
				<div class="w3ls-slide-text">
					<h3>The Easiest Way to get a Job</h3>
					<a href="Product?p=job" class="w3layouts-explore">Find a Job</a>
				</div>
			</li>
		</ul>
	</div>
	<!-- //Slider -->
		
	<div class="main-content">
		<!-- productView -->
		<form name="frm" id="productView" method='post' action='/market/product/productBuy'>	
			<div class="w3-categories contant-info">
				<h3>${products[0].name}</h3>
				
				<div class="container content-info percent90">
					<div class="col-md-5">
						<div class="focus-layout top mT0">
							<img src='${contextPath}/market/downloadFile?fName=${products[0].image}' />
						</div>
											
						<c:if test="${products[0].canBuy eq 'Y' && products[0].canWish eq 'Y'}">
							<span class="btn wishDot" onclick="wishInsert('${products[0].productIndex}')">찜하기</span>
						</c:if>
											
						<c:if test="${products[0].status ne 'sold'}">
							<span class="soldDot">판매중</span>
											
							<c:if test="${products[0].canWish eq 'N'}">
								<span class="soldDot-wish">찜한상품</span>
							</c:if>
											
							<c:if test="${products[0].canBuy eq 'N'}">
								<span class="soldDot-wish">낙찰대기</span>
							</c:if>
						</c:if>
											
						<c:if test="${products[0].status eq 'sold' && products[0].memberType eq 'seller'}">
							<span class="soldDot">판매완료</span>
						</c:if>
											
						<c:if test="${products[0].status eq 'sold' && products[0].memberType eq 'buyer'}">
							<span class="soldDot">구매완료</span>
						</c:if>
					
						<div class="focus-layout desctiption">
							<div class="taL" id="description">
								${products[0].description}
							</div>
						</div>
					</div>
					
					<div class="col-md-7 <c:if test="${products[0].status eq 'sold'}">padT20</c:if>">
						<table class="table">
							<tr>
								<th>상품명</th>
								<td>${products[0].name}</td>
							</tr>
							<tr>
								<th>상품금액</th>
								<td class="red bold">${products[0].price} 원</td>
							</tr>
							<tr>
								<th>판매금액</th>
								<td class="blue bold font16">${products[0].tradingPrice} 원</td>
							</tr>
							<tr>
								<th>판매자</th>
								<td>${products[0].sellerName}</td>
							</tr>
							<tr>
								<th>판매자 연락처</th>
								<td>${products[0].phoneNumber}</td>
							</tr>
							
							<c:if test="${user.memberType != null && user.memberType eq 'buyer' && products[0].status ne 'sold'}">
								<!-- params -->
								<input type="hidden" name="sellerId"     value="${products[0].sellerId}" />			<!-- 판매자 아이디 -->
								<input type="hidden" name="sellerName"   value="${products[0].sellerName}" />		<!-- 판매자 명 -->
								<input type="hidden" name="productIndex" value="${products[0].productIndex}" />		<!-- 상품번호 -->
								<input type="hidden" name="productName"  value="${products[0].productName}" />		<!-- 상품명 -->
								<input type="hidden" name="price"  		 value="${products[0].price}" />			<!-- 상품금액 -->
								
								<input type="hidden" name="buyerId"      value="${user.id}" />						<!-- 구매자 아이디 -->
								<input type="hidden" name="buyerName"    value="${user.name}" />					<!-- 구매자 명 -->
								
								<input type="hidden" name="cartIndex"    value="${products[0].cartIndex}" />		<!-- 구매키-->
								<input type="hidden" name="maxPrice"     value="${products[0].maxPrice}" />			<!-- 최고 구매가-->
								<!-- params -->
			
								<tr>
									<th class="valign">구매 희망가격</th>
									<td>
										<input type="text" name="biddingPrice" maxlength="8" value="${products[0].biddingPrice}" class="mT0 mB0 mR4"/>
										<span>현재 최고 입찰가 : ${products[0].maxPrice} 원</span>
									</td>
								</tr>
			
								<tr>
									<th class="valign">구매자 연락처</th>
									<td>
										<input type="text" name="phoneNumber" class="mT0 mB0"  value="${products[0].buyerPhoneNumber}"/>
									</td>
								</tr>
			
								<tr>
									<th class="valign">상품 수령 장소</th>
									<td>
										<input type="text" name="tradingPlace" class="mT0 mB0"  value="${products[0].tradingPlace}"/>
									</td>
								</tr>
								
								<tr>
									<td colspan="2" style="border:none !important; padding:22px 0px !important;">
										<c:if test="${products[0].canBuy eq 'Y'}">
											<span class="btn" onclick="productBuy('${products[0].productIndex}')">구매하기</span>
										</c:if>
										
										<c:if test="${products[0].canBuy eq 'N'}">
											<span class="btn" onclick="cartModPrice('${products[0].productIndex}')">변경하기</span>
										</c:if>
									</td>
								</tr>
							</c:if>				
						</table>
					</div>
				</div>
			</div>
		</form>
		<!--productView -->	
									
		<div class="trending-ads">
			<div class="container">
			<!-- slider -->
			<div class="agile-trend-ads">
				<h2>Trending Ads</h2>
						<ul id="flexiselDemo3">
							<li>
								<div class="col-md-3 biseller-column">
									<a href="single.html">
										<img src="${resources}/images/p1.jpg" alt="" />
										<span class="price">&#36; 450</span>
									</a> 
									<div class="w3-ad-info">
										<h5>There are many variations of passages</h5>
										<span>1 hour ago</span>
									</div>
								</div>
								<div class="col-md-3 biseller-column">
									<a href="single.html">
										<img src="${resources}/images/p2.jpg" alt="" />
										<span class="price">&#36; 399</span>
									</a> 
									<div class="w3-ad-info">
										<h5>Lorem Ipsum is simply dummy</h5>
										<span>3 hour ago</span>
									</div>
								</div>
								<div class="col-md-3 biseller-column">
									<a href="single.html">
										<img src="${resources}/images/p3.jpg" alt="" />
										<span class="price">&#36; 199</span>
									</a> 
									<div class="w3-ad-info">
										<h5>It is a long established fact that a reader</h5>
										<span>8 hour ago</span>
									</div>
								</div>
								<div class="col-md-3 biseller-column">
									<a href="single.html">
										<img src="${resources}/images/p4.jpg" alt="" />
										<span class="price">&#36; 159</span>
									</a> 
									<div class="w3-ad-info">
										<h5>passage of Lorem Ipsum you need to be</h5>
										<span>19 hour ago</span>
									</div>
								</div>
							</li>
							<li>
								<div class="col-md-3 biseller-column">
									<a href="single.html">
										<img src="${resources}/images/p5.jpg" alt="" />
										<span class="price">&#36; 1599</span>
									</a> 
									<div class="w3-ad-info">
										<h5>There are many variations of passages</h5>
										<span>1 hour ago</span>
									</div>
								</div>
								<div class="col-md-3 biseller-column">
									<a href="single.html">
										<img src="${resources}/images/p6.jpg" alt="" />
										<span class="price">&#36; 1099</span>
									</a> 
									<div class="w3-ad-info">
										<h5>passage of Lorem Ipsum you need to be</h5>
										<span>1 day ago</span>
									</div>
								</div>
								<div class="col-md-3 biseller-column">
									<a href="single.html">
										<img src="${resources}/images/p7.jpg" alt="" />
										<span class="price">&#36; 109</span>
									</a> 
									<div class="w3-ad-info">
										<h5>It is a long established fact that a reader</h5>
										<span>9 hour ago</span>
									</div>
								</div>
								<div class="col-md-3 biseller-column">
									<a href="single.html">
										<img src="${resources}/images/p8.jpg" alt="" />
										<span class="price">&#36; 189</span>
									</a> 
									<div class="w3-ad-info">
										<h5>Lorem Ipsum is simply dummy</h5>
										<span>3 hour ago</span>
									</div>
								</div>
							</li>
							<li>
								<div class="col-md-3 biseller-column">
									<a href="single.html">
										<img src="${resources}/images/p9.jpg" alt="" />
										<span class="price">&#36; 2599</span>
									</a> 
									<div class="w3-ad-info">
										<h5>Lorem Ipsum is simply dummy</h5>
										<span>3 hour ago</span>
									</div>
								</div>
								<div class="col-md-3 biseller-column">
									<a href="single.html">
										<img src="${resources}/images/p10.jpg" alt="" />
										<span class="price">&#36; 3999</span>
									</a> 
									<div class="w3-ad-info">
										<h5>It is a long established fact that a reader</h5>
										<span>9 hour ago</span>
									</div>
								</div>
								<div class="col-md-3 biseller-column">
									<a href="single.html">
										<img src="${resources}/images/p11.jpg" alt="" />
										<span class="price">&#36; 2699</span>
									</a> 
									<div class="w3-ad-info">
										<h5>passage of Lorem Ipsum you need to be</h5>
										<span>1 day ago</span>
									</div>
								</div>
								<div class="col-md-3 biseller-column">
									<a href="single.html">
										<img src="${resources}/images/p12.jpg" alt="" />
										<span class="price">&#36; 899</span>
									</a> 
									<div class="w3-ad-info">
										<h5>There are many variations of passages</h5>
										<span>1 hour ago</span>
									</div>
								</div>
							</li>
					</ul>
				</div>   
			</div>		
		</div>
			
		<!--partners-->
		<div class="w3layouts-partners">
			<h3>Our Partners</h3>
			<div class="container">
				<ul>
					<li><a href="#"><img class="img-responsive" src="${resources}/images/p-1.png" alt=""></a></li>
					<li><a href="#"><img class="img-responsive" src="${resources}/images/p-2.png" alt=""></a></li>
					<li><a href="#"><img class="img-responsive" src="${resources}/images/p-3.png" alt=""></a></li>
					<li><a href="#"><img class="img-responsive" src="${resources}/images/p-4.png" alt=""></a></li>
					<li><a href="#"><img class="img-responsive" src="${resources}/images/p-5.png" alt=""></a></li>
					<li><a href="#"><img class="img-responsive" src="${resources}/images/p-6.png" alt=""></a></li>
					<li><a href="#"><img class="img-responsive" src="${resources}/images/p-7.png" alt=""></a></li>
					<li><a href="#"><img class="img-responsive" src="${resources}/images/p-8.png" alt=""></a></li>
					<li><a href="#"><img class="img-responsive" src="${resources}/images/p-9.png" alt=""></a></li>
					<li><a href="#"><img class="img-responsive" src="${resources}/images/p-10.png" alt=""></a></li>	
				</ul>
			</div>
		</div>	
		<!--//partners-->	
	</div>
		
	<jsp:include page="./footer.jsp"/>

	<script src="${resources}/js/responsiveslides.min.js"></script>
	<script src="${resources}/js/jquery.flexisel.js"></script>
	<script src="${resources}/js/move-top.js"></script>
	<script src="${resources}/js/easing.js"></script>

	<!-- flexisel-js -->
	<script type="text/javascript">
		$(window).load(function() {
			$("#flexiselDemo3").flexisel({
				visibleItems : 1,
				animationSpeed : 1000,
				autoPlay : true,
				autoPlaySpeed : 5000,
				pauseOnHover : true,
				enableResponsiveBreakpoints : true,
				responsiveBreakpoints : {
					portrait : {
						changePoint : 480,
						visibleItems : 1
					},
					landscape : {
						changePoint : 640,
						visibleItems : 1
					},
					tablet : {
						changePoint : 768,
						visibleItems : 1
					}
				}
			});

		});
	</script>

	<!-- Slider-JavaScript -->
	<script>
		$(function() {
			$("#slider").responsiveSlides({
				auto : true,
				pager : false,
				nav : true,
				speed : 500,
				maxwidth : 800,
				namespace : "large-btns"
			});

		});
	</script>

</body>		
</html>