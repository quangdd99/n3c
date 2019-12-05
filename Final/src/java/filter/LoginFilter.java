package filter;

import dao.UserDAO;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/CartServlet", "/CheckLoginServlet"})
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        HttpSession httpSession = req.getSession();

        Object obj = httpSession.getAttribute("email");

        if (obj != null) {
            chain.doFilter(request, response);
        } else {
            UserDAO usersDAO = new UserDAO();
            if (email != null && password != null && usersDAO.validate(email, password)) {
                httpSession.setAttribute("email", email);
                chain.doFilter(request, response);
            } else {
                resp.sendRedirect("/Final/my-account.jsp");
            }
        }
    }

    @Override
    public void destroy() {
    }
}
