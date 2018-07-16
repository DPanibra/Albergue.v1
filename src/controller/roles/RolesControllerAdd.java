package controller.roles;

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
import model.entity.Access;
import model.entity.Resource;
import model.entity.Role;
import model.entity.User;

@SuppressWarnings("serial")
public class RolesControllerAdd extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query q =pm.newQuery(Role.class);
		

						if(request.getParameterMap().isEmpty()){
							RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Roles/add.jsp");
							rd.forward(request, response);
						}
						else{
							Role a = new Role(request.getParameter("nombre"));
								try {pm.makePersistent(a);}
								finally {pm.close();}
								response.sendRedirect("/roles");
						}
					
	}
}
