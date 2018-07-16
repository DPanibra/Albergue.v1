package controller.users;

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
public class UsersControllerAdd extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query q =pm.newQuery(User.class);
		final Query f =pm.newQuery(Role.class);
		

						if(request.getParameterMap().isEmpty()){
							try{
								
								List<Role> listRoles = (List<Role>) f.execute();
								request.setAttribute("listaRoles", listRoles);
								RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Users/add.jsp");
								rd.forward(request, response);
							}catch(Exception e){
								System.out.println(e);
							}finally{
								f.closeAll();
								pm.close();
							}
						}
						else{
							
							User a = new User(
									(String)request.getParameter("nombre"),
									(String)request.getParameter("correo"),
									(String)request.getParameter("birth"),
									(String)request.getParameter("sexo"),
									(String)request.getParameter("rol"));
								try {pm.makePersistent(a);}
								finally {pm.close();}
								response.sendRedirect("/users");
						}
					
		
	}
}
