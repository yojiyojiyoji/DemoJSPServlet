<%-- 
    Document   : incident_manager
    Created on : 15-Aug-2013, 8:15:18 PM
    Author     : yoji_salut
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../includes/header.jsp"/>

    <h1>Select Incident</h1>
       
    <c:if test="${requestScope.incidentViewsForSelection.size() != 0}">
        
    <table>
        <thead>
            <tr>
                <td>Customer</td>
                <td>Product</td>
                <td>Date Opened</td>
                <td>Title</td>
                <td>Description</td>
                <td></td>
            </tr>

        </thead>
        <tbody>
            
            <c:forEach var="eachIncident" items="${requestScope.incidentViewsForSelection}">
                <tr>
                    <td>${eachIncident.customerFirstName} ${eachIncident.customerLastName}</td>
                    <td>${eachIncident.productCode}</td>
                    <td>${eachIncident.dateOpened}</td>
                    <td>${eachIncident.title}</td>
                    <td>${eachIncident.description}</td>

                    <td><form action="ManageIncident" method="get">
                            <c:choose>
                                <c:when test="${requestScope.pageType == 'selectAssignedIncident'}">
                                    <input type="hidden" name="action" value="updateIncident">
                                </c:when>
                                <c:when test="${requestScope.pageType == 'selectUnassignedIncident'}">
                                    <input type="hidden" name="action" value="selectTechnician">
                                </c:when>
                            </c:choose>
                            <input type="hidden" name="incidentID" value="${eachIncident.incidentID}">
                            <input type="submit" value="Select">
                        </form> 
                        </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
        
    </c:if> 

    <c:if test="${requestScope.pageType == 'selectAssignedIncident'}">
        
        <c:if test="${requestScope.incidentViewsForSelection.size() == 0}">
            <p>There is no open incident for logged in email address.</p>
            <a href="<c:url value="ManageIncident">
                   <c:param name="action" value="selectAssignedIncident"/></c:url>">
                Refresh List of Incidents</a> 
        </c:if>
        
        <p>You are logged in as  ${sessionScope.technicianEmail}</p>
        <form action="ManageIncident" method="get">
            <input type="hidden" name="action" value="technicianLogout">
            <input type="submit" value="Logout">
        </form>
        
    </c:if> 
    
<c:import url="../includes/footer.jsp"/>
