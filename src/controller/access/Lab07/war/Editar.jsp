<%@ page import="pw2.*"%>
<%
Tareas a = (Tareas)request.getAttribute("tarea");
%>
<html>
<head>
<title>Tarea</title>
<link rel="stylesheet" type="text/css" href="/stylesheets/styles.css"/>
</head>
<body>
<span class="nav"><a href="/lab07?action=indexTarea">HOME</a></span><p/>
<span class="title">Editar Tarea</span>
<p/>

<form method="get" action="lab07">
<input type="hidden" name="action" value="EditarDo"/>
<input type="hidden" name="tareaId" value="<%= a.getId() %>"/>
<table border="0" cellspacing="1" cellpadding="5" bgcolor="#CCCCCC">

<tr bgcolor="#407BA8">
<td style="color: #ffffff; font-weight: bold;">Tema</td>
<td bgcolor="#ffffff"><input type="input" name="tema" value="<%= a.getTema() %>"></td>
</tr>

<tr bgcolor="#407BA8">
<td style="color: #ffffff; font-weight: bold;">Curso</td>
<td bgcolor="#ffffff"><input type="input" name="curso" value="<%= a.getCurso() %>"></td>
</tr>

<tr bgcolor="#407BA8">
<td style="color: #ffffff; font-weight: bold;">Alumno</td>
<td bgcolor="#ffffff"><input type="input" name="alumno" value="<%= a.getAlumno() %>"></td>
</tr>

<tr bgcolor="#407BA8">
<td style="color: #ffffff; font-weight: bold;">Profesor</td>
<td bgcolor="#ffffff"><input type="input" name="profesor" value="<%= a.getProfesor() %>"></td>
</tr>

<tr>
<td colspan="2" bgcolor="#ffffff" align="center"><input
type="submit" value="Editar"></td>
</tr>
</table>
</form>
<span class="nav"><a href="/lab07?action=Eliminar&tareaId=<%= a.getId()%>">Eliminar</a></span><p/>


</body>
</html>