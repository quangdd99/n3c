package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import model.User;

public class UserDAO {

    public User getOneUser(String email) {
        User u = new User();
        try {
            Context initContext = new InitialContext();
                    DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/quanly");
            Connection conn = ds.getConnection();
            Statement sttm = conn.createStatement();
            String sql = "SELECT * FROM users WHERE email = '" + email + "'";
            ResultSet rs = sttm.executeQuery(sql);
            if (rs.next()) {
                u.setUserID(rs.getInt("user_id"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setIsAdmin(rs.getBoolean("is_admin"));
                return u;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (NamingException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean validate(String email, String password) {
        User u = this.getOneUser(email);
        if (u == null) {
            return false;
        } else if (u.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
