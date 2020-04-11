import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/Login")
public class Login extends HttpServlet {

    Connection con = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int fg=connect(email,password);
        if(fg==1)
        {
            response.sendRedirect("Success.html");
        }
        else
        {
            response.sendRedirect("Unsuccess.html");
        }
    }

    public int connect(String email, String password) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gendata", "root", "Y@24giri");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT email,password FROM signupdata");
            while(rs.next())
            {
                if(rs.getString("email").equals(email) && rs.getString("password").equals(password)){
                    return 1;

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
}