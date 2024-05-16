<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<title>Staff for CamSchool</title>
	<meta name="description" content="Staff page for camschool">
	<c:set value="${pageContext.servletContext.contextPath}" var="ContextPath" />
	<script> 
		location.href="${ContextPath}/com/login";
	</script>
</head>