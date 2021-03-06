package ekart;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import ekart.getResultset;

@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	static final String url="jdbc:mysql://localhost:3306/eshop";
	static final String uname="root";
	static final String pass="Ashok@669061";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s=request.getSession(true);
		int name=(int)s.getAttribute("userid");
		int id=Integer.parseInt(request.getParameter("id"));
		int qty=Integer.parseInt(request.getParameter("qty"));
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,uname,pass);
		PreparedStatement p=con.prepareStatement("insert into cart values(?,?,?)");
		p.setInt(1, name);
		p.setInt(2, id);
		p.setInt(3, qty);
		p.executeUpdate();
		JSONObject json=new JSONObject();
		json.put("Success", "True");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
