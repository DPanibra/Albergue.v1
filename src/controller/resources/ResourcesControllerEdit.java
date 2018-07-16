package controller.resources;

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
import model.entity.Resource;

@SuppressWarnings("serial")
public class ResourcesControllerEdit extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query q =pm.newQuery(Resource.class);
		
		if(request.getParameter("action").equals("edit")){
			Key k = KeyFactory.createKey(Resource.class.getSimpleName(), new Long(request.getParameter("resourcesId")).longValue());
			Resource a = pm.getObjectById(Resource.class, k);
			request.setAttribute("resource", a);
			RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/WEB-INF/Views/Resources/edit.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("action").equals("Edit")){
			Key k = KeyFactory.createKey(Resource.class.getSimpleName(), new Long(request.getParameter("resourcesId")).longValue());
			Resource a = pm.getObjectById(Resource.class, k);
			a.setNombre((String)request.getParameter("nombre"));
			a.setStatus((String)request.getParameter("status"));
			try {pm.makePersistent(a);}
			finally {pm.close();}
			response.sendRedirect("/resources");
		}
	}
}