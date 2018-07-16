package controller.homeworks;

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
import model.entity.Homework;

@SuppressWarnings("serial")
public class HomeworksControllerDelete extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query q =pm.newQuery(Homework.class);
		
		Key k = KeyFactory.createKey(Homework.class.getSimpleName(), new Long(request.getParameter("homeworksId")).longValue());
		Homework a = pm.getObjectById(Homework.class, k);;
		try{
			pm.deletePersistentAll(a);
			response.sendRedirect("/homeworks");
		}catch(Exception e){
				System.out.println(e);
				response.getWriter().println("No se han podido borrar la tarea.");
		}finally{
			q.closeAll();
			pm.close();
		}
	}
}