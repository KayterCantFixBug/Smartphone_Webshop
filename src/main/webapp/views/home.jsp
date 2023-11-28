<%@ include file="/views/includes/header.jsp"%>
<div class="hero">
	<div class="container">
		<div class="row justify-content-between">
			<div class="col-lg-5">
				<div class="intro-excerpt">
					<h1>
						Modern Device <span clsas="d-block">Design Studio</span>
					</h1>
					<p class="mb-4">Devices for communication, assistance, and
						entertainment. With top-notch connectivity and diverse apps, they
						redefine convenience and productivity in everyday life.</p>
					<p>
						<a href="${pageContext.servletContext.contextPath}/views/shop.jsp"
							class="btn btn-secondary me-2">Shop Now</a>
					</p>
				</div>
			</div>
			<div class="col-lg-7">
				<div class="hero-img-wrap">
					<img
						src="${pageContext.servletContext.contextPath}/images/head.png"
						class="img-fluid">
				</div>
			</div>
		</div>
	</div>
</div>

<div class="why-choose-section">
	<div class="container">
		<div class="row justify-content-between">
			<div class="col-lg-6">
				<h2 class="section-title">Why Choose Us</h2>
				<p>We offer top-notch devices, a wide selection, and seamless
					shopping, ensuring convenience and quality in every purchase.</p>

				<div class="row my-5">
					<div class="col-6 col-md-6">
						<div class="feature">
							<div class="icon">
								<img
									src="${pageContext.servletContext.contextPath}/images/truck.svg"
									alt="Image" class="imf-fluid">
							</div>
							<h3>Fast &amp; Free Shipping</h3>
							<p>Get top-tier devices delivered to your doorstep without
								extra costs, ensuring convenience and value in every order.</p>
						</div>
					</div>

					<div class="col-6 col-md-6">
						<div class="feature">
							<div class="icon">
								<img
									src="${pageContext.servletContext.contextPath}/images/bag.svg"
									alt="Image" class="imf-fluid">
							</div>
							<h3>Easy to Shop</h3>
							<p>Our user-friendly interface and streamlined process make
								finding and purchasing your perfect device effortless and
								enjoyable.</p>
						</div>
					</div>

					<div class="col-6 col-md-6">
						<div class="feature">
							<div class="icon">
								<img
									src="${pageContext.servletContext.contextPath}/images/support.svg"
									alt="Image" class="imf-fluid">
							</div>
							<h3>24/7 Support</h3>
							<p>Our dedicated team ensures you receive assistance and
								guidance anytime, making your shopping experience seamless and
								worry-free.</p>
						</div>
					</div>

					<div class="col-6 col-md-6">
						<div class="feature">
							<div class="icon">
								<img
									src="${pageContext.servletContext.contextPath}/images/return.svg"
									alt="Image" class="imf-fluid">
							</div>
							<h3>Hassle Free Returns</h3>
							<p>If your smartphone purchase doesn't meet expectations, our
								easy return process ensures a smooth and convenient experience,
								prioritizing customer satisfaction.</p>
						</div>
					</div>

				</div>
			</div>
			<div class="col-lg-5">
				<div class="img-wrap">
					<img
						src="${pageContext.servletContext.contextPath}/images/why-choose-us-img.jpg"
						alt="Image" class="img-fluid">
				</div>
			</div>
		</div>
	</div>
</div>

<div class="untree_co-section">
	<div class="container">
		<div class="row mb-5">
			<div class="col-lg-5 mx-auto text-center">
				<h2 class="section-title">Our Team</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-4">
				<img
					src="${pageContext.servletContext.contextPath}/images/trong.jpg"
					class="img-fluid mb-5">
				<h3 clas>
					<a href="https://web.facebook.com/profile.php?id=100010912861774"
						target="_blank"><span class="">Kien</span> Duc Trong</a>
				</h3>
				<span class="d-block position mb-4">CEO, Founder.</span>
				<p>
					Why did the smartphone go to therapy?<br>Because it had too
					many "app"rehensions about its future!
				</p>
				<p class="mb-0">
					<a href="https://web.facebook.com/profile.php?id=100010912861774"
						target="_blank" class="more dark">Learn More <span
						class="icon-arrow_forward"></span></a>
				</p>
			</div>
			<div class="col-4">
				<img
					src="${pageContext.servletContext.contextPath}/images/trung.jpg"
					class="img-fluid mb-5">

				<h3 clas>
					<a href="https://web.facebook.com/vantrungbusiness" target="_blank"><span
						class="">Nguyen Tran</span><br> Van Trung</a>
				</h3>
				<span class="d-block position mb-4">CEO, Co-Founder.</span>
				<p>
					The smartphone break up with its charger.<br>It couldn't
					handle the "current" relationship!
				</p>
				<p class="mb-0">
					<a href="https://web.facebook.com/vantrungbusiness" target="_blank"
						class="more dark">Learn More <span class="icon-arrow_forward"></span></a>
				</p>

			</div>
			<div class="col-4">
				<img src="${pageContext.servletContext.contextPath}/images/duy.jpg"
					class="img-fluid mb-5">
				<h3 clas>
					<a href="#"><span class="">Ngo Dinh</span><br> Quoc Duy</a>
				</h3>
				<span class="d-block position mb-4">CEO, Founder.</span>
				<p>The smartphone go to school to improve its "cell-f" esteem.</p>
				<p class="mb-0">
					<a href="#" class="more dark">Learn More <span
						class="icon-arrow_forward"></span></a>
				</p>
			</div>
		</div>
	</div>
</div>

<%@ include file="/views/includes/footer.jsp"%>