<%-- 
    Document   : register_product
    Created on : Aug 12, 2013, 8:40:23 AM
    Author     : yuno
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../includes/header.jsp"/>

    <h1>Customer Login</h1>
    
    <c:if test="${requestScope.message != null}">
        <p>${requestScope.message}</p>
    </c:if>
    
    <p>You must login before you can register a product.</p>

    <form action="<c:url value="RegisterProduct"/>" method="post">
        <input type="hidden" name="action" value="registerProduct">

        <label>Email:</label>
        <input type="text" name="email"/><br>

        <input type="submit" value="Login">
    </form>

<c:import url="../includes/footer.jsp"/>
