<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %> 

<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 


<z:layout pageTitle="Login">

<h1 class="display-5">Регистрация в системе</h1><br>

<form method="POST" action="signup">

<div class="col-md-5">  
	<p style="color:red">${errorMsg}</p><br>
</div>

<div class="col-md-3">
   <div class="mb-3">
      <label class="form-label">Логин</label>
      <input type="text" name="login" class="form-control" minlength="5" required>
   </div>
   
   <div class="mb-3">
      <label class="form-label">Пароль</label>
      <input type="password" name="password" class="form-control" minlength="5" required>
   </div>
   
   <div class="mb-3">
      <label class="form-label">Подтверждение пароля</label>
      <input type="password" name="password_confirm" class="form-control" minlength="5" required>
   </div>

  <input type="submit" name="btn_signup" value="Зарегестрироваться" class="btn btn-primary"><br><br>

	<a class="mylink" href="login">Уже есть аккаунт? <b> Войдите здесь! </b></a>	
</div>

</form>

</z:layout>