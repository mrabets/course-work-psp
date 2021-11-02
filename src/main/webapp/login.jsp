<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %> 

<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 



<z:layout pageTitle="Login">

<div class="col-md-3">

<form method="POST" action="login" onsubmit="return validate();">
		
	  <h1>Войти в систему</h1><br>
	  
	  <p style="color:red">${errorMsg}</p><br>

      <div class="mb-3">
         <label class="form-label">Логин</label>
         <input type="text" name="login" class="form-control" id="email" reguired minlength=5>
      </div>
      
	  <div class="mb-3">
         <label class="form-label">Пароль</label>
         <input type="password" name="password" class="form-control" reguired minlength=5>
      </div>


     <input type="submit" name="btn_login" value="Войти" class="btn btn-primary"><br><br>

     <a href="#">Нет аккаунта? <b> Создайте здесь! </b></a>	

</form>

</div>
</z:layout>

<script>
  
 function validate()
 {
  var email = document.myform.txt_email;
  var password = document.myform.txt_password;
    
  if (email.value == null || email.value == "") //check email textbox not blank
  {
   window.alert("please enter email ?"); //alert message
   email.style.background = '#f08080';
   email.focus();
   return false;
  }
  if (password.value == null || password.value == "") //check password textbox not blank
  {
   window.alert("please enter password ?"); //alert message
   password.style.background = '#f08080'; 
   password.focus();
   return false;
  }
 }
   
</script>