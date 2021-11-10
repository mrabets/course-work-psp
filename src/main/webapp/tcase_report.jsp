<%
String filename = "Reportt.txt"; 
response.setContentType("APPLICATION/OCTET-STREAM charset=UTF-8");   
response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   
out.write((String)request.getAttribute("everything"));   
%>