package controller.access;

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
import model.entity.Access;
import model.entity.Resource;
import model.entity.Role;

@SuppressWarnings("serial")
public class AccessControllerEdit extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query q =pm.newQuery(Access.class);
		final Query rol =pm.newQuery(Role.class);
		final Query res =pm.newQuery(Resource.class);
		
		if(request.getParameter("action").equals("edit")){
			Key k = KeyFactory.createKey(Access.class.getSimpleName(), new Long(request.getParameter("accessId")).longValue());
			Access a = pm.getObjectById(Access.class, k);
			List<Role> listRoles = (List<Role>) rol.execute();
			List<Resource> listResources = (List<Resource>) res.execute();
			request.setAttribute("listaRoles", listRoles);
			request.setAttribute("listaResources", listResources);
			request.setAttribute("access", a);
			RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/WEB-INF/Views/Access/edit.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("action").equals("Edit")){
			Key k = KeyFactory.createKey(Access.class.getSimpleName(), new Long(request.getParameter("accessId")).longValue());
			Access a = pm.getObjectById(Access.class, k);
			a.setRol((String)request.getParameter("rol"));
			a.setResource((String)request.getParameter("resource"));
			a.setStatus((String)request.getParameter("status"));
			try {pm.makePersistent(a);}
			finally {pm.close();}
			response.sendRedirect("/access");
		}
		
	}
}