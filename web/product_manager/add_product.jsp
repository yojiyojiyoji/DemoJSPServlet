<%-- 
    Document   : product
    Created on : Aug 9, 2013, 12:54:15 PM
    Author     : yuno
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../includes/header.jsp"/>

    <h1>Add Product</h1>
    
    <c:if test="${requestScope.messages != null}">
        <c:forEach var="eachMessage" items="${requestScope.messages}">
            <i>${eachMessage}</i><br>
        </c:forEach>
    </c:if>

    <form action="<c:url value="ManageProduct">
                <c:param name="action" value="addProductExe"/></c:url>" 
                method="post">

        <label>Code:</label>
        <input type="text" name="productCode" value="${requestScope.product.productCode}"><br>
        <label>Name:</label>
        <input type="text" name="name" value="${requestScope.product.name}"><br>
        <label>Version:</label>
        <input type="text" name="version" value="${requestScope.product.version}" ><br>
        <label>Release Date:</label>
        <input type="text" name="releaseDate" value="${requestScope.dateStr}"> Use 'yyyy-mm-dd' format<br>

        <label>&nbsp;</label>
        <input type="submit" value="Add Product"><br><br>

    </form>

<a href="<c:url value="ManageProduct">
            <c:param name="action" value=""/></c:url>">
    View Product List</a><br>

<c:import url="../includes/footer.jsp" />