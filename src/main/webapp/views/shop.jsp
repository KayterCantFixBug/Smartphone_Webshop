<%@ include file="/views/includes/header.jsp"%>
<div class="untree_co-section product-section before-footer-section">
	<form action="shop" method="post">
		<div class="container">
			<div class="row mb-5">
				<div class="container" role="search">
					<div class="row justify-content-center pt-2">
						<div class="col-6">
							<input class="form-control me-2" type="search"
								placeholder="Search" name="search" aria-label="Search">
						</div>
						<div class="col-auto">
							<button class="btn btn-outline-success" type="submit">Search</button>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<c:forEach var="product" items="${listProduct}">
					<div class="col-12 col-md-4 col-lg-3 mb-5">
						<a class="product-item" href="#"> <img
							src="${pageContext.servletContext.contextPath}/images/product.png"
							class="img-fluid product-thumbnail" width="100" height="100">
							<h3 class="product-title">${product.name }</h3> <strong
							class="product-price">$${product.price}</strong> <span
							class="icon-cross"> <img
								src="${pageContext.servletContext.contextPath}/images/cross.svg"
								class="img-fluid">
						</span>
						</a>
						<div class="text-center pt-4">
							<a href="#" class="btn btn-sm btn-outline-black">Details</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<li class="page-item"><a class="page-link" href="#">Previous</a></li>
				<c:forEach var="pageNumber" begin="1" end="${numberOfPages}">
					<li class="page-item"><input name="page" class="page-link"
						type="submit" value="${pageNumber}" /></li>
				</c:forEach>
				<li class="page-item"><a class="page-link" href="#">Next</a></li>
			</ul>
		</nav>
	</form>
</div>
<%@ include file="/views/includes/footer.jsp"%>