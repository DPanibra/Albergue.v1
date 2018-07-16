package controller.access;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import controller.PMF;
import model.entity.Access;

@SuppressWarnings("serial")
public class AccessControllerDelete extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query q =pm.newQuery(Access.class);
		
		Key k = KeyFactory.createKey(Access.class.getSimpleName(), new Long(request.getParameter("accessId")).longValue());
		Access a = pm.getObjectById(Access.class, k);;
		try{
			pm.deletePersistentAll(a);
			response.sendRedirect("/access");
		}catch(Exception e){
				System.out.println(e);
				response.getWriter().println("No se han podido borrar el Access.");
		}finally{
			q.closeAll();
			pm.close();
		}
	}
}