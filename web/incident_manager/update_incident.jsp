<%-- 
    Document   : update_incident
    Created on : 17-Aug-2013, 10:02:13 AM
    Author     : yoji_salut
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../includes/header.jsp"/>
    <h1>Update Incident</h1>
    
    <c:if test="${requestScope.pageType == 'updateIncident'}">
        
        <c:if test="${$requestScope.errorMessages.size() != 0}">
            <c:forEach var="eachMessage" items="${requestScope.errorMessages}">
                ${eachMessage}<br>
            </c:forEach>
        </c:if><br>
                
        <form action="ManageIncident" method="post">
            <input type="hidden" name="action" value="updateIncidentExe">
            <input type="hidden" name="incidentID" value="${requestScope.incidentView.incidentID}">
            <input type="hidden" name="productCode" value="${requestScope.incidentView.productCode}">
            <input type="hidden" name="dateOpened" value="${requestScope.incidentView.dateOpened}">
            <input type="hidden" name="title" value="${requestScope.incidentView.title}">

            <label>Incident ID:</label>${requestScope.incidentView.incidentID}<br>
            <label>Product Code:</label>${requestScope.incidentView.productCode}<br>
            <label>Date Opened:</label>${requestScope.incidentView.dateOpened}<br>
            <label>Date Closed:</label>
            <input type="text" name="dateClosed"> Use 'yyyy/mm/dd' format <br>
            <label>Title:</label>${requestScope.incidentView.title}<br>
            <label>Description:</label>
            <textarea rows="5" cols="32" name="description">${requestScope.incidentView.description}</textarea><br>

            <input type="submit" value="Update Incident"><br>
        </form>
        
    </c:if>
    
    <c:if test="${requestScope.pageType == 'updateIncidentExe'}">
        <p>This incident was updated</p>
        <a href="<c:url value="ManageIncident">
               <c:param name="action" value="selectAssignedIncident"/></c:url>">
        Select Another Incident</a> 
    </c:if>
    
    <p>You are logged in as  ${sessionScope.technicianEmail}</p>
    <form action="ManageIncident" method="get">
        <input type="hidden" name="action" value="technicianLogout">
        <input type="submit" value="Logout">
    </form>
        
<c:import url="../includes/footer.jsp"/>
