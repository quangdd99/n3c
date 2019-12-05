package controller;

import dao.ProductDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Item;
import model.Order;
import model.Product;

public class AddToCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int quantity = 1;
        String id;
        if (request.getParameter("productID") != null) {
            id = request.getParameter("productID");
            ProductDAO dao = new ProductDAO();
            Product product = dao.viewSingleProduct(id);
            if (product != null) {
                if (request.getParameter("quantity") != null) {
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                }
                HttpSession session = request.getSession();
                if (session.getAttribute("order") == null) {
                    Order order = new Order();
                    List<Item> listItems = new ArrayList<Item>();
                    Item item = new Item();
                    item.setProduct(product);
                    item.setQuantity(quantity);
                    item.setPrice(product.getPriceSale());
                    listItems.add(item);
                    order.setItems(listItems);
                    session.setAttribute("order", order);
                } else {
                    Order order = (Order) session.getAttribute("order");
                    List<Item> listItems = order.getItems();
                    boolean check = false;
                    for (Item item : listItems) {
                        if (item.getProduct().getProductID().equals(product.getProductID())) {
                            item.setQuantity(item.getQuantity() + quantity);
                            check = true;
                        }
                    }
                    if (check == false) {
                        Item item = new Item();
                        item.setProduct(product);
                        item.setQuantity(quantity);
                        item.setPrice(product.getPriceSale());
                        listItems.add(item);
                    }
                    session.setAttribute("order", order);
                }
            }
            response.sendRedirect(request.getContextPath() + "/CartServlet");
        } else {
            response.sendRedirect(request.getContextPath() + "/CartServlet");
        }
    }
}
