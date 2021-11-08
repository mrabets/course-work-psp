<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 

<z:layout pageTitle="Create user">

<h1 class="display-5">Создание нового пользователя</h1><br>


<form action="user_create" method="POST">
	
<div class="col-md-5">  
  <p style="color:red">${errorMsg}</p><br>
</div>
	
    <div class="col-md-3">
    	<label class="form-label">Логин</label>
		<input name="login" required minlength="1" type="text" class="form-control" /><br>	  
		
    	<label class="form-label">Пароль</label>
		<input name="password" required min="0" type="password" class="form-control" /><br>
		
		<div class="form-check">
		  <input name="isAdmin" class="form-check-input" type="checkbox" value="true" id="flexCheckChecked" >
		  <label class="form-check-label" for="flexCheckChecked">
		     Права администратора
		  </label>
		</div>
		 		
	</div><br>

   	<input type="submit" value="Создать" class="btn btn-primary" />
   	
   	</form>	
</div>

</z:layout>