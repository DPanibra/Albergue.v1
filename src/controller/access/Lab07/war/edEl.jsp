<%@ page import="pw2.*"%>
<%
Tareas a = (Tareas)request.getAttribute("tarea");
%>
<html>
<head>
<title>Tarea</title>
<link rel="stylesheet" type="text/css"
href="/stylesheets/styles.css"/>
</head>
<body>
<span class="nav"><a href="/lab07?action=indexTarea">HOME</a></span><p/>
<span class="title">Tarea</span>
<p/>
<table border="0" cellspacing="1" cellpadding="5" bgcolor="#CCCCCC">
<tr bgcolor="#407BA8">
<td style="color: #ffffff; font-weight: bold;">Curso</td>
<td bgcolor="#ffffff"><%= a.getCurso() %></td>
</tr>
<tr bgcolor="#407BA8">
<td style="color: #ffffff; font-weight: bold;">Tema</td>
<td bgcolor="#ffffff"><%= a.getTema() %></td>
</tr>
<tr bgcolor="#407BA8">
<td style="color: #ffffff; font-weight:bold;">Profesor</td>
<td bgcolor="#ffffff"><%= a.getProfesor() %></td>
</tr>
<tr bgcolor="#407BA8">
<td style="color: #ffffff; font-weight:bold;">Alumno</td>
<td bgcolor="#ffffff"><%= a.getAlumno() %></td>
</tr>
</table></br>
<span class="nav"><a href="/lab07?action=Editar&tareaId=<%= a.getId()%>">Editar</a></span><p/>
<span class="nav"><a href="/lab07?action=Eliminar&tareaId=<%= a.getId()%>">Eliminar</a></span><p/>


</body>
</html>