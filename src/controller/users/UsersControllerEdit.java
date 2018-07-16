package controller.users;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import controller.PMF;
import model.entity.User;
import model.entity.Role;

@SuppressWarnings("serial")
public class UsersControllerEdit extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query q =pm.newQuery(User.class);
		final Query f =pm.newQuery(Role.class);
		
		if(request.getParameter("action").equals("edit")){
			@SuppressWarnings("unchecked")
			List<Role> listRoles = (List<Role>) f.execute();
			Key k = KeyFactory.createKey(User.class.getSimpleName(), new Long(request.getParameter("usersId")).longValue());
			User a = pm.getObjectById(User.class, k);
			request.setAttribute("user", a);
			request.setAttribute("listaRoles", listRoles);
			RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/WEB-INF/Views/Users/edit.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("action").equals("Edit")){
			Key k = KeyFactory.createKey(User.class.getSimpleName(), new Long(request.getParameter("usersId")).longValue());
			User a = pm.getObjectById(User.class, k);
			a.setNombre((String)request.getParameter("nombre"));
			a.setCorreo((String)request.getParameter("correo"));
			a.setRol((String)request.getParameter("rol"));
			a.setBirth((String)request.getParameter("birth"));
			a.setSexo((String)request.getParameter("sexo"));
			
			try {pm.makePersistent(a);}
			finally {pm.close();}
			response.sendRedirect("/users");
		}
	}
}