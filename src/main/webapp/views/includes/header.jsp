<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>TechGadget</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
	integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
	integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
	integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT"
	crossorigin="anonymous"></script>
</head>

<body>
	<nav class="navbar navbar-expand-lg" style="background-color: #e3f2fd;">
		<div class="container-fluid">
			<a class="navbar-brand"> <img
				src="${pageContext.servletContext.contextPath}/images/icon.png"
				alt="Logo" width="30" height="30"
				class="d-inline-block align-text-middle"> <b>TechGadget</b>
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="${pageContext.servletContext.contextPath}/views/home.jsp">Home</a>
					</li>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="${pageContext.servletContext.contextPath}/views/cart.jsp">Cart</a>
					</li>
				</ul>
				<c:choose>
					<c:when test="${sessionScope.account != null}">
						<div class="nav-item dropdown p-2">
							<a class="nav-link dropdown-toggle" href="#" role="button"
								data-bs-toggle="dropdown" aria-expanded="false"><b>${sessionScope.account.name}</b>
							</a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="#">My Profile</a></li>
								<li><a class="dropdown-item"
									href="${pageContext.servletContext.contextPath}/logout">Logout</a></li>
							</ul>
						</div>
					</c:when>
					<c:otherwise>
						<div class="p-2">
							<a class="nav-link active"
								href="${pageContext.servletContext.contextPath}/views/home/login.jsp">Login</a>
						</div>
						<a class="btn btn-primary"
							href="${pageContext.servletContext.contextPath}/views/home/register.jsp"
							role="button">Register</a>
					</c:otherwise>
				</c:choose>
				<form class="d-flex p-2" role="search">
					<input class="form-control me-2" type="search"
						placeholder="Searching..." aria-label="Search">
					<button class="btn btn-outline-success" type="submit">Search</button>
				</form>
			</div>
		</div>
	</nav>