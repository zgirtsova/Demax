package helpers;

import java.util.HashMap;

public class SortParam {

    private static final HashMap<String, String> columnNames = new HashMap<>() {{
        put(SortParamNames.DATE, "date_created");
        put(SortParamNames.QUANTITY, "quantity");
        put(SortParamNames.PRODUCTS, "products_count");
    }};

    private String name;

    private String column;

    private boolean ascending;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        this.setColumn(name);
    }

    public String getColumn() {
        return this.column;
    }

    private void setColumn(String name) {
        this.column = columnNames.get(name);
    }

    public boolean isAscending() {
        return this.ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }
}
