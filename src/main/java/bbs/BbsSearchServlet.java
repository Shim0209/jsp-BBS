package bbs;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BbsSearchServlet")
public class BbsSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String bbsTitle = request.getParameter("bbsTitle");
		response.getWriter().write(getJSON(bbsTitle));
	}
	
	public String getJSON(String bbsTitle) {
		if(bbsTitle == null) {
			bbsTitle = "";
		}
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		BbsDAO bbsDAO = new BbsDAO();
		ArrayList<Bbs> bbsList = bbsDAO.searchByTitle(bbsTitle);
		for(int i=0; i<bbsList.size(); i++) {
			result.append("[{\"value\": \"" + bbsList.get(i).getBbsID() + "\"},");
			result.append("{\"value\": \"" + bbsList.get(i).getBbsTitle() + "\"},");
			result.append("{\"value\": \"" + bbsList.get(i).getUserID() + "\"},");
			result.append("{\"value\": \"" + bbsList.get(i).getBbsDate() + "\"}],");
		}
		result.append("]}");
		System.out.println(result);
		return result.toString();
	}

}













