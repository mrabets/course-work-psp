<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 

<z:layout pageTitle="Show utests">

<h1 class="display-5">Список пользователей</h1><br>

<form action="user_multiple_delete" method="POST" onsubmit="return confirm('Вы уверены?');"> 
<a class="mybtn btn btn-success btn-sm" href="user_create">Создать </a> 
<input type="submit" value="Удалить" class="mybtn btn btn-danger btn-sm" /><br><br>

<table id="sortTable">
  <thead>
    <tr>
      <th><input type="checkbox" onClick="toggle(this)" /></th>
      <th scope="col">ID</th>
      <th scope="col">Логин</th>
      <th scope="col">Пароль</th>
      <th scope="col">Права администратора</th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
  <h1></h1>
  <c:forEach var="user" items="${users}">
    <tr>
      <td><input type="checkbox" name="users" value="${user.id}"></td>
      <th scope="row">${user.id}</th>
      <td>${user.login}</td>
      <td>${user.password}</td>
      <td>${user.admin ? "Да" : "Нет"}</td>
      <td>
      <div class="btn-group">
        <a class="mybtn btn btn-warning btn-sm" href="user_update?id=${user.id}">Обновить</a>
      </div>
      </td>
    </tr>
  </c:forEach>  
  </tbody>
</table>
</form><br><br>
</z:layout>

<script>
$(document).ready( function () {
	var table = $('#sortTable').DataTable({
	    language: {
	        url: '//cdn.datatables.net/plug-ins/1.11.3/i18n/ru.json'
	    }
	});
} );

function toggle(source) {
  checkboxes = document.getElementsByName('users');
  for(let i = 0, n = checkboxes.length; i < n; i++) {
    checkboxes[i].checked = source.checked;
  }
}
</script>