package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import model.Category;

public class CategoryDAO {

    public List<Category> getCategories() {
        try {
            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/quanly");
            Connection conn = ds.getConnection();
            Statement sttm = conn.createStatement();
            String sql = "SELECT * FROM category";
            ResultSet rs = sttm.executeQuery(sql);
            List<Category> categories = new ArrayList<>();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryID(rs.getString("category_id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
            return categories;
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (NamingException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
