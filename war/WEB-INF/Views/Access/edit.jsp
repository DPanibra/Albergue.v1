<%@ page language="java" contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="model.entity.Role"%>
<%@ page import="model.entity.Resource"%>
<%@ page import="model.entity.Access"%>
<%@ page import="java.util.List"%>
<% List<Role> roles =(List<Role>)request.getAttribute("listaRoles");%>
<% List<Resource> resources =(List<Resource>)request.getAttribute("listaResources");%>
<% Access ac=(Access)request.getAttribute("access"); %>
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
	  		<p>ADD ACCESS</p>
	  		<form method="get" action="/access/edit">
	  			<table id="tabla">
					<input type="hidden" name="action" value="Edit"/>
					<input type="hidden" name="accessId" value="<%= ac.getId() %>"/>
					<tr >
					<td bgcolor="#4caf50">Rol</td>
					<td >
				<%if(roles.size()>0){ %>
					<select name="rol">
					<% for (int i = 0;i<roles.size();i++) { %>
						<% Role a = (Role)roles.get(i); %>
							<%if( ac.getRol().equals(a.getNombre())) { %>
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
					<td bgcolor="#4caf50">Resources</td>
					<td >
					<%if(resources.size()>0){ %>
						<select name="resource">
						<% for (int i = 0;i<resources.size();i++) { %>
						<% Resource b = (Resource)resources.get(i); %>
						  <%if( ac.getResource().equals(b.getNombre())) { %>
						  <option value="<%=b.getNombre()%>" selected><%=b.getNombre()%></option>
							<%}else {%>
							<option value="<%=b.getNombre()%>" ><%=b.getNombre()%></option>
							
					<%} %>
				<%} %>
						</select>
						<%}else{ %>
						<select name="rol">
						<option value="null">No hay Recursos</option>
						</select>
						<%} %>
					</td>
					</tr>
					<tr >
					<td bgcolor="#4caf50">Status</td>
					<td > 
					<%if(ac.getStatus().equals("true")){ %>
					<input type="radio" name="status" value="true" checked> True<br>
 					<input type="radio" name="status" value="false"> False<br></td>
 					<%}else{ %>
 					<input type="radio" name="status" value="true" > True<br>
 					<input type="radio" name="status" value="false" checked> False<br></td>
 					<%} %>
 					</td>
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