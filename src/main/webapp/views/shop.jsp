<%@ include file="/views/includes/header.jsp"%>
<form class="container" role="search">
	<div class="row justify-content-center pt-2">
		<div class="col-6">
			<input class="form-control me-2" type="search" placeholder="Search"
				aria-label="Search">
		</div>
		<div class="col-auto">
			<button class="btn btn-outline-success" type="submit">Search</button>
		</div>
	</div>
</form>
<div class="untree_co-section product-section before-footer-section">
	<div class="container">
		<div class="row">
			<div class="col-12 col-md-4 col-lg-3 mb-5">
				<a class="product-item" href="#"> <img
					src="${pageContext.servletContext.contextPath}/images/product.png"
					class="img-fluid product-thumbnail" width="100" height="100">
					<h3 class="product-title">Nordic Chair</h3> <strong
					class="product-price">$50.00</strong> <span class="icon-cross">
						<img
						src="${pageContext.servletContext.contextPath}/images/cross.svg"
						class="img-fluid">
				</span>
				</a>
				<div class="text-center pt-4">
					<a href="#" class="btn btn-sm btn-outline-black">Details</a>
				</div>
			</div>
		</div>
	</div>
</div>
<nav aria-label="Page navigation example">
	<ul class="pagination justify-content-center">
		<li class="page-item disabled"><a class="page-link">Previous</a>
		</li>
		<li class="page-item"><a class="page-link" href="#">1</a></li>
		<li class="page-item"><a class="page-link" href="#">2</a></li>
		<li class="page-item"><a class="page-link" href="#">3</a></li>
		<li class="page-item"><a class="page-link" href="#">Next</a></li>
	</ul>
</nav>
<%@ include file="/views/includes/footer.jsp"%>