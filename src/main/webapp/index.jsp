<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>

<z:layout pageTitle="Show utests">


	<main class="px-3">
	  ${alert}
	  <h1 style="font-weight: 600;" class="text-center">Добро пожаловать!</h1><br>
	
	  <p class="lead text-center mx-auto" style="width: 609px;">
	    Используйте сайт для просмотра модульных тестов. Чтобы начать создавать модульные тесты зарегестрируетесь на сайте. Если у вас уже есть аккаунт, то войдите.
	  </p><br>
	
	  <p class="lead text-center">
        <a href="utest_index" class="btn btn-lg btn-secondary fw-bold border-black bg-black">Начать</a>
      </p>
    </main>


</z:layout>
