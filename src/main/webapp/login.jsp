<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %> 

<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 


<z:layout pageTitle="Login">

<form method="POST" action="login">

<div class="col-md-5">  
	<h1>Войти в систему</h1><br>
	<p style="color:red">${errorMsg}</p><br>
</div>

<div class="col-md-3">  
   <div class="mb-3">
      <label class="form-label">Логин</label>
      <input type="text" name="login" class="form-control" id="email" reguired minlength=5>
   </div>
   
   <div class="mb-3">
      <label class="form-label">Пароль</label>
      <input type="password" name="password" class="form-control" reguired minlength=5>
   </div>


  <input type="submit" name="btn_login" value="Войти" class="btn btn-primary"><br><br>

  <a class="mylink" href="signup">Нет аккаунта? <b> Создайте здесь! </b></a>	
</div>

</form>

</z:layout>