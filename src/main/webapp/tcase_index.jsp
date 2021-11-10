<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 

<z:layout pageTitle="Show utests">

<h1 class="display-5">Список контрольных тестов</h1><br>

<a class="mybtn btn btn-warning" href="test_case_report">Сделать отчёт</a>
<a class="mybtn btn btn-info" href="tcase_chart">Построить диаграмму</a><hr>

<form action="tcase_multiple_delete" method="POST" onsubmit="return confirm('Вы уверены?');"> 
<a class="mybtn btn btn-success btn-sm" href="tcase_create">Создать </a> 
<input type="submit" value="Удалить" class="mybtn btn btn-danger btn-sm" /><br><br>

<table id="sortTable">
  <thead>
    <tr>
      <th><input type="checkbox" onClick="toggle(this)" /></th>
      <th scope="col">ID</th>
      <th scope="col">Наименование</th>
      <th scope="col">Фреймворк</th>
      <th scope="col">Последний запуск</th>
      <th scope="col">Статус</th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
  <h1></h1> 
  <c:forEach var="test_case" items="${test_cases}">
    <tr>
      <td><input type="checkbox" name="testCases" value="${test_case.id}"></td>
      <td scope="row">${test_case.id}</td>
      <td>${test_case.name}</td>
      <td>${test_case.framework}</td>
      <td>${test_case.lastLaunch}</td>
      <td>
      
      <c:if test="${test_case.complete == true}">
	    <button type="button" class="btn btn-sm btn-success" disabled>Успешно</button>
	  </c:if>
	  <c:if test="${test_case.complete == false}">
	     <button type="button" class="btn btn-sm btn-danger" disabled>Неудачно</button>
	  </c:if>
      
      </td>
      <td>
      <div class="btn-group">
        <a class="mybtn btn btn-warning btn-sm" href="tcase_update?id=${test_case.id}">Обновить</a>
      </div>
      </td>
    </tr>
  </c:forEach>  
  </tbody>
</table><br><br>

</form>
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
  checkboxes = document.getElementsByName('testCases');
  for(let i = 0, n = checkboxes.length; i < n; i++) {
    checkboxes[i].checked = source.checked;
  }
}
</script>