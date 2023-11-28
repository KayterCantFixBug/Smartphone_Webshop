<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>TechGadget</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">
<link rel="icon"
	href="${pageContext.servletContext.contextPath}/images/icon.png">
<%@ include file="/views/includes/link.jsp"%>
</head>
<body>
	<nav
		class="custom-navbar navbar navbar navbar-expand-md navbar-dark bg-dark"
		arial-label="Furni navigation bar">
		<div class="container">
			<a class="navbar-brand" href="">TechGadget<span>.</span></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarsFurni"
				aria-controls="navbarsFurni" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarsFurni">
				<ul class="custom-navbar-nav navbar-nav ms-auto mb-2 mb-md-0">
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.servletContext.contextPath}/views/home.jsp">Home</a></li>
					<li><a class="nav-link"
						href="${pageContext.servletContext.contextPath}/views/shop.jsp">Shop</a></li>
					<c:if test="${sessionScope.account != null}">
						<li><a class="nav-link"
							href="${pageContext.servletContext.contextPath}/order">Order</a></li>
					</c:if>
				</ul>
				<ul class="custom-navbar-cta navbar-nav mb-2 mb-md-0 ms-5">
					<li><a class="nav-link"
						href="${pageContext.servletContext.contextPath}/views/cart.jsp"><img
							src="${pageContext.servletContext.contextPath}/images/cart.svg"
							style="padding-right: 10px;"><span
							class="badge text-bg-secondary">0</span></a></li>
					<c:choose>
						<c:when test="${sessionScope.account != null}">
							<div style="padding-right: 5px;">
								<a class="btn"
									href="${pageContext.servletContext.contextPath}/profile"><c:if
										test="${sessionScope.account.image != null}">
										<img src="${imageData}" alt="avatar"
											style="padding-right: 5px;" width="30" height="30">
									</c:if>${sessionScope.account.name}</a>
							</div>
							<div>
								<a class="btn"
									href="${pageContext.servletContext.contextPath}/logout">Logout</a>
							</div>
						</c:when>
						<c:otherwise>
							<div style="padding-right: 5px;">
								<a class="btn"
									href="${pageContext.servletContext.contextPath}/login">Login</a>
							</div>
							<div>
								<a class="btn"
									href="${pageContext.servletContext.contextPath}/register">Register</a>
							</div>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>