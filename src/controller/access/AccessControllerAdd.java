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

import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.User;
import model.entity.Access;
import model.entity.Role;
import model.entity.Resource;

@SuppressWarnings("serial")
public class AccessControllerAdd extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query q =pm.newQuery(Access.class);
		final Query rol =pm.newQuery(Role.class);
		final Query res =pm.newQuery(Resource.class);
		

						if(request.getParameterMap().isEmpty()){
							List<Role> listRoles = (List<Role>) rol.execute();
							List<Resource> listResources = (List<Resource>) res.execute();
							request.setAttribute("listaRoles", listRoles);
							request.setAttribute("listaResources", listResources);
							RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Access/add.jsp");
							rd.forward(request, response);
						}
						else{
							Access a = new Access(
								(String)request.getParameter("rol"),
								(String)request.getParameter("resource"));
								try {pm.makePersistent(a);}
								finally {pm.close();}
								response.sendRedirect("/access");
						}
					
				
		
	}
}
