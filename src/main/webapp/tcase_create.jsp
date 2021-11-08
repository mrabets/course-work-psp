<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 

<z:layout pageTitle="Create tcase">

<h1 class="display-5">Создание контрольного теста</h1><br>

<div class="col-md-5 col-lg-8">
    <form action="tcase_create" method="POST">

    <div class="col-md-8">
    	<label class="form-label">Наименование</label>
		<input name="name" required minlength="1" type="text" class="form-control" /><br>	  
		
		<label class="form-label">Фреймворк</label>
		<input name="framework" required minlength="1" type="text" list="frameworks" class="form-control" /><br>	
		
		<datalist id="frameworks">
		  <select>
		    <c:forEach var="framework" items="${frameworks}">
		      <option value="${framework}">${framework}</option>
		    </c:forEach>
		  </select>
		</datalist>
		
	</div><br>

   	<input type="submit" value="Создать" class="btn btn-primary" />
   	
   	</form>	
</div>

</z:layout>