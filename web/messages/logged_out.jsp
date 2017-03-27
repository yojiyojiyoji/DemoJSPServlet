<%-- 
    Document   : logged_out
    Created on : 17-Aug-2013, 11:35:08 AM
    Author     : yoji_salut
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../includes/header.jsp"/>
        <h1>Logged out</h1>
        <a href="<c:url value="ManageIncident">
               <c:param name="action" value="technicianLogin"/></c:url>">
        Go back to login page</a>
<c:import url="../includes/footer.jsp"/>
