<%@ page import="java.util.List"%>
<%@ page import="pw2.*"%>
<%
List<Tareas> accounts =(List<Tareas>)request.getAttribute("listaTareas");
%>
<html>
<head>
<title>Lab07</title>
<link rel="stylesheet" type="text/css"
href="/stylesheets/styles.css"/>
</head>
<body>
<span class="title">Tareas</span>
<p>
<a href="lab07?action=addTarea"/>Crear Tarea</a>
</p>

<p/>
<%
if(accounts.size() > 0)
{%>
<p/>
<table border="0" cellspacing="1" cellpadding="5" bgcolor="#CCCCCC" width="50%">
<tr bgcolor="#407BA8">
<td style="color: #ffffff; font-weight:bold;">Curso</td>
<td style="color: #ffffff; font-weight:bold;">Tema</td>
<td style="color: #ffffff; font-weight:bold;">Profesor</td>
<td style="color: #ffffff; font-weight:bold;">Alumno</td>	
</tr>
<% for (int i = 0;i<accounts.size();i++) { %>
<% Tareas a = (Tareas)accounts.get(i); %>
<tr style="background:#ffffff" onMouseOver="this.style.background='#eeeeee';" onMouseOut="this.style.background='#ffffff';">
<td><a href="lab07?action=Ed/El&tareaId=<%= a.getId() %>"><%=a.getCurso() %></a></td>
<td><%= a.getTema() %></td>
<td><%= a.getProfesor() %></td>
<td><%= a.getAlumno() %></td>
</tr>
<% } %>
</table>
<% }
else { %>
<span class="heading">No hay tareas registradas</span>
<% } %>
<p/>
</body>
</html>