package controller;

import dao.CategoryDAO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(loadOnStartup = 1)
public class ControllerServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        CategoryDAO categoryDAO = new CategoryDAO();
        getServletContext().setAttribute("categories", categoryDAO.getCategories());
    }
}
