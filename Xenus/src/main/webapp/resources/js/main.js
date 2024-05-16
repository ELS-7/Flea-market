/*main.js*/

function openMenu() {
	$('body').addClass('show-menu');
}

function closeMenu() {
	$('body').removeClass('show-menu');
}

function goBack() {
	window.history.back();
}

function memberModify() {		 
	var form = $('#memberMod');
	
	if (!confirm("수정합니다.")) {
		 return;
	}

	form.ajaxSubmit({
		success : function(data) {
	 		alert(data.text);
	 		
			if (data.code < 0)
		 		window.location.href = zcontextPath + '/member/memberModForm?memIndex=' + $('#memIndex').val();
		 	else
		 		window.location.href = zcontextPath + '/market/memberList';
		},
		error : function(error) {
			alert('Ajax Error.');
		}
	});
}

/* 학습용
function memberModForm(memIndex) {
	window.location.href = zcontextPath + '/market/member/memberModForm?memIndex=' + memIndex;
}
*/

function memberDelete(memIndex) {
	if (confirm("삭제합니다.")) {
		window.location.href = zcontextPath + '/market/member/memberDel?memIndex=' + memIndex;
	}
}

function checkValid() {
	if (document.frm.name.value == '') {
		alert('상품명을 입력하세요.');
		document.frm.name.focus();
		return false;
	}
	
	if (document.frm.price.value == '') {
		alert('상품금액을 입력하세요.');
		document.frm.price.focus();
		return false;
	}
	
	if (document.frm.tradingPrice.value == '') {
		alert('판매금액을 입력하세요.');
		document.frm.tradingPrice.focus();
		return false;
	}
	
	if (document.frm.image.value == '') {
		alert('이미지를 업로드 하세요.');
		return false;
	}
	
	if (document.frm.keywords.value == '') {
		alert('검색어를 입력하세요.');
		document.frm.keywords.focus();
		return false;
	}
	
	if (document.frm.productNumber.value == '') {
		alert('제품 번호를 입력하세요.');
		document.frm.productNumber.focus();
		return false;
	}
	
	if (document.frm.phoneNumber.value == '') {
		alert('판매자 연락처를 입력하세요.');
		document.frm.phoneNumber.focus();
		return false;
	}
	
	return true;
}

function checkBuy() {
	if (document.frm.biddingPrice.value == '') {
		alert('구매 희망가격을 입력하세요.');
		document.frm.biddingPrice.focus();
		return false;
	}
	
	if (document.frm.biddingPrice.value < document.frm.maxPrice.value) {
		alert('현재 최고 입찰가보다 큰 금액이여야 합니다.');
		document.frm.biddingPrice.focus();
		return false;
	}
	
	if (document.frm.tradingPlace.value == '') {
		alert('상품 수령 장소를 입력하세요.');
		document.frm.tradingPlace.focus();
		return false;
	}
	
	if (document.frm.phoneNumber.value == '') {
		alert('구매자 연락처을 입력하세요.');
		document.frm.phoneNumber.focus();
		return false;
	}
	
	return true;
}

function productRegister() {
	var form = $('#productIns');
	
	if (!checkValid()) {
		 return;
	}
	
	if (!confirm("등록합니다.")) {
		 return;
	}

	form.ajaxSubmit({
		success : function(data) {
	 		alert(data.text);
	 		
			if (data.code > 0) {
				window.location.href = zcontextPath + '/market/productList';
			}
		},
		error : function(error) {
			alert('Ajax Error.');
		}
	});
}

function productModify() {
	//1번 웹페이지 productModForm.jsp의 id가 productMod인 form을 가져옴
	var form = $('#productMod1');
	
	//2번 빈칸있나 확인
	if (!checkValid()) {
		 return;
	}
	
	if (!confirm("수정합니다.")) {
		 return;
	}
	
	//.jsp로 받은 form의 name에 해당하는 파라미터를 action(action='/market/product/productMod')을 호출하면서 MarketController.java로 전달함 -> java작동 시작
	form.ajaxSubmit({
		success : function(data) {
	 		alert(data.text);
	 		
			if (data.code > 0) {
				window.location.href = zcontextPath + '/market/productList';
			}
		},
		error : function(error) {
			alert('Ajax Error.');
		}
	});
}

function productDelete(productIndex) {
	if (confirm("삭제합니다.")) {
		window.location.href = zcontextPath + '/market/product/productDel?productIndex=' + productIndex;
	}
}

function productView(productIndex) {
	window.location.href = zcontextPath + '/market/productView?productIndex=' + productIndex;
}

function productBuy(productIndex) {
	$('#productView').attr('action','/market/product/productBuy');
	
	var form = $('#productView');
	
	if (!checkBuy()) {
		 return;
	}
	
	if (!confirm("구매합니다.")) {
		 return;
	}

	form.ajaxSubmit({
		success : function(data) {
	 		alert(data.text);
	 		
			if (data.code > 0) {
				window.location.href = zcontextPath + '/market/home';
			}
		},
		error : function(error) {
			alert('Ajax Error.');
		}
	});
}

function cartModPrice(productIndex) {
	$('#productView').attr('action','/market/cart/cartModPrice');
	
	var form = $('#productView');
	
	if (!checkBuy()) {
		 return;
	}
	
	if (!confirm("변경합니다.")) {
		 return;
	}

	form.ajaxSubmit({
		success : function(data) {
	 		alert(data.text);
	 		
			if (data.code > 0) {
				productView(productIndex);
			}
		},
		error : function(error) {
			alert('Ajax Error.');
		}
	});
}

function cartDelete(cartIndex) {
	if (confirm("삭제합니다.")) {
		window.location.href = zcontextPath + '/market/cart/cartDel?cartIndex=' + cartIndex;
	}
}

function cartSold(cartIndex) {
	if (confirm("거래 확정 처리합니다.")) {
		window.location.href = zcontextPath + '/market/cart/cartSold?cartIndex=' + cartIndex;
	}
}

function wishInsert(productIndex) {
	$('#productView').attr('action','/market/wish/wishIns');
	
	var form = $('#productView');
	
	if (!confirm("찜합니다.")) {
		 return;
	}

	form.ajaxSubmit({
		success : function(data) {
	 		alert(data.text);
	 		
			if (data.code > 0) {
				productView(productIndex);
			}
		},
		error : function(error) {
			alert('Ajax Error.');
		}
	});
}

function wishDelete(wishIndex) {
	if (confirm("삭제합니다.")) {
		window.location.href = zcontextPath + '/market/wish/wishDel?wishIndex=' + wishIndex;
	}
}

function findProduct(findStr) {
	document.frmSearch.findStr.value = findStr;
	document.frmSearch.submit();
}

function uploadImage() {
	if ($('#fileName')[0].files[0] == null) {
		alert('업로드 파일을 선택하세요.');
		return;
	}
	
	var formData = new FormData();
		formData.append('files', $('#fileName')[0].files[0]);
	
	$.ajax({
			url: zcontextPath + '/market/product/uploadFile',
			data: formData,
			processData: false,
			contentType: false,
			type: 'POST',
			success: function(data) {
			 	if (data.code < 0) {
			 		alert(data.text);
			 	} else {
			 		$('#image').val(data.keyStr);
			 		$('#imageView').attr('src', zcontextPath + '/market/downloadFile?fName=' + data.keyStr);
			 	}
 			},
			error:  function(request, status, error) {
				alert('Ajax Error.');
			}
	});
}

/*main.js*/

