<%@ include file="/views/includes/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<h1>Update Brand</h1>

<section>
    <form action="updateBrand" method="post">
        <label>Id</label>
        <input type="text" name="id" value="${brand.id}" /><br>
        <label>Name</label>
        <input type="text" name="name" value="${brand.name}"/><br>
        <label>Password </label>
        <label>Phone number</label>
        <input type="text" name="country" value="${brand.country}" /><br>
        </p>
        <input type="submit" value="Xác nhận" id="submit">
    </form>
</section>
<br>
<%@ include file="/views/includes/footer.jsp"%>