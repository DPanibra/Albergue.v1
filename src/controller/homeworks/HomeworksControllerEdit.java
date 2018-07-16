package controller.homeworks;

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
import model.entity.Homework;

@SuppressWarnings("serial")
public class HomeworksControllerEdit extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query q =pm.newQuery(Homework.class);
		
		if(request.getParameter("action").equals("edit")){
			Key k = KeyFactory.createKey(Homework.class.getSimpleName(), new Long(request.getParameter("homeworksId")).longValue());
			Homework a = pm.getObjectById(Homework.class, k);
			request.setAttribute("rol", a);
			RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/WEB-INF/Views/Homeworks/edit.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("action").equals("Edit")){
			Key k = KeyFactory.createKey(Homework.class.getSimpleName(), new Long(request.getParameter("homeworksId")).longValue());
			Homework a = pm.getObjectById(Homework.class, k);
			a.setAlumno((String)request.getParameter("alumno"));
			a.setTema((String)request.getParameter("tema"));
			a.setCurso((String)request.getParameter("curso"));
			a.setProfesor((String)request.getParameter("profesor"));
			try {pm.makePersistent(a);}
			finally {pm.close();}
			response.sendRedirect("/homeworks");
		}
	}
}