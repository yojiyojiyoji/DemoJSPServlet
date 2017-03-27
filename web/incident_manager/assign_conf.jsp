<%-- 
    Document   : assign_incident
    Created on : 15-Aug-2013, 10:29:31 PM
    Author     : yoji_salut
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../includes/header.jsp"/>
    
    <h1>Assign Incident</h1>
    
    <c:if test="${requestScope.pageType == 'assignConf'}">
    
        <label>Customer:</label> ${incidentView.customerFirstName} ${incidentView.customerLastName}<br>
        <label>Product:</label> ${incidentView.productCode}<br>
        <label>Technician:</label> ${technician.firstName} ${technician.lastName}<br>
        
        <form action ="ManageIncident" method="post">
            <input type="hidden" name="action" value="assignExe">
            <input type="hidden" name="incidentID" value="${incidentView.incidentID}">
            <input type="hidden" name="techID" value="${technician.techID}">
            <input type="submit" value="Assign Incident">
        </form>
    </c:if>
    
    <c:if test="${requestScope.pageType == 'assignExe'}">
        
        <p>This incident was assigned to a technician.</p>
        <a href="<c:url value="ManageIncident">
               <c:param name="action" value="selectUnassignedIncident"/></c:url>">
            Select Another Incident</a>
        
    </c:if>
    
<c:import url="../includes/footer.jsp"/>
