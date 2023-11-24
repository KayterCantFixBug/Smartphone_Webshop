<%@ include file="/views/includes/header.jsp"%>
<div class="position-absolute top-50 start-50 translate-middle">
	<img src="${pageContext.servletContext.contextPath}/images/error.png"
		alt="Error">
	<h1>Java Error</h1>
	<p>Sorry, Java has thrown an exception.</p>
	<p>To continue, click the Back button.</p>
	<h2>Details</h2>
	<p>Type: ${pageContext.exception["class"]}</p>
	<p>Message: ${pageContext.exception.message}</p>
</div>
<%@ include file="/views/includes/footer.jsp"%>
