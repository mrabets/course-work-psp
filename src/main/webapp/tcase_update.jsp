<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 

<z:layout pageTitle="Update tcase">

<div class="col-md-5 col-lg-8">
    <form action="tcase_update" method="POST">
    
	<input type="hidden" value="${testCase.id}" name="id" />
	
    <div class="col-md-8">
    	<label class="form-label">Наименование</label>
		<input value="${testCase.name}" name="name" required minlength="1" type="text" class="form-control" /><br>	  
		
		<label class="form-label">Фреймворк</label>
		<input value="${testCase.framework}" name="framework" required minlength="1" type="text" list="frameworks" class="form-control" /><br>	
		
		<div>
		  Выполнен?
		  <input value="${testCase.complete}" name="complete" class="form-check-input" type="checkbox" 
		  <c:if test="${testCase.complete == true}">
		    checked
		  </c:if>
		  /><br>	
		</div>
		
		<datalist id="frameworks">
		  <select>
		    <c:forEach var="framework" items="${frameworks}">
		      <option value="${framework}">${framework}</option>
		    </c:forEach>
		  </select>
		</datalist>
	</div><br>

   	<input type="submit" value="Обновить" class="btn btn-primary" />
   	
   	</form>	
</div>

</z:layout>