package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccessDAO;
import error.DuplicateEmailException;
import model.UserInfo;

@SuppressWarnings("serial")
@WebServlet(name = "RegistServlet", urlPatterns = "/regist")
public class RegistServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			//
			String name = request.getParameter("name");
			String yomi = request.getParameter("yomi");
			String zip = request.getParameter("zip");
			String address = request.getParameter("address");
			String tel = request.getParameter("tel");
			String email = request.getParameter("email");

			//
			if (name == null || name.length() < 1) {
				request.setAttribute("message", "名前を入力をしてください");
				RequestDispatcher rd = request
						.getRequestDispatcher("/regist.jsp");
				rd.forward(request, response);
				return;
			}
			if (email == null || email.length() < 1) {
				request.setAttribute("message", "メールアドレスを入力してください");
				RequestDispatcher rd = request
						.getRequestDispatcher("/regist.jsp");
				rd.forward(request, response);
				return;
			}

			// UserInfo
			UserInfo userInfo = new UserInfo();
			userInfo.setName(name);
			userInfo.setYomi(yomi);
			userInfo.setZip(zip);
			userInfo.setAddress(address);
			userInfo.setTel(tel);
			userInfo.setEmail(email);

			//AcccessDAOのインスタンスを作成
			AccessDAO ad = new AccessDAO();
			ad.registUserInfo(userInfo);
			//findall(一覧画面)にリダイレクト
			response.sendRedirect(request.getContextPath() + "/findall");
		} catch (SQLException e) {
			e.printStackTrace();
			// 例外が発生した場合(WEB-INF/error.jsp)へフォワード
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request,
					response);
		} catch (DuplicateEmailException e) {
			request.setAttribute("message","このメールアドレスは既に登録されています" );
			RequestDispatcher rd = request.getRequestDispatcher("/regist.jsp");
			rd.forward(request, response);
		}
	}

}

