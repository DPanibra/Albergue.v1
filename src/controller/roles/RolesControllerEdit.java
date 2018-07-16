package controller.roles;

import java.io.IOException;

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
import model.entity.Role;

@SuppressWarnings("serial")
public class RolesControllerEdit extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query q =pm.newQuery(Role.class);
		
		if(request.getParameter("action").equals("edit")){
			Key k = KeyFactory.createKey(Role.class.getSimpleName(), new Long(request.getParameter("rolesId")).longValue());
			Role a = pm.getObjectById(Role.class, k);
			request.setAttribute("rol", a);
			RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/WEB-INF/Views/Roles/edit.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("action").equals("Edit")){
			Key k = KeyFactory.createKey(Role.class.getSimpleName(), new Long(request.getParameter("rolesId")).longValue());
			Role a = pm.getObjectById(Role.class, k);
			a.setNombre((String)request.getParameter("nombre"));
			try {pm.makePersistent(a);}
			finally {pm.close();}
			response.sendRedirect("/roles");
		}
	}
}