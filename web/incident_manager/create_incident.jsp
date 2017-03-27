<%-- 
    Document   : create_incident
    Created on : 18-Aug-2013, 8:30:53 PM
    Author     : yoji_salut
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../includes/header.jsp"/>

    <h1>Create Incident</h1>
    
    <c:if test="${requestScope.errorMessages.size() != 0}">
        <c:forEach var="eachErrorMessage" items="${requestScope.errorMessages}">
            ${eachErrorMessage}<br>
        </c:forEach>
    </c:if>
    
    <c:if test="${requestScope.pageType=='createIncident'}">
        
            <form action="ManageIncident" method="post">
                <input type="hidden" name="action" value="createIncidentExe">
                <input type="hidden" name="customerID" value="${requestScope.registrationViews[0].customerID}">
                <label>Customer:</label>
                ${requestScope.registrationViews[0].customerFirstName} 
                ${requestScope.registrationViews[0].customerLastName}<br>
                <label>Product:</label>
                <select name="productCode">
                    <c:forEach var="eachRegistrationView" items="${requestScope.registrationViews}">
                        <option value="${eachRegistrationView.productCode}">${eachRegistrationView.productName}</option>
                    </c:forEach>
                </select><br>
                <label>Title:</label>
                <input type="text" name="title" value="${requestScope.title}"><br>
                <label>Description:</label>
                <textarea rows="5" cols="32" name="description">${requestScope.description}</textarea><br><br>
                <input type="submit" value="Create Incident">
            </form><br>
            
    </c:if>
    
    <c:if test="${requestScope.pageType=='createIncidentExe'}">
        <p>This incident was added to our database.</p>
    </c:if>
    
<c:import url="../includes/footer.jsp"/>
