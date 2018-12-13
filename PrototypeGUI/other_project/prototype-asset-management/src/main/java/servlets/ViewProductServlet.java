package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import orm.dto.NewProductDto;
import orm.dto.PictureDto;
import orm.dto.ViewProductDto;
import services.PictureService;
import services.ProductService;

@MultipartConfig
public class ViewProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ProductService productService;
	private static PictureService pictureService;
	/**
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewProductServlet() throws ClassNotFoundException, SQLException {
		super();
		productService = ProductService.getProductServiceInstance();
		pictureService = PictureService.getPictureServiceInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			ViewProductDto productDto = productService.getProductById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("productDto", productDto);
		} catch (NumberFormatException | SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/views/products/viewProduct.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getPart("image") != null) {
			Part part = request.getPart("image");
			byte[] imageBytes = part.getInputStream().readAllBytes();
			String picture = new String(Base64.getEncoder().encode(imageBytes));
			PictureDto pictureDto = new PictureDto();
			pictureDto.setPicture(picture);
			pictureDto.setProductId(Integer.parseInt(request.getParameter("id")));

			try {
				pictureService.createPicture(pictureDto);
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else if (request.getParameter("deletePicture") != null) {
			try {
				pictureService.deletePicture(request.getParameter("deletePicture"),
						Integer.parseInt(request.getParameter("id")));

			} catch (NumberFormatException | SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			NewProductDto newProductDto = new NewProductDto();
			newProductDto.setProductName(request.getParameter("productName"));
			newProductDto.setSerialNumber(request.getParameter("serialNumber"));
			newProductDto.setQuantity(Integer.parseInt(request.getParameter("quantity")));
			newProductDto.setProjectName(request.getParameter("projects"));
			newProductDto.setStatus(request.getParameter("status"));
			newProductDto.setDescription(request.getParameter("description"));

			try {
				productService.updateProduct(newProductDto, Integer.parseInt(request.getParameter("id")));
			} catch (NumberFormatException | SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		doGet(request, response);
	}
}
