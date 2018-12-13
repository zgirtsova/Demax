package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import orm.dto.ProductOnePictureWithDescriptionDto;
import orm.dto.ProjectIdNameDto;
import services.ProductService;
import util.CurrentConnection;
import util.Filter;
import util.Status;

public class AllProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ProductService productService;

	public AllProductsServlet() throws ClassNotFoundException, SQLException {
		super();
		productService = ProductService.getProductServiceInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateFrom = request.getParameter("dateFrom");
			Timestamp dateF = null;
			String dateTo = request.getParameter("dateTo");
			Timestamp dateT = null;
			String status = request.getParameter("status");
			Integer page = null;
			Integer numberOfResults = null;
			Integer statusId;
			Integer projectId = null;
			List<ProductOnePictureWithDescriptionDto> productDtos;
			List<ProjectIdNameDto> projectIdsAndNames;

			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException e) {
			}

			try {
				numberOfResults = Integer.parseInt(request.getParameter("numPerPage"));
			} catch (NumberFormatException e) {
			}

			try {
				projectId = Integer.parseInt(request.getParameter("projectId"));
			} catch (NumberFormatException e) {
			}

			if (numberOfResults == null || numberOfResults < 1) {
				numberOfResults = 5;
			}

			if (page == null || page < 1) {
				page = 1;
			}

			if (projectId == null || projectId == 0) {
				projectId = null;
			}

			if (status == null || status.equals("null") || status.equals("All")) {
				statusId = null;
			} else {
				statusId = Status.getStatusIdByName(status);
			}

			try {
				if (dateFrom != null && !dateFrom.equals("null") && !dateFrom.isEmpty()) {
					dateF = new Timestamp(sdf.parse(dateFrom).getTime());
				}

				if (dateTo != null && !dateTo.equals("null") && !dateTo.isEmpty()) {
					dateT = new Timestamp(sdf.parse(dateTo).getTime());
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}

			try {
				
			
				
				Object[] result = productService.getFilteredProductsWithCountAndPagesCount(
						new Filter(dateF, dateT, statusId, projectId, page, numberOfResults, false, false, false));
				productDtos = (List<ProductOnePictureWithDescriptionDto>) result[0];
				projectIdsAndNames = (List<ProjectIdNameDto>) result[1];
				Integer productCount = (Integer) result[2];
				Integer numOfPages = (Integer) result[3];

				request.setAttribute("productDtos", productDtos);
				request.setAttribute("projectIdsAndNames", projectIdsAndNames);
				request.setAttribute("productCount", productCount);
				request.setAttribute("numOfPages", numOfPages);

			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (CurrentConnection.getCurrent() != null) {
					CurrentConnection.getCurrent().close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		request.getRequestDispatcher("/views/products/allProducts.jsp").forward(request, response);
	}

	/**
	 * @throws IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String[] selectedProductIds = request.getParameterValues("boxes");
			switch (request.getParameter("action")) {
			case "Change to New":
				for (int i = 0; i < selectedProductIds.length; i++) {
					try {
						productService.changeProductStatus(Integer.parseInt(selectedProductIds[i]), 1);
					} catch (NumberFormatException | SQLException | ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
				break;
			case "Change to In Progress":
				for (int i = 0; i < selectedProductIds.length; i++) {
					try {
						productService.changeProductStatus(Integer.parseInt(selectedProductIds[i]), 2);

					} catch (NumberFormatException | SQLException | ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
				break;
			case "Change to Finished":
				for (int i = 0; i < selectedProductIds.length; i++) {
					try {
						productService.changeProductStatus(Integer.parseInt(selectedProductIds[i]), 3);
					} catch (NumberFormatException | SQLException | ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
				break;
			case "Delete":
				for (int i = 0; i < selectedProductIds.length; i++) {
					try {
						productService.deleteProductById(Integer.parseInt(selectedProductIds[i]));
					} catch (NumberFormatException | SQLException | ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
				break;
			}
		} finally {
			try {
				if (CurrentConnection.getCurrent() != null) {
					CurrentConnection.getCurrent().close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		doGet(request, response);
	}
}
