<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Admin - TechGadget</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">
<link rel="icon" href="${pageContext.servletContext.contextPath}/images/icon.png">
<%@ include file="/views/admin/includes/link.jsp"%>
</head>
<body>
	<div class="container">
		<nav>
			<ul>
				<li><a href="" class="logo"> <img
						src="${pageContext.servletContext.contextPath}/images/icon.png"
						alt=""> <span class="nav-item">TechGadget</span>
				</a></li>
				<c:if test="${sessionScope.account != null}">
					<li><p>
							<span class="nav-item">Admin: <b>${sessionScope.account.name}</b></span>
						</p>
						<br></li>
				</c:if>
				<li><a href="home.jsp"> <i class="fas fa-home"></i> <span
						class="nav-item">Home</span>
				</a></li>
				<li><a href="user-list.jsp"> <i class="fas fa-user"></i> <span
						class="nav-item">User</span>
				</a></li>
				<li><a href="product-list.jsp"> <i class="fas fa-mobile"></i>
						<span class="nav-item">Product</span>
				</a></li>
				<li><a href="order-list.jsp"> <i
						class="fas fa-shopping-cart"></i> <span class="nav-item">Order</span>
				</a></li>
				<li><a href="${pageContext.servletContext.contextPath}/logout"
					class="logout"> <i class="fas fa-sign-out-alt"></i> <span
						class="nav-item">Log out</span>
				</a></li>
			</ul>
		</nav>