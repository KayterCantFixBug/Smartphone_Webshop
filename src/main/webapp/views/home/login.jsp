<%@ include file="/views/includes/header.jsp"%>
<script>
	function showPassword() {
		var passwordField = document.getElementById("password");
		if (passwordField.type == "password") {
			passwordField.type = "text";
		} else {
			passwordField.type = "password";
		}
	}
</script>
<div class="position-absolute top-50 start-50 translate-middle">
	<div class="card" style="width: 30rem;">
		<div class="card-header text-center">Login</div>
		<div class="card-body">
			<c:if test="${error != null}">
				<div class="alert alert-danger mb-3" role="alert">${error}</div>
			</c:if>
			<form action="login" method="post">
				<div class="mb-3">
					<label class="form-label"><b>Email Address:</b></label> <input
						type="email" name="email" class="form-control"
						placeholder="name@example.com" required>
				</div>
				<div class="mb-3">
					<label class="form-label"><b>Password:</b></label><input
						type="password" name="password" id="password" class="form-control"
						required>
				</div>
				<div class="form-check mb-3">
					<input name="cbShowPass" class="form-check-input" type="checkbox"
						onchange="showPassword()"> <label>Show Password</label>
				</div>
				<div class="form-check mb-3">
					<input name="remember" class="form-check-input" type="checkbox">
					<label>Remember Me</label>
				</div>
				<div class="text-center mb-3">
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
			</form>
			<div class="card-footer">
				<a
					href="${pageContext.servletContext.contextPath}/views/home/forgotpassword.jsp"
					class="link-body-emphasis link-underline-opacity-25 link-underline-opacity-75-hover">Forgot
					Password?</a>
			</div>
		</div>
	</div>
</div>
<%@ include file="/views/includes/footer.jsp"%>