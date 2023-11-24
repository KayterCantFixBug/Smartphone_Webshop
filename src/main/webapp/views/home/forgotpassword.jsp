<%@ include file="/views/includes/header.jsp"%>
<div class="position-absolute top-50 start-50 translate-middle">
	<div class="card" style="width: 30rem;">
		<div class="card-header text-center">Reset Password</div>
		<div class="card-body">
			<c:if test="${error != null}">
				<div class="alert alert-danger mb-3" role="alert">${error}</div>
			</c:if>
			<form action="forgotpassword" method="post">
				<div class="mb-3">
					<label class="form-label"><b>Email Address:</b></label> <input
						type="email" name="email" class="form-control"
						placeholder="name@example.com" required>
				</div>
				<div class="text-center mb-3">
					<button type="submit" class="btn btn-primary">Next</button>
				</div>
			</form>
		</div>
	</div>
</div>
<%@ include file="/views/includes/footer.jsp"%>