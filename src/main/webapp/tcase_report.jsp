<%@ page import="java.nio.charset.StandardCharsets" %>

<%
String filename = "Reportt.txt"; 
response.setContentType("APPLICATION/OCTET-STREAM charset=UTF-8");   
response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   

java.io.FileInputStream fileInputStream = new java.io.FileInputStream(filename);  


int i;   
while ((i=fileInputStream.read()) != -1) {  
  out.write(i);   
}   
fileInputStream.close();  
%>