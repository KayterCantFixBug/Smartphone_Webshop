<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>User Management</title>
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2>

        <form action="showNewFormUser" method="post">
            <input type="submit" value="Add New User">
        </form>

        <form action="printListUser" method="post">
            <input type="submit" value="List All Users">
        </form>


    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>ID</th>
            <th>PhoneNumber</th>
            <th>Name</th>
            <th>Password</th>
            <th>Birhtdate</th>
            <th>Email</th>
            <th>Image</th>
            <th>Status</th>
            <th>Gender</th>

        </tr>
        <c:forEach var="user" items="${listUser}">
            <tr>
                <td><c:out value="${user.id}" /></td>
                <td><c:out value="${user.phoneNumber}" /></td>
                <td><c:out value="${user.name}" /></td>
                <td><c:out value="${user.password}" /></td>
                <td><c:out value="${user.birthdate}" /></td>
                <td><c:out value="${user.email}" /></td>
                <td><c:out value="${user.image}" /></td>
                <td><c:out value="${user.status}" /></td>
                <td><c:out value="${user.gender}" /></td>

                <td>
                <form action="showEditFormUser" method="post">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="hidden" name="phoneNumber" value="${user.phoneNumber}">
                    <input type="hidden" name="name" value="${user.name}">
                    <input type="hidden" name="password" value="${user.password}">
                    <input type="hidden" name="birthdate" value="${user.birthdate}">
                    <input type="hidden" name="email" value="${user.email}">
                    <input type="hidden" name="status" value="${user.status}">
                    <input type="hidden" name="gender" value="${user.gender}">
                    <input type="submit" value="Edit">
                </form>
                </td>
<%--                    <a href="deleteUser?id=<c:out value='${user.id}' />">Delete</a>--%>

            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>