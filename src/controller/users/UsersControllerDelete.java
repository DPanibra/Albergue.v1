package controller.users;

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
import model.entity.User;

@SuppressWarnings("serial")
public class UsersControllerDelete extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query q =pm.newQuery(User.class);
		
		Key k = KeyFactory.createKey(User.class.getSimpleName(), new Long(request.getParameter("usersId")).longValue());
		User a = pm.getObjectById(User.class, k);;
		try{
			pm.deletePersistentAll(a);
			response.sendRedirect("/users");
		}catch(Exception e){
				System.out.println(e);
				response.getWriter().println("No se han podido borrar el user.");
		}finally{
			q.closeAll();
			pm.close();
		}
	}
}