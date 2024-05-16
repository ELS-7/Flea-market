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
						<h3>Notebook</h3>
						<a href="javascript:findProduct('노트북')" class="w3layouts-explore">Browse all Notebook</a>
					</div>
				</li>
				<li>
					<div class="w3ls-slide-text">
						<h3>Women's clothes</h3>
						<a href="javascript:findProduct('여성옷')" class="w3layouts-explore">Browse all for Women</a>
					</div>
				</li>
				<li>
					<div class="w3ls-slide-text">
						<h3>Children's clothes</h3>
						<a href="javascript:findProduct('아동')" class="w3layouts-explore">Browse all for Children</a>
					</div>
				</li>
				<li>
					<div class="w3ls-slide-text">
						<h3>Car</h3>
						<a href="javascript:findProduct('자동차')" class="w3layouts-explore">Browse all Car</a>
					</div>
				</li>
				<li>
					<div class="w3ls-slide-text">
						<h3>Dayday Fall New Blouse</h3>
						<a href="javascript:findProduct('블라우스')" class="w3layouts-explore">Browse all Blouse</a>
					</div>
				</li>
			</ul>
		</div>
		<!-- //Slider -->
		
		<!-- content-starts-here -->
		<div class="main-content">
			<!-- products -->
			<div class="w3l-popular-ads">  
				<h3>Product for market</h3>
				 <div class="w3l-popular-ads-info">
				 
					<c:forEach var="data" items="${products}"> 
						<div class="col-md-4 w3ls-portfolio-left">
							<div class="portfolio-img event-img">
								<img src="${contextPath}/market/downloadFile?fName=${data.image}" class="img-responsive"/>
								<div class="over-image"></div>
							</div>
							<div class="portfolio-description">
							   	<h4 class="font16 bold white">${data.name}</h4>
							   	<p  class="font16 white" ><span class="gray font13 mR4">구입가</span>${data.price}</p>
							   	<p  class="font16 orange"><span class="gray font13 mR4">판매가</span>${data.tradingPrice}</p>
							   	
								<c:if test="${data.status ne 'sold'}">
									<a href="javascript:productView('${data.productIndex}')">
										<span>Buy</span>
									</a>
								</c:if>
							   	
								<c:if test="${data.status eq 'sold'}">
									<a href="javascript:alert('판매완료 상품입니다.')">
										<span class="sold">Sold</span>
									</a>
								</c:if>
								
							</div>
							<div class="clearfix"> </div>
						</div>
					</c:forEach>
					
					<div class="clearfix"> </div>
				 </div>
			 </div>
			<!-- products -->	
											
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
			<!-- //slider -->				
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