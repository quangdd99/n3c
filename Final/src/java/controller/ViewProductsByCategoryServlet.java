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

public class ViewProductsByCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryID = req.getParameter("categoryID");
        ProductDAO dao = new ProductDAO();
        List<Product> list = dao.viewProductsByCategory(categoryID);
        req.setAttribute("productListByCategory", list);
        RequestDispatcher view = req.getRequestDispatcher("shop-grid-by-category.jsp");
        view.forward(req, resp);
    }
}
