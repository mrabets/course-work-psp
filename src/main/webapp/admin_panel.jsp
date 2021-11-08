<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 

<z:layout pageTitle="Admin panel">
<h1 class="display-5 text-center">Панель администратора</h1><br><br>

<div class="d-grid gap-2 col-6 mx-auto">
  <a href="user_index" class="btn btn-info" type="button">Усправление пользователями</a><hr>
  <a href="tcase_index" class="btn btn-warning" type="button">Усправление контрольными тестами</a>
</div>
</z:layout>