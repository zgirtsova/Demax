package servlets;

import dtos.pictures.PictureDTO;
import dtos.products.NewProductDTO;
import dtos.projects.ProjectNameDTO;
import services.ProductService;
import services.ProjectService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;

@MultipartConfig
@WebServlet("/new-product")
public class NewProductServlet extends HttpServlet {

    private ProductService productService = new ProductService();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProjectService projectService = new ProjectService();

        List<ProjectNameDTO> projectsNames = projectService.getProjectsNames();
        req.setAttribute("projectsNames", projectsNames);


        resp.setContentType("text/html");
        req.getRequestDispatcher("new-product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String productName = req.getParameter("product-name");
        String serialNumber = req.getParameter("serial-number");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String description = req.getParameter("description");
        Part part = req.getPart("picture");
        int projectId = Integer.parseInt(req.getParameter("project-id"));

        byte[] picture = part.getInputStream().readAllBytes();
        String fileName = part.getSubmittedFileName();

        PictureDTO pictureDTO = new PictureDTO();
        pictureDTO.setPicture(picture);
        pictureDTO.setFileName(fileName);

        NewProductDTO newProductDTO = new NewProductDTO();
        newProductDTO.setProductName(productName);
        newProductDTO.setSerialNumber(serialNumber);
        newProductDTO.setQuantity(quantity);
        newProductDTO.setDescription(description);
        newProductDTO.setProjectId(projectId);
        newProductDTO.setStatusId(1);
        newProductDTO.setDateCreated(LocalDateTime.now());

        productService.addNewProduct(newProductDTO, pictureDTO);

        resp.sendRedirect("products");
    }
}