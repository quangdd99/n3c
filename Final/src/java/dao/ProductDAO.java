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
import model.Product;

public class ProductDAO {

    private int noOfRecords;

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }

    public List<Product> viewAllProducts(int offset, int noOfRecords) {
        int x = offset - 1;
        List<Product> list = new ArrayList<>();
        try {
            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/quanly");
            Connection conn = ds.getConnection();
            Statement sttm = conn.createStatement();
            String sql = "SELECT * FROM product";
            ResultSet rs = sttm.executeQuery(sql);
            int i = 0;
            int j = 0;
            while (rs.next()) {
                i++;
                if (i >= offset && j < noOfRecords) {
                    j++;
                    Product product = new Product();
                    product.setProductID(rs.getString("product_id"));
                    product.setName(rs.getString("name"));
                    product.setImage(rs.getString("image"));
                    product.setPrice(rs.getFloat("price"));
                    product.setPriceSale(rs.getFloat("price_sale"));
                    product.setDiscount(rs.getInt("discount"));
                    product.setAuthor(rs.getString("author"));
                    product.setPublisher(rs.getString("publisher"));
                    product.setDetail(rs.getString("detail"));
                    product.setCategoryID(rs.getString("category_id"));
                    list.add(product);
                }
            }
            setNoOfRecords(i);
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (NamingException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Product> viewProductsByCategory(String categoryID) {

        List<Product> list = new ArrayList<>();
        try {
            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/quanly");
            Connection conn = ds.getConnection();
            Statement sttm = conn.createStatement();
            String sql = "SELECT * FROM product WHERE category_id = '" + categoryID + "'";
            ResultSet rs = sttm.executeQuery(sql);
            while (rs.next()) {
                Product product = new Product();
                product.setProductID(rs.getString("product_id"));
                product.setName(rs.getString("name"));
                product.setImage(rs.getString("image"));
                product.setPrice(rs.getFloat("price"));
                product.setPriceSale(rs.getFloat("price_sale"));
                product.setDiscount(rs.getInt("discount"));
                product.setAuthor(rs.getString("author"));
                product.setPublisher(rs.getString("publisher"));
                product.setDetail(rs.getString("detail"));
                product.setCategoryID(rs.getString("category_id"));
                list.add(product);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (NamingException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Product viewSingleProduct(String productID) {

        Product product = new Product();
        try {
            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/quanly");
            Connection conn = ds.getConnection();
            Statement sttm = conn.createStatement();
            String sql = "SELECT * FROM product WHERE product_id = '" + productID + "'";
            ResultSet rs = sttm.executeQuery(sql);
            while (rs.next()) {
                product.setProductID(rs.getString("product_id"));
                product.setName(rs.getString("name"));
                product.setImage(rs.getString("image"));
                product.setPrice(rs.getFloat("price"));
                product.setPriceSale(rs.getFloat("price_sale"));
                product.setDiscount(rs.getInt("discount"));
                product.setAuthor(rs.getString("author"));
                product.setPublisher(rs.getString("publisher"));
                product.setDetail(rs.getString("detail"));
                product.setCategoryID(rs.getString("category_id"));
            }
            return product;
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (NamingException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Product> viewRelatedBooks(String productID) {

        String categoryID = productID.substring(0, 1);
        List<Product> list = new ArrayList<>();
        try {
            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/quanly");
            Connection conn = ds.getConnection();
            Statement sttm = conn.createStatement();
            String sql = "SELECT * FROM product WHERE category_id = '" + categoryID + "' AND product_id != '" + productID + "'";
            ResultSet rs = sttm.executeQuery(sql);
            while (rs.next()) {
                Product product = new Product();
                product.setProductID(rs.getString("product_id"));
                product.setName(rs.getString("name"));
                product.setImage(rs.getString("image"));
                product.setPrice(rs.getFloat("price"));
                product.setPriceSale(rs.getFloat("price_sale"));
                product.setDiscount(rs.getInt("discount"));
                product.setAuthor(rs.getString("author"));
                product.setPublisher(rs.getString("publisher"));
                product.setDetail(rs.getString("detail"));
                product.setCategoryID(rs.getString("category_id"));
                list.add(product);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (NamingException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
