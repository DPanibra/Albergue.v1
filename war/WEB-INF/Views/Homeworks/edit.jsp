<%@ page language="java" contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="model.entity.Homework"%>
<% Homework a=(Homework)request.getAttribute("rol"); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="../style.css" />
	<title>Albergue</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script>
    $(document).ready(function() {
        $menu = $('#menu').find('ul').find('li');

        $menu.hover(function() {
            $(this).children('ul').stop();
            $(this).children('ul').slideDown();
        }, function() {
            $(this).children('ul').stop();
            $(this).children('ul').slideUp();
        });
    });
    </script>
</head>
<body>
	<div class="grid-container">
		<div class="logo">
			<h4>Santo Tomas de Aquino</h4>
		</div>

		<div class="login">
			<h4>
			<a href="/users/login">Login</a>
	 	 	<a href="/users/logout">Logout</a>
	 		</h4>
	 	</div>

		<div class="sidenav">
			<nav id="menu">
				<ul>
				<li><a href="/homeworks">Tareas</a>
	                <ul>
	                    <li><a href="/homeworks/add">New Tarea</a></li>
	                    
	                </ul>
	            </li>

	            <li><a href="/users">Usuarios</a>
	                <ul>
	                    <li><a href="/users/add">New Usuario</a></li>
	                    
	                </ul>
	            </li>

	            <li><a href="/access">Accesos</a>
	                <ul>
	                    <li><a href="/access/add">New Acceso</a></li>
	                    
	                </ul>
	            </li>
	            
	            <li><a href="/resources">Recursos</a>
	                <ul>
	                    <li><a href="/resources/add">New Recurso</a></li>
	                    
	                </ul>
	            </li>
	            
	            <li><a href="/roles">Roles</a>
	                <ul>
	                    <li><a href="/roles/add">New Rol</a></li>
	                    
	                </ul>
	            </li>

	  			</ul>
	  		</nav>
		</div>

		<div class="content">
	  		<h2>ALBERGUE</h2>
	  		<p>EDIT TAREA</p>
	  		<form method="get" action="/homeworks/edit">
	  			<input type="hidden" name="action" value="Edit"/>
				<input type="hidden" name="homeworksId" value="<%= a.getId() %>"/>
	  			<table id="tabla">
	  				<tr >
					<td bgcolor="#4caf50">Alumno</td>
					<td ><input type="input" name="alumno" value="<%=a.getAlumno() %>" required></td>
					</tr>
					
					<tr >
					<td bgcolor="#4caf50">Tema</td>
					<td ><input type="input" name="tema" value="<%=a.getTema() %>" required></td>
					</tr>
					
					<tr >
					<td bgcolor="#4caf50">Curso</td>
					<td ><input type="input" name="curso" value="<%=a.getCurso() %>" required></td>
					</tr>
					
					<tr >
					<td bgcolor="#4caf50">Profesor</td>
					<td ><input type="input" name="profesor" value="<%=a.getProfesor() %>"></td>
					</tr>
					
					<tr>
					<td bgcolor="#4caf50" align="center"><input type="submit" value="Listo"></td>
					</tr>
	  			</table>
	  		</form>
		</div>
	</div>
</body>
</html>