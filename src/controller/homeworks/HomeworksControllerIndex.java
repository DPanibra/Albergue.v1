package controller.homeworks;

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
import model.entity.Homework;
import model.entity.User;
import model.entity.Resource;
import model.entity.Access;

@SuppressWarnings("serial")
public class HomeworksControllerIndex extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query q =pm.newQuery(Homework.class);
		
		com.google.appengine.api.users.User uGoogle=UserServiceFactory.getUserService().getCurrentUser();
		
		if(uGoogle==null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/deny1.jsp");
			rd.forward(request, response);
		}
		else{
			String query="select from "+User.class.getName()+" where correo=='"+uGoogle.getEmail()+"'";
			
			List<User> uSearch=(List<User>) pm.newQuery(query).execute();
			
			if(uSearch.isEmpty()){
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/deny2.jsp");
				rd.forward(request, response);
			}
			else{
				String query2="select from "+Resource.class.getName()+" where nombre=='"+request.getServletPath()+"'"+" && status=='true'";
				
				List<Resource> rSearch = (List<Resource>) pm.newQuery(query2).execute();
				if(rSearch.isEmpty()){
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/deny3.jsp");
					rd.forward(request, response);
				}
				else{
					String query3="select from "+Access.class.getName()+
							" where rol=='"+uSearch.get(0).getRol()+
							"' && resource=='"+rSearch.get(0).getNombre()+
							"' && status=='true'";
					List<Access> aSearch = (List<Access>) pm.newQuery(query3).execute();
					if(aSearch.isEmpty()){
						RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/deny4.jsp");
						rd.forward(request, response);
					}
					else{
						try{
							List<Homework> listHomeworks = (List<Homework>) q.execute();
							request.setAttribute("listaHomeworks", listHomeworks);
							RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Homeworks/index.jsp");
							rd.forward(request, response);
						}catch(Exception e){
							System.out.println(e);
						}finally{
							q.closeAll();
							pm.close();
						}
					}
				}
			}
		}
		
		
		
	}
}