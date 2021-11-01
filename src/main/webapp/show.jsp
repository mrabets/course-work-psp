<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 

<z:layout pageTitle="Show utests">

<h1 class="display-5">Unit Tests List</h1><br>

<table class="table">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Name</th>
      <th scope="col">Errors number</th>
      <th scope="col">Lead time</th>
      <th scope="col">Date of creation</th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
  <h1></h1>
  <c:forEach var="unit_test" items="${unit_tests}">
    <tr>
      <th scope="row">${unit_test.id}</th>
      <td>${unit_test.name}</td>
      <td>${unit_test.errorsNumber}</td>
      <td>${unit_test.leadTime}</td>
      <td>${unit_test.createdAt}</td>
      <td>
      <div class="btn-group">
        <a class="mybtn btn btn-warning btn-sm" href="update?id=${unit_test.id}">Обновить</a>

      	<form action="delete" method="POST" onsubmit="return confirm('Are you sure?');">
          <input type="hidden" name="id" value=${unit_test.id} />
          <input type="submit" value="Удалить" class="mybtn btn btn-danger btn-sm"  />
    	</form>
      </div>
      </td>
    </tr>
  </c:forEach>  
  </tbody>
</table>
</z:layout>