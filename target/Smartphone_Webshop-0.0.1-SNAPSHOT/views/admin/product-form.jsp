<%@ include file="/views/includes/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<h1>Update Product</h1>

<section>
    <form action="updateProduct" method="post">
        <label>Id</label>
        <input type="text" name="id" value="${product.id}" /><br>
        <label>Name</label>
        <input type="text" name="name" value="${product.name}" /><br>
        <label>Brand</label>
        <input type="text" name="brand" value="${product.brand.id}"/><br>
        <label>Price </label>
        <input type="text" name="price" value="${product.price}" />  <br>
        <label>Storage</label>
        <input type="text" name="storage" value="${product.storage}"/><br>
        <label>Ram </label>
        <input type="text" name="ram" value="${product.ram}" />  <br>
        <label>Os </label>
        <input type="text" name="os" value="${product.os}" />  <br>
        <label>Description </label>
        <input type="text" name="description" value="${product.description}" />  <br>

        <input type="submit" value="Xác nhận" id="submit">
    </form>
</section>
<br>
<%@ include file="/views/includes/footer.jsp"%>