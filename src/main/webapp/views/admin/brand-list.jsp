<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Brand Management</title>
</head>
<body>
<center>
    <h1>Brand Management</h1>
    <h2>

        <form action="showNewFormBrand" method="post">
            <input type="submit" value="Add New Brand">
        </form>

        <form action="printListBrand" method="post">
            <input type="submit" value="List All Brands">
        </form>


    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Brand

            s</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Country</th>
        </tr>
        <c:forEach var="brand" items="${listBrand}">
            <tr>
                <td><c:out value="${brand.id}" /></td>
                <td><c:out value="${brand.name}" /></td>
                <td><c:out value="${brand.country}" /></td>
                <td>
                    <form action="showEditFormBrand" method="post">
                        <input type="hidden" name="id" value="${brand.id}">
                        <input type="submit" value="Edit">
                    </form>
                </td>
                <td>
                    <form action="deleteBrand" method="post">
                        <input type="hidden" name="id" value="${brand.id}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>