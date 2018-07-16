<%@ page language="java" contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="model.entity.Role"%>
<%@ page import="model.entity.User"%>
<%@ page import="java.util.List"%>
<% User u=(User)request.getAttribute("user"); %>
<% List<Role> roles =(List<Role>)request.getAttribute("listaRoles");%>
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
	  		<p>EDIT USER</p>
	  		<form method="get" action="/users/edit">
	  			<input type="hidden" name="action" value="Edit"/>
				<input type="hidden" name="usersId" value="<%= u.getId() %>"/>
	  			<table id="tabla">
	  				<tr >
					<td bgcolor="#4caf50">Nombre</td>
					<td ><input type="input" name="nombre" value="<%= u.getNombre() %>" required></td>
					</tr>
					
					<tr >
					<td bgcolor="#4caf50">Rol</td>
					<td >
					<%if(roles.size()>0){ %>
					<select name="rol">
					<% for (int i = 0;i<roles.size();i++) { %>
						<% Role a = (Role)roles.get(i); %>
							<%if( u.getRol().equals(a.getNombre())) { %>
						  <option value="<%=a.getNombre()%>" selected><%=a.getNombre()%></option>
							<%}else {%>
							<option value="<%=a.getNombre()%>" ><%=a.getNombre()%></option>
							
					<%} %>
				<%} %>
						</select>
						<%}else{ %>
						<select name="rol">
						<option value="null">No hay Roles</option>
						</select>
						<%} %>
					</td>
					</tr>
					
					<tr >
					<td bgcolor="#4caf50">Correo</td>
					<td ><input type="input" name="correo" value="<%= u.getCorreo() %>" required></td>
					</tr>
					
					<tr >
					<td bgcolor="#4caf50">Birth</td>
					<td ><input type="date" name="birth" value="<%= u.getBirth() %>" required></td>
					</tr>
					
					<tr >
					<td bgcolor="#4caf50">Sexo</td>
					<td >
					<%if(u.getSexo().equals("Masculino")){ %>
					<input type="radio" name="sexo" value="Masculino" checked> Masculino<br>
 					<input type="radio" name="sexo" value="Femenino"> Femenino<br></td>
 					<%}else{ %>
 					<input type="radio" name="sexo" value="Masculino" > Masculino<br>
 					<input type="radio" name="sexo" value="Femenino" checked> Femenino<br></td>
 					<%} %>
					</tr>
					
					<tr>
					<td align="center" bgcolor="#4caf50"><input type="submit" value="Listo"></td>
					</tr>
	  			</table>
	  		</form>
		</div>
	</div>
</body>
</html>
