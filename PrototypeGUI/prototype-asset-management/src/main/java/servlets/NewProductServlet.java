package servlets;

import services.ProductService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/new-product")
public class NewProductServlet extends HttpServlet {

    private ProductService productService = new ProductService();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        req.getRequestDispatcher("new-product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String productName = req.getParameter("product-name");
        String serialNumber = req.getParameter("serial-number");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String description = req.getParameter("description");

    }
}