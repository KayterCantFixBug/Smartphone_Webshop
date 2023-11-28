<%@ include file="/views/includes/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<h1>Update User</h1>

<section>
    <form action="updateUser" method="post">
        <label>Id</label>
        <input type="text" name="id" value="${user.id}" /><br>
        <label>Phone number</label>
        <input type="text" name="phoneNumber" value="${user.phoneNumber}" /><br>
        <label>Name</label>
        <input type="text" name="name" value="${user.name}"/><br>
        <label>Password </label>
        <input type="text" name="password" value="${user.password}" />  <br>
        <label>BirthDate</label>
        <input type="datetime-local" name="birthdate" value="${user.birthdate}"/><br>
        <label>Email </label>
        <input type="text" name="email" value="${user.email}" />  <br>
        <input type="hidden" name="status" value="${user.status}" />  <br>
        <input type="radio" name="gender" value="MALE" ${user.gender == 'MALE' ? 'checked' : ''} />Nam
        <input type="radio" name="gender" value="FEMALE" ${user.gender == 'FEMALE' ? 'checked' : ''} />Nữ

        </p>
        <input type="submit" value="Xác nhận" id="submit">
    </form>
</section>
<br>
<%@ include file="/views/includes/footer.jsp"%>