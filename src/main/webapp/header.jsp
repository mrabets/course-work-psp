<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
	<a class="nav navbar-brand" href="<c:url value="/" />">UNIT TEST</a>
    <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
      <li class="nav-item">
        <a class="nav nav-link" href="utest_show">Отобразить</a>
      </li>
      <li class="nav-item">
        <a class="nav nav-link" href="utest_create">Создать</a>
      </li>
      <% if (session.getAttribute("is_admin") != null) { %>
      <li class="nav-item">
        <a class="nav nav-link" href="admin_panel">Панель администратора</a>
      </li>
      <% } %>
    </ul>
    
    <% if (session.getAttribute("login") != null) { %>
      <a class="btn btn-danger" href="logout">Выйти</a>	
    <% } else { %>
    <form class="nav-btn d-flex">
      <a class="btn btn-outline-primary me-2" href="login">Вход</a>
      <a class="btn btn-primary" href="signup">Регистрация</a>
    </form>
    <% } %>
</nav><br>