package controller.resources;

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
import model.entity.Resource;

@SuppressWarnings("serial")
public class ResourcesControllerDelete extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query q =pm.newQuery(Resource.class);
		
		Key k = KeyFactory.createKey(Resource.class.getSimpleName(), new Long(request.getParameter("resourcesId")).longValue());
		Resource a = pm.getObjectById(Resource.class, k);;
		try{
			pm.deletePersistentAll(a);
			response.sendRedirect("/resources");
		}catch(Exception e){
				System.out.println(e);
				response.getWriter().println("No se han podido borrar el rol.");
		}finally{
			q.closeAll();
			pm.close();
		}
	}
}