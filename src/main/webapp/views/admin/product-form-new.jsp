<%@ include file="/views/includes/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<h1>Add Product</h1>

<section>
    <form action="addProduct" method="post">
        <label>Name</label>
        <input type="text" name="name" /><br>
        <label>Brand</label>
        <input type="text" name="brand"/><br>
        <label>Price </label>
        <input type="text" name="price" />  <br>
        <label>Storage</label>
        <input type="text" name="storage"/><br>
        <label>Ram </label>
        <input type="text" name="ram"  />  <br>
        <label>Os </label>
        <input type="text" name="os"/>  <br>
        <label>Description </label>
        <input type="text" name="description" />  <br>

        <input type="submit" value="Xác nhận" id="submit">
    </form>
</section>
<br>
<%@ include file="/views/includes/footer.jsp"%>