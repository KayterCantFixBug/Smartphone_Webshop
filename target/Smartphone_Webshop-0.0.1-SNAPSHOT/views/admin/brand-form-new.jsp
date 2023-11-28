<%@ include file="/views/includes/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<h1>Add Brand</h1>

<section>
    <form action="addBrand" method="post">
        <label>Name</label>
        <input type="text" name="name"/><br>
        <label>Country</label>
        <input type="text" name="country" /><br>
        </p>
        <input type="submit" value="Xác nhận" id="submit">
    </form>
</section>
<br>
<%@ include file="/views/includes/footer.jsp"%>