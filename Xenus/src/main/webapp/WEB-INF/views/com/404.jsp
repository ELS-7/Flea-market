<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<!-- Page not found -->
	<script>
		var va = 'HTTP Status 404 - Page not found.\n';

		<c:if test="${not empty error}">
			va += '${error}\n';
		</c:if>

		alert(va);

		<c:set value ="${pageContext.servletContext.contextPath}" var="ContextPath" />
		location.href="${ContextPath}/market/home";
	</script>