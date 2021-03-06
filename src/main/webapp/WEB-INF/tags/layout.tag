<%@ tag body-content="scriptless" pageEncoding="UTF-8" %>
<%@ attribute name="pageTitle" required="true" type="java.lang.String" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
			<title>${pageTitle}</title>
			
			<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
			<link rel="preconnect" href="https://fonts.googleapis.com">
			<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
			<link href="https://fonts.googleapis.com/css2?family=Oswald&family=Roboto&display=swap" rel="stylesheet">
			<link href="//datatables.net/download/build/nightly/jquery.dataTables.css" rel="stylesheet" type="text/css" />
			
			<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
			<script src="//datatables.net/download/build/nightly/jquery.dataTables.js"></script>
			<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
			
			<style><%@include file="/WEB-INF/css/style.css"%></style>
			<style><%@include file="/WEB-INF/css/datatable.css"%></style>
	</head>
	
	<body class="container">
		<jsp:include page="header.jsp" />
  		<jsp:doBody/>
	</body>
</html>