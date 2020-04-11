import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/Signup")
public class Signup extends HttpServlet {
    Connection con =null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name");
        String phno=request.getParameter("phno");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        System.out.println(name+phno+email+password);
        connect(name,phno,email,password);
        response.sendRedirect("Login.html");

    }
    //connect

    public void connect(String name,String phno,String email,String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gendata", "root", "Y@24giri");
            PreparedStatement pr=con.prepareStatement("INSERT INTO signupdata VALUES (?,?,?,?)");
            pr.setString(1,name);
            pr.setString(2,phno);
            pr.setString(3,email);
            pr.setString(4,password);
            pr.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
