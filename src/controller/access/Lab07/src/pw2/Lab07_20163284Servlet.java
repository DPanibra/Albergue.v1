package pw2;
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
@SuppressWarnings("serial")
public class Lab07_20163284Servlet extends HttpServlet {
	
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query q =pm.newQuery(Tareas.class);
		
		if(request.getParameter("action").equals("indexTarea")){
			try{
				@SuppressWarnings("unchecked")
				List<Tareas> listTareas = (List<Tareas>) q.execute();
				request.setAttribute("listaTareas", listTareas);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/indexTareas.jsp");
				rd.forward(request, response);
			}catch(Exception e){
				System.out.println(e);
			}finally{
				q.closeAll();
				pm.close();
			}
		}
		
		else if(request.getParameter("action").equals("Ed/El")) {
			Key k = KeyFactory.createKey(Tareas.class.getSimpleName(), new Long(request.getParameter("tareaId")).longValue());
			Tareas a = pm.getObjectById(Tareas.class, k);
			request.setAttribute("tarea", a);
			RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edEl.jsp");
			dispatcher.forward(request, response);
		} 
		
		else if(request.getParameter("action").equals("addTarea")) {
			response.sendRedirect("/addTarea.jsp");
		} 
		
		else if(request.getParameter("action").equals("addTareaDo")) {
			Tareas a = new Tareas(
			request.getParameter("tema"),
			request.getParameter("curso"),
			request.getParameter("alumno"),
			request.getParameter("profesor"));
			try {pm.makePersistent(a);}
			finally {pm.close();}
			response.sendRedirect("/lab07?action=indexTarea");
		}
		
		else if(request.getParameter("action").equals("Eliminar")){
			
			Key k = KeyFactory.createKey(Tareas.class.getSimpleName(), new Long(request.getParameter("tareaId")).longValue());
			Tareas a = pm.getObjectById(Tareas.class, k);;
			try{
				pm.deletePersistentAll(a);
				response.sendRedirect("/lab07?action=indexTarea");
			}catch(Exception e){
					System.out.println(e);
					response.getWriter().println("No se han podido borrar personas.");
			}finally{
				q.closeAll();
				pm.close();
			}
		}
		else if(request.getParameter("action").equals("Editar")){
					
			Key k = KeyFactory.createKey(Tareas.class.getSimpleName(), new Long(request.getParameter("tareaId")).longValue());
			Tareas a = pm.getObjectById(Tareas.class, k);
			request.setAttribute("tarea", a);
			RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/Editar.jsp");
			dispatcher.forward(request, response);
		}
		
		else if(request.getParameter("action").equals("EditarDo")){
			Key k = KeyFactory.createKey(Tareas.class.getSimpleName(), new Long(request.getParameter("tareaId")).longValue());
			Tareas a = pm.getObjectById(Tareas.class, k);
			
				a.setTema((String)request.getParameter("tema"));
				a.setCurso((String)request.getParameter("curso"));
				a.setAlumno((String)request.getParameter("alumno"));
				a.setProfesor((String)request.getParameter("profesor"));
				response.sendRedirect("/lab07?action=indexTarea");
				try {pm.makePersistent(a);}
				finally {pm.close();}
				response.sendRedirect("/lab07?action=indexTarea");
			
		}
		
		
	}
}
