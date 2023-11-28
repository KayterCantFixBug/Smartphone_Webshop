<%@ include file="/views/includes/header.jsp"%>
<div class="container mt-5 mb-5 d-flex justify-content-center">
	<div class="card" style="width: 30rem;">
		<div class="card-header text-center text-bg-success mb-5">Profile</div>
		<div class="card-body">
			<form action="editprofile" method="post"
				enctype="multipart/form-data">
				<div class="row align-items-center mb-3">
					<div class="col-3">
						<label class="form-label"><b>Name:</b></label>
					</div>
					<div class="col">
						<input type="text" name="name" class="form-control">
					</div>
				</div>
				<div class="row align-items-center mb-3">
					<div class="col-3">
						<label class="form-label"><b>Role:</b></label>
					</div>
					<div class="col">
						<input type="radio" class="btn-check" name="role" id="User"
							autocomplete="off"> <label
							class="btn btn-outline-primary" for="User">USER</label> <input
							type="radio" class="btn-check" name="role" id="Admin"
							autocomplete="off"> <label
							class="btn btn-outline-primary" for="Admin">ADMIN</label>
					</div>
				</div>
				<div class="row align-items-center mb-3">
					<div class="col-3">
						<label class="form-label"><b>Status:</b></label>
					</div>
					<div class="col">
						<input type="radio" class="btn-check" name="status" id="active"
							autocomplete="off"> <label
							class="btn btn-outline-success" for="active">ACTIVE</label><input
							type="radio" class="btn-check" name="status" id="Inactive"
							autocomplete="off"> <label class="btn btn-outline-danger"
							for="Inactive">INACTIVE</label>
					</div>
				</div>
				<div class="row align-items-center mb-3">
					<div class="col-3">
						<label class="form-label"><b>Email:</b></label>
					</div>
					<div class="col">
						<input type="email" name="email" class="form-control"
							value="${profileUser.email }">
					</div>
				</div>
				<div class="row align-items-center mb-3">
					<div class="col-3">
						<label class="form-label"><b>Image:</b></label>
					</div>
					<div class="col">
						<input class="form-control" type="file" name="image">
						<c:if test="${profileUser.image != null}">
							<img src="${requestScope.imageData}" width="50" height="50" />
						</c:if>
					</div>
				</div>
				<div class="row align-items-center mb-3">
					<div class="col-3">
						<label class="form-label"><b>Birth Date:</b></label>
					</div>
					<div class="col">
						<input type="date" name="date" class="form-control">
					</div>
				</div>
				<div class="row align-items-center mb-3">
					<div class="col-3">
						<label class="form-label"><b>Gender:</b></label>
					</div>
					<div class="col">
						<input type="radio" class="btn-check" name="gender" id="male"
							autocomplete="off"> <label
							class="btn btn-outline-primary" for="male">MALE</label> <input
							type="radio" class="btn-check" name="gender" id="female"
							autocomplete="off"> <label
							class="btn btn-outline-primary" for="female">FEMALE</label><input
							type="radio" class="btn-check" name="gender" id="none"
							autocomplete="off"> <label class="btn btn-outline-danger"
							for="none">NON-DISCLOSURE</label>
					</div>
				</div>
				<div class="row align-items-center mb-3">
					<div class="col-3">
						<label class="form-label"><b>Phone:</b></label>
					</div>
					<div class="col">
						<input type="text" name="phoneNumber" class="form-control"
							pattern="0[0-9]{9}" title="The phone number is invalid.">
					</div>
				</div>
				<div class="row align-items-center mb-3">
					<div class="col-3">
						<label class="form-label"><b>Password:</b></label>
					</div>
					<div class="col">
						<input type="password" name="password" class="form-control"
							pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$"
							title="The password must contain at least 8 characters, including at least ONE LETTER and ONE NUMBER.">
					</div>
				</div>
				<div class="form-check form-switch mb-3">
					<input class="form-check-input" type="checkbox" role="switch"
						id="showpw"> <label class="form-check-label" for="showpw">Show
						Password</label>
				</div>
				<div class="text-center mb-3">
					<button type="submit" class="btn btn-primary">Edit</button>
				</div>
			</form>
		</div>
	</div>
</div>
<%@ include file="/views/includes/footer.jsp"%>