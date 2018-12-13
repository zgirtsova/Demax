<%@page import="java.util.List" %>
<%@page import="dtos.projects.ProjectDTO" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN""http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Prototype</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <meta name="company" content="www.demax.bg"/>
    <meta name="title" content="Prototype"/>
    <meta name="description" content="Prototype"/>
    <meta name="copyright" content="www.prototype.com"/>
    <meta name="robots" content="index,follow"/>
    <meta name="robots" content="all"/>
    <meta name="googlebot" content="INDEX,FOLLOW"/>
    <meta name="revisit-after" content="1 days"/>
    <meta http-equiv="imagetoolbar" content="no"/>
    <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1"/>
    <meta name="format-detection" content="telephone=no"/>

    <link rel="shortcut icon" href="./assets/icon/favicon.ico" type="image/x-icon"/>
    <link rel="icon" href="./assets/icon/favicon.ico" type="image/x-icon"/>

    <link href="./assets/css/style.css" rel="stylesheet" type="text/css" title="default"/>
</head>
<body>
<!-- --------------------------------------------------------- -->
<div class="wrapper">
    <%--  header --%>
    <jsp:include page="./header.jsp"/>
    <%--  /header --%>

    <div class="contentWrapper">
        <div class="content">
            <div class="windowStatusCont" style="display: none">
                <i class="success"></i>
                <p>Success!</p>
                <a class="close"></a>
            </div>
            <div class="breadcrumbs">
                <a>Prototype</a>
                <i></i>
                <a>Projects</a>
            </div>
            <div class="heading">
                <div class="pageTtl">
                    <i class="projects"></i>
                    <p>Projects(<%= request.getAttribute("filteredProjectsCount") %>)</p>
                </div>
                <div class="action">
                    <a class="greenBtn" href="new-project">Add project</a>
                    <div class="dropdown">
                        <form action="projects" method="post" name="results-per-page">
                            <select name="results">
                                <option value="5">Results per page:5</option>
                                <option value="10">Results per page:10</option>
                                <option value="20">Results per page:20</option>
                            </select>
                            <input type="hidden" value="<%= request.getParameter("results") %>" name="results-hidden"
                                   id="results-hidden">
                            <button type="submit">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="tableWrp">
                <div class="filters">
                    <%
                        String url = "projects?page=" +
                                request.getParameter("page") +
                                "&results=" +
                                request.getParameter("results");
                    %>
                    <form name="filters" method="POST" action="<%= url + "&" %>">
                        <p>Filters</p>
                        <div class="dropdown date">
                            <input
                                    type="date"
                                    name="date-from"
                                    value="<%= request.getParameter("date-from") %>"
                                    style="width: 100%;height: 100%;"
                            />
                        </div>
                        <div class="dropdown date">
                            <input
                                    type="date"
                                    name="date-to"
                                    value="<%= request.getParameter("date-to") %>"
                                    style="width: 100%;height: 100%;"
                            />
                        </div>
                        <div class="dropdown status">
                            <input type="hidden" id="status-value" value="<%= request.getParameter("status") %>">
                            <select name="status" title="statuses">
                                <option value="0">Status: All</option>
                                <option value="1">New</option>
                                <option value="2">In Progress</option>
                                <option value="3">Finished</option>
                            </select>
                        </div>
                        <%-- <button type="submit">Apply</button> --%>
                        <a class="blueDarkBtn" type="button">
                            <button type="submit" name="apply-filters-button">
                                Apply
                            </button>
                        </a>

                        <div class="right">
                            <a class="grayBtn" id="clear-filters"><span>Clear filters</span><i></i></a>
                            <div class="pagination">
                                <% int filteredProjectsCount = (int) request.getAttribute("filteredProjectsCount"); %>
                                <% int resultPerPage = Integer.parseInt(request.getParameter("results")); %>
                                <% int pages =  filteredProjectsCount % resultPerPage == 0 ? filteredProjectsCount / resultPerPage : filteredProjectsCount / resultPerPage + 1; %>
                                <% int currentPage = Integer.parseInt(request.getParameter("page")); %>
                                <a class="arrowLeft disabled"><i></i></a>
                                <form name="pagination" method="post" action="">
                                    <div class="center">
                                        <% if (currentPage != 1 && currentPage != pages) { %>
                                            <a><button id="pb1" value="<%= currentPage - 1 %>" type="submit" name="pageBtn" onclick="{document.pagination.page.value=this.value;document.pagination.submit();}"><%= currentPage - 1 %></button></a>
                                            <a><button id="pb2" value="<%= currentPage %>" type="submit" name="pageBtn" onclick="{document.pagination.page.value=this.value;document.pagination.submit();}"><%= currentPage %></button></a>
                                            <a><button id="pb3" value="<%= currentPage + 1 %>" type="submit" name="pageBtn" onclick="{document.pagination.page.value=this.value;document.pagination.submit();}"><%= currentPage + 1 %></button></a>
                                        <% } else if (currentPage == 1) { %>
                                            <a><button id="pb4" value="<%= currentPage %>"type="submit" name="pageBtn" onclick="{document.pagination.page.value=this.value;document.pagination.submit();}"><%= currentPage %></button></a>
                                            <% if (currentPage+1 <= pages) { %>
                                                <a><button id="pb5" value="<%= currentPage + 1 %>" type="submit" name="pageBtn" onclick="{document.pagination.page.value=this.value;document.pagination.submit();}"><%= currentPage + 1 %></button></a>
                                            <% } %>
                                            <% if (currentPage+2 <= pages) { %>
                                                <a><button id="pb6" value="<%= currentPage + 2 %>" type="submit" name="pageBtn" onclick="{document.pagination.page.value=this.value;document.pagination.submit();}"><%= currentPage + 2 %></button></a>
                                            <% } %>
                                        <% } else { %>
                                            <% if (currentPage-2 >= 1) { %>
                                                <a><button id="pb7" value="<%= currentPage - 2 %>" type="submit" name="pageBtn" onclick="{document.pagination.page.value=this.value;document.pagination.submit();}"><%= currentPage - 2 %></button></a>
                                            <% } %>
                                            <% if (currentPage-1 >= 1) { %>
                                                <a><button id="pb8" value="<%= currentPage - 1 %>" type="submit" name="pageBtn" onclick="{document.pagination.page.value=this.value;document.pagination.submit();}"><%= currentPage - 1 %></button></a>
                                            <% } %>
                                            <a><button id="pb9" value="<%= currentPage %>" type="submit" name="pageBtn" onclick="{document.pagination.page=this.value;document.pagination.submit();}"><%= currentPage %></button></a>
                                        <% } %>
                                        <input type="hidden" name="page">
                                    </div>
                                </form>
                                <a class="arrowRight disabled"><i></i></a>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="table">
                    <div class="header">
                        <div class="checkbox">
                            <a class="checkbox" name="checkbox-main">
                                <i></i>
                            </a>
                        </div>
                        <span class="number">#</span>
                        <a class="date"><i class="sort up"></i>Created</a>
                        <span class="projectName">Project name</span>
                        <span class="companyName">Company name</span>
                        <span class="projectManager">Project manager</span>
                        <a class="products"><i class="sort up"></i>Products</a>
                        <div class="status">Status</div>
                        <div class="settings"><i></i></div>
                    </div>
                    <div class="content">

                        <% List<ProjectDTO> projects = (List<ProjectDTO>) request.getAttribute("projects"); %>
                        <% if(projects != null) { %>
                            <% for (int i = 0; i < projects.size(); i++) { %>
                            <% ProjectDTO project = projects.get(i); %>
                            <div class="row">
                                <div class="checkbox">
                                    <a class="checkbox" name="checkbox-project" id="<%= project.getId() %>">
                                        <i></i>
                                    </a>
                                </div>
                                <span class="number"> <%= project.getId() %></span>
                                <span class="date"><%= project.getDateCreated().toLocalDate() %></span>
                                <span class="projectName"> <%= project.getProjectName() %> </span>
                                <span class="companyName"> <%= project.getCompanyName() %> </span>
                                <span class="projectManager"> <%= project.getProjectManager() %></span>
                                <div class="products">
                                    <span> <%= project.getProductsCount() %> </span>
                                    <a class="grayBtn"><i></i></a>
                                </div>
                                <div class="status"><span class="orange"> <%= project.getStatus() %> </span></div>
                                <div class="settings"><a class="grayBtn" href="edit-project"><i></i></a></div>
                            </div>
                            <% } %>
                        <% } else { %>
                            <div class="row">
                                <span class="projectName"> No projects were found. </span>
                            </div>
                        <% } %>
                    </div>
                </div>
                <div class="bottomActions">
                    <form name="actions" method="POST" action="<%= url %>">
                        <p>Actions</p>
                        <div class="dropdown">
                            <select name="action-index" title="actions">
                                <option value="0">Select</option>
                                <option value="1">Change status to New</option>
                                <option value="2">Change status to In progress</option>
                                <option value="3">Change status to Finished</option>
                                <option value="4">Delete</option>
                            </select>
                        </div>
                        <a class="blueDarkBtn" id="apply-actions-button">Apply</a>
                        <div id="projects-boxes"></div>
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>
<script>
    window.onload = () => {
        attachEvents();
        setStatus();
        setResultsPerPage();

        function attachEvents() {
            //clearFilters
            let clearFiltersButton = document.getElementById('clear-filters');
            clearFiltersButton.addEventListener('click', clearFilters);

            //selectProject
            let projectsCheckboxes = document.querySelectorAll('a[name="checkbox-project"]');
            projectsCheckboxes.forEach(value => value.addEventListener('click', selectProject));

            //selectAllProjects
            let mainCheckbox = document.querySelector('a[name="checkbox-main"]');
            mainCheckbox.addEventListener('click', () => selectAllProjects(projectsCheckboxes, mainCheckbox));

            //applyActionOnSelectedProjects
            let applyActionsButton = document.getElementById('apply-actions-button');
            applyActionsButton.addEventListener('click', applyActionOnSelectedProjects);

            //let pageButtons = document.getElementsByName('pageBtn');
            //for(let i = 0; i < pageButtons.length; i++) {
            //    pageButtons[i].addEventListener('click', () => setPage(this));
            //}
        }

        function clearFilters() {

            let dateFrom = document.querySelector('input[name="date-from"]');
            let dateTo = document.querySelector('input[name="date-to"]');
            let status = document.querySelector('select[name="status"]');
            let filtersForm = document.querySelector('form[name="filters"]');

            dateFrom.value = "";
            dateTo.value = "";
            status.value = "0";

            filtersForm.submit();
        }

        function selectProject() {
            let classList = this.classList;

            if (classList.contains("active")) {
                this.classList.remove("active");
            } else {
                this.classList.add("active");
            }
        }

        function selectAllProjects(allProjects, self) {
            let classes = self.classList;

            if (classes.contains("active")) {
                classes.remove("active");

                allProjects.forEach(checkbox => {
                    checkbox.classList.remove("active");
                });
            } else {
                classes.add("active");

                allProjects.forEach(checkbox => {
                    checkbox.classList.add("active");
                });
            }
        }

        function applyActionOnSelectedProjects() {
            let selectedProjects = document.querySelectorAll('a.checkbox.active');
            let projectBoxesList = document.getElementById('projects-boxes');

            let selectedProjectsIds =
                Array.from(selectedProjects)
                    .map(x => x.getAttribute('id'));

            selectedProjectsIds.forEach(projectId => {
                let node = document.createElement("input");

                node.setAttribute('name', 'project-id');
                node.setAttribute('type', 'text');
                node.setAttribute('value', projectId);

                projectBoxesList.appendChild(node);
            });

            let actionsForm = document.querySelector('form[name="actions"]');
            actionsForm.submit();
        }

        function setStatus() {
            let statusValue = document.getElementById("status-value").value;
            let statuses = document.querySelector('select[name="status"]');

            if (statusValue !== 'null') {
                statuses.value = statusValue;
            }
        }

        function setResultsPerPage() {
            let resultsValue = document.getElementById("results-hidden").value;
            let results = document.querySelector('select[name="results"]');

            if (resultsValue !== 'null') {
                results.value = resultsValue;
            }
        }

        function setPage(pageBtn) {
            //let pageNumber =  pageBtn.value;
            let pageNumber = document.getElementById(pageBtn.id).value;
            console.log(pageNumber);
            let page = document.getElementById("page");

            if (pageNumber !== ' null') {
                page.value = pageNumber;
            }
        }
    };
</script>
</body>
</html>