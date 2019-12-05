package controller;

import dao.ProductDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;

public class ViewSingleProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productID = req.getParameter("singleProductID");
        ProductDAO dao = new ProductDAO();
        Product product = dao.viewSingleProduct(productID);
        List<Product> list = dao.viewRelatedBooks(productID);
        req.setAttribute("singleProduct", product);
        req.setAttribute("relatedProductList", list);
        RequestDispatcher view = req.getRequestDispatcher("single-product.jsp");
        view.forward(req, resp);
    }
}
