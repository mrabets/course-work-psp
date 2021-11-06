<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 

<z:layout pageTitle="Update user">

<div class="col-md-5 col-lg-8">
    <form action="user_update" method="POST">
    
	<input type="hidden" value="${user.id}" name="id" />
	
    <div class="col-md-8">
    	<label class="form-label">Логин</label>
		<input name="login" value="${user.login}" required minlength="5" type="text" class="form-control" /><br>	  
		
    	<label class="form-label">Пароль</label>
		<input name="password" required minlength="5" type="password" class="form-control" /><br>
		
		<div class="form-check">
		  <input name="isAdmin" class="form-check-input" type="checkbox" value="true" id="flexCheckChecked" >
		  <label class="form-check-label" for="flexCheckChecked">
		     Права администратора
		  </label>
		</div><br>

   	<input type="submit" value="Обновить" class="btn btn-primary" />
   	
   	</form>	
</div>

</z:layout>