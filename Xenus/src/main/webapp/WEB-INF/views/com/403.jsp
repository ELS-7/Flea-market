<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<!-- access denied -->
	<script>
		var va = 'HTTP Status 403 - Access is denied.\n\n' +
				 'You do not have permission to access this page!\n';

		<c:if test="${not empty error}">
			va += '${error}\n';
		</c:if>

		alert(va);

		<c:set value ="${pageContext.servletContext.contextPath}" var="ContextPath" />
		location.href="${ContextPath}/market/home";
	</script>