<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Product Management</title>
</head>
<body>
<center>
    <h1>Product Management</h1>
    <h2>

        <form action="showNewFormProduct" method="post">
            <input type="submit" value="Add New Product">
        </form>

        <form action="printListProduct" method="post">
            <input type="submit" value="List All Products">
        </form>


    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of products</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Brand</th>
            <th>Price</th>
            <th>Storage</th>
            <th>Ram</th>
            <th>Os</th>
            <th>Description</th>
            <th>Image</th>
        </tr>
        <c:forEach var="product" items="${listProduct}">
            <tr>
                <td><c:out value="${product.id}" /></td>
                <td><c:out value="${product.name}" /></td>
                <td><c:out value="${product.brand.id}" /></td>
                <td><c:out value="${product.price}" /></td>
                <td><c:out value="${product.storage}" /></td>
                <td><c:out value="${product.ram}" /></td>
                <td><c:out value="${product.os}" /></td>
                <td><c:out value="${product.description}" /></td>
                <td><c:out value="${product.image}" /></td>

                <td>
                    <form action="showEditFormProduct" method="post">
                        <input type="hidden" name="id" value="${product.id}">
                        <input type="submit" value="Edit">
                    </form>
                </td>
                <td>
                    <form action="deleteProduct" method="post">
                        <input type="hidden" name="id" value="${product.id}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>