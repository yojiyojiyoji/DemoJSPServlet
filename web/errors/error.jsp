<%-- 
    Document   : error
    Created on : 13-Aug-2013, 8:27:19 PM
    Author     : yoji_salut
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../includes/header.jsp"/>
        <h1>Error!</h1>
        <p>${requestScope.errorMessage}</p>
<c:import url="../includes/footer.jsp"/>
