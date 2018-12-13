package servlets;

import dtos.products.ProductWithDescriptionDTO;
import dtos.products.ProductsPerPageDTO;
import dtos.projects.ProjectNameDTO;
import helpers.Filter;
import helpers.FilterNames;
import services.ProductService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/products/*")
public class ProductsServlet extends BaseListView {
    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> parameters = super.getParametersMap(req);
        List<Filter> filters = super.getFilters(parameters);

        int page = Integer.parseInt(parameters.get("page"));
        int resultPerPage = Integer.parseInt(parameters.get("results"));

        ProductsPerPageDTO productsPerPageDTO = productService.getProductsPerPage(resultPerPage,page, filters);
        List<ProductWithDescriptionDTO> products = productsPerPageDTO.getProductsPerPage();
        int filteredProductsCount = productsPerPageDTO.getFilteredProductsCount();
        List<ProjectNameDTO> projectsNames = productsPerPageDTO.getProjectsNames();

        req.setAttribute("products", products);
        req.setAttribute("filteredProductsCount", filteredProductsCount);
        req.setAttribute("projectsNames", projectsNames);

        resp.setContentType("text/html");
        req.getRequestDispatcher("products.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pageParam = req.getParameter("page");
        String projectParam = req.getParameter("results");
        String statusParam = req.getParameter("status");
        String dateFromParam = req.getParameter("date-from");

        String dateToParam = req.getParameter("date-to");

        String actionIndexParam = req.getParameter("action-index");
        String[] productsIdsParam = req.getParameterValues("product-id");
        int projectId = Integer.parseInt(req.getParameter("project-id"));


        if (actionIndexParam != null && productsIdsParam != null) {
            int actionIndex = Integer.parseInt(actionIndexParam);
            int[] projectsIds = Arrays.stream(productsIdsParam).mapToInt(Integer::parseInt).toArray();

            this.applyActions(actionIndex, projectsIds);
        }

        String url = parseURL("products", pageParam, projectParam, statusParam, dateFromParam, dateToParam);
        resp.sendRedirect(url);
    }

    private void applyActions(int actionIndex, int[] productsIds) {
        switch (actionIndex) {
            case 1:
            case 2:
            case 3:
                productService.updateProductsStatusesByIds(productsIds, actionIndex);
                break;
            case 4:
                productService.deleteProductsByIds(productsIds);
                break;
        }
    }
}