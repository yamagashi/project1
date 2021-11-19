package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccessDAO;

@SuppressWarnings("serial")
@WebServlet(name = "DeleteServlet", urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			// 
			String email = request.getParameter("email");

			// 
			AccessDAO ad = new AccessDAO();
			//  
			ad.deleteUserInfo(email);
			// 
			response.sendRedirect(request.getContextPath() + "/findall");
		} catch (SQLException e) {
			e.printStackTrace();
			// WEB-INF/error.jsp
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request,
					response);
		}
	}
}

