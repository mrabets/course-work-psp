<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 

<z:layout pageTitle="Create utest">

<div class="col-md-5 col-lg-8">
    <form action="create" method="POST">

    <div class="col-md-8">
    	<label class="form-label">Name</label>
		<input name="name" required minlength="1" type="text" class="form-control" /><br>	  
		
    	<label class="form-label">Errors number</label>
		<input name="errorsNumber" required min="0" type="number" class="form-control" /><br>
		
		<label class="form-label">Lead time</label>
		<input name="leadTime" required type="time" class="form-control" /><br>	 
		
		<label class="form-label">Date of creation</label>
		<input name="createdAt" value="2021-01-01" required min="2001-01-01" max="2099-12-31" type="date" class="form-control" /><br>	  
	</div><br>

   	<input type="submit" value="Send" class="btn btn-primary" />
   	
   	</form>	
</div>

</z:layout>