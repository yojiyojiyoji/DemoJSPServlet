<%-- 
    Document   : get_customer
    Created on : 18-Aug-2013, 8:13:06 PM
    Author     : yoji_salut
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../includes/header.jsp"/>
    <h1>Get Customer</h1>
    
    <c:if test="${requestScope.message != null}">
        <p>${requestScope.message}</p>
    </c:if>
    
    <p>You must enter the customer's email address to select the customer.</p>
    
    <form action="ManageIncident" method="get">
        <input type="hidden" name="action" value="createIncident">
        <label>Email:</label>
        <input type="text" name="customerEmail">
        <input type="submit" value="Get Customer">
    </form>
    
<c:import url="../includes/footer.jsp"/>
