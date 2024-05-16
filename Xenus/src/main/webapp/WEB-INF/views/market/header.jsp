<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Navigation -->
<div class="agiletopbar">
	<div class="wthreenavigation">
		<div class="menu-wrap">
		<nav class="menu">
			<div class="icon-list">
			
				<c:if test="${user eq null}">
					<a href="/com/login"><i class="fa fa-fw fa-user"></i><span>로그인</span></a>
					<hr>
				</c:if>
			
				<c:if test="${user.memberType eq 'admin'}">
					<a href="/market/memberList"><i class="fa fa-fw fa-user"></i><span>회원관리</span></a>
					<a href="/market/productAll"><i class="fa fa-fw fa-suitcase"></i><span>전체상품</span></a>
					<a href="/market/productSold"><i class="fa fa-fw fa-suitcase"></i><span>판매된상품</span></a>
					<a href="/market/productUnSold"><i class="fa fa-fw fa-suitcase"></i><span>판매중인 상품</span></a>
				</c:if>
				
				<c:if test="${user.memberType eq 'seller'}">
					<a href="/market/productList"><i class="fa fa-fw fa-suitcase"></i><span>판매상품</span></a>
					<a href="/market/cartList"><i class="fa fa-fw fa-cart-plus"></i><span>거래처리</span></a>
					<a href="/market/soldList"><i class="fa fa-fw fa-cart-arrow-down"></i><span>판매완료</span></a>
				</c:if>
				
				<c:if test="${user.memberType eq 'buyer'}">
					<a href="/market/wishList"><i class="fa fa-fw fa-bell"></i><span>위시리스트</span></a>
					<a href="/market/cartList"><i class="fa fa-fw fa-cart-plus"></i><span>장바구니</span></a>
					<a href="/market/soldList"><i class="fa fa-fw fa-cart-arrow-down"></i><span>구매상품</span></a>
				</c:if>
				
				<c:if test="${user ne null}">
					<hr>
					<a href="/com/logout"><i class="fa fa-fw fa-sign-out"></i><span>로그아웃</span></a>
				</c:if>
				
			</div>
		</nav>
		<button class="close-button" id="close-button" onclick="closeMenu()">Close Menu</button>
	</div>
	<button class="menu-button" id="open-button" onclick="openMenu()"> </button>
	</div>
	<div class="clearfix"></div>
</div>
<!-- //Navigation -->


<!-- header -->
<header>
	<div class="w3ls-header"><!--header-one--> 
		<div class="w3ls-header-left">
			<p><a href="/market/home"><i class="fa fa-home" aria-hidden="true"></i> Home</a></p>
		</div>
		
		<div class="w3ls-header-right">
			<ul>
				<c:if test="${user.memIndex eq null}">
					<li class="dropdown head-dpdn">
						<a href="/com/login" aria-expanded="false"><i class="fa fa-user" aria-hidden="true"></i> Log In</a>
					</li>
				</c:if>
				
				<c:if test="${user.memIndex ne null}">
				
					<c:if test="${user.memberType eq 'buyer'}">
						<li class="dropdown head-dpdn">
							<a href="/market/wishList" aria-expanded="false"><i class="fa fa-bell" aria-hidden="true"></i>위시리스트</a>
						</li>
						<li class="dropdown head-dpdn">
							<a href="/market/cartList" aria-expanded="false"><i class="fa fa-shopping-cart" aria-hidden="true"></i>장바구니</a>
						</li>
					</c:if>
				
					<li class="dropdown head-dpdn">
						<a href="#" aria-expanded="false"><i class="fa fa-user" aria-hidden="true"></i>${user.name}</a>
					</li>
					
					<li class="dropdown head-dpdn">
						<a href="/com/logout"><i class="fa fa-sign-out" aria-hidden="true"></i>Logout</a>
					</li>
				</c:if>
				
				<li class="dropdown head-dpdn">
					<a href="#"><i class="fa fa-question-circle" aria-hidden="true"></i> Help</a>
				</li>
			</ul>
		</div>
		
		<div class="clearfix"> </div> 
	</div>
	
	<div class="container">
		<div class="agile-its-header">
			<div class="logo">
				<h1><a href="#"><span>Flea</span>Market</a></h1>
			</div>
			
			<div class="agileits_search">
			
				<form name="frmSearch" action="/market/home" method="post">
					<input name="findStr" type="text">
					
					<select id="agileinfo_search" name="findSel">
						<option value="">All Categories</option>
						<option value="seller">Seller Name</option>
						<option value="product">Product Name</option>
						<option value="price">Hoping Price</option>
					</select>
					
					<button type="submit" class="btn btn-default" aria-label="Left Align">
						<i class="fa fa-search" aria-hidden="true"> </i>
					</button>
				</form>
				
			</div>	
			<div class="clearfix"></div>
		</div>
	</div>
</header>
<!-- //header -->

