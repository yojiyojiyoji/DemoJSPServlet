<%-- 
    Document   : display_incidents
    Created on : 17-Aug-2013, 11:52:57 PM
    Author     : yoji_salut
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../includes/header.jsp"/>

    <c:if test="${requestScope.pageType == 'unassigned'}">
        <h1>Unassigned Incidents</h1>
        <a href="<c:url value="ManageIncident">
               <c:param name="action" value="displayAssignedIncidents"/></c:url>">
        View Assigned Incidents</a>
    </c:if>    

    <c:if test="${requestScope.pageType == 'assigned'}">
        <h1>Assigned Incidents</h1>
        <a href="<c:url value="ManageIncident">
               <c:param name="action" value="displayUnassignedIncidents"/></c:url>">
        View Unassigned Incidents</a>
    </c:if>
        
        <table>
            <thead>
                <tr>
                    <td>Customer</td>
                    <td>Product</td>
                    <c:if test="${requestScope.pageType == 'assigned'}">
                        <td>Technician</td>
                    </c:if>
                    <td>Incident</td>
            </thead>
            <tbody>
            <c:forEach var="eachIncident" items="${requestScope.incidentsForDisplay}">
                <tr>
                    <td>${eachIncident.customerFirstName} ${eachIncident.customerLastName}</td>
                    <td>${eachIncident.productName}</td>
                    <c:if test="${requestScope.pageType == 'assigned'}">
                        <td>${eachIncident.technicianFirstName} ${eachIncident.technicianLastName}</td>
                    </c:if>
                    <td>
                        <table id='incident_table'>
                        <tr>
                            <td><label>ID:</label></td>
                            <td>${eachIncident.incidentID}<br></td>
                        </tr>
                        <tr>
                            <td><label>Opened:</label></td>
                            <td>${eachIncident.dateOpened}</td>
                        </tr>
                        <c:if test="${requestScope.pageType == 'assigned'}">
                            <tr>
                                <td><label>Closed:</label></td>
                                <td>
                                    <c:if test="${eachIncident.dateClosed.getTime() == 0}">OPEN</c:if>
                                    <c:if test="${eachIncident.dateClosed.getTime() != 0}">${eachIncident.dateClosed}</c:if>
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <td><label>Title:</label></td>
                            <td>${eachIncident.title}</td>
                        </tr>
                        <tr>
                            <td><label>Description:</label></td>
                            <td>${eachIncident.description}</td>
                        </tr>
                        </table>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    
<c:import url="../includes/footer.jsp"/>
