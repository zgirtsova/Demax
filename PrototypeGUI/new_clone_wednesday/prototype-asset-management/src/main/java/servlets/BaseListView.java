package servlets;

import helpers.Filter;
import helpers.FilterNames;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

public abstract class BaseListView extends HttpServlet {

    protected String parseURL(String tableName, String pageParam, String projectParam, String statusParam, String dateFromParam, String dateToParam) {
        String url = String.format("%s?page=%s&results=%s", tableName, pageParam, projectParam);

        if (statusParam != null && !statusParam.isEmpty() && !statusParam.equals("0")) {
            url += "&status=" + statusParam;
        }

        if (dateFromParam != null && !dateFromParam.isEmpty()) {
            url += "&date-from=" + dateFromParam;
        }

        if (statusParam != null && !dateToParam.isEmpty()) {
            url += "&date-to=" + dateToParam;
        }

        return url;
    }

    protected List<Filter> getFilters(Map<String, String> parameters) {

        List<Filter> filters = new ArrayList<>();

        String dateFromParam = parameters.get("date-from");
        String dateToParam = parameters.get("date-to");
        String status = parameters.get("status");

        if (dateFromParam != null) {

            LocalDateTime dateFrom = this.getDate(dateFromParam);

            Filter<LocalDateTime> filter = new Filter<>();
            filter.setName(FilterNames.DATE_FROM);
            filter.setValue(dateFrom);

            filters.add(filter);
        }

        if (dateToParam != null) {

            LocalDateTime dateTo = this.getDate(dateToParam);

            Filter<LocalDateTime> filter = new Filter<>();
            filter.setName(FilterNames.DATE_TO);
            filter.setValue(dateTo);

            filters.add(filter);
        }

        if (status != null) {

            Filter<Integer> filter = new Filter<>();

            int statusId = Integer.parseInt(status);
            filter.setName(FilterNames.STATUS_ID);
            filter.setValue(statusId);

            filters.add(filter);
        }

        return filters.size() == 0 ? null : filters;
    }

    protected LocalDateTime getDate(String dateParam) {

        int[] dateFromParams = Arrays.stream(dateParam.split("-")).mapToInt(Integer::parseInt).toArray();

        return LocalDateTime.of(dateFromParams[0], dateFromParams[1], dateFromParams[2], 0, 0);

    }

    protected Map<String, String> getParametersMap(HttpServletRequest req) {

        Map<String, String> params = new HashMap<>();

        Enumeration<String> parameterNames = req.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = req.getParameter(paramName);

            params.put(paramName, paramValue);
        }

        return params;
    }
}
