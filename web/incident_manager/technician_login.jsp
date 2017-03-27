<%-- 
    Document   : technician_login
    Created on : 16-Aug-2013, 6:54:42 PM
    Author     : yoji_salut
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../includes/header.jsp"/>
    <h1>Technician Login</h1>
    
    <c:if test="${requestScope.message != null}">
        <p>${requestScope.message}</p>
    </c:if>
    
    <form action="<c:url value="ManageIncident"/>" method="get">
        <input type="hidden" name="action" value="selectAssignedIncident">
        <label>Email: </label>
        <input type="text" name="technicianEmail" value="${cookie.techEmailCookie.value}">
        <input type="submit" value="Login">
    </form>
    
<c:import url="../includes/footer.jsp"/>
