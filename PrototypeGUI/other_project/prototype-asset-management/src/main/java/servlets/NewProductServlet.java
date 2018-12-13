package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import orm.dto.NewProductDto;
import orm.dto.PictureDto;
import services.ProductService;
import services.ProjectService;

@MultipartConfig
public class NewProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ProjectService projectService;
	private static ProductService productService;
       
    /**
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public NewProductServlet() 
    		throws ClassNotFoundException, SQLException {
        super();
        projectService = ProjectService.getProjectServiceInstance();
        productService = ProductService.getProductServiceInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {			
			List<String> names = projectService.getProjectNames();
			request.setAttribute("names", names);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/views/products/newProduct.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NewProductDto newProductDto = new NewProductDto();
		newProductDto.setProductName(request.getParameter("productName"));
		newProductDto.setSerialNumber(request.getParameter("serialNumber"));
		newProductDto.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		newProductDto.setProjectName(request.getParameter("projects"));
		newProductDto.setStatus(request.getParameter("status"));
		newProductDto.setDescription(request.getParameter("description"));

		Part part = request.getPart("image");
		byte[] imageBytes = part.getInputStream().readAllBytes();
		String picture = new String(Base64.getEncoder().encode(imageBytes));
		PictureDto pictureDto = new PictureDto();
		pictureDto.setPicture(picture);

		try {
			productService.createProduct(newProductDto, pictureDto,
					request.getSession().getAttribute("user").toString());
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		response.sendRedirect(request.getContextPath() + "/products/all");
	}
}
