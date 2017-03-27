<%-- 
    Document   : product_manager
    Created on : 7-Aug-2013, 10:19:37 PM
    Author     : yoji_salut
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../includes/header.jsp"/>
        
<h1>Product List</h1>

<table>
    <thead>
        <tr>
            <td>Code</td>
            <td>Name</td>
            <td>Version</td>
            <td>Release Date</td>
            <td></td>
        </tr>
    </thead>
    <c:forEach var="eachProduct" items="${requestScope.products}">
    <tr>
        <td>${eachProduct.productCode}</td>
        <td>${eachProduct.name}</td>
        <td>${eachProduct.version}</td>
        <td>${eachProduct.releaseDate}</td>
        <td>
            <form action="<c:url value="ManageProduct"/>" method="get">
                <input type="hidden" name="action" value="deleteProduct">
                <input type="hidden" name="productCode" value="${eachProduct.productCode}">
                <input type="submit" value="Delete">
            </form>
        </td>
    </tr>
    </c:forEach>
</table><br>

<a href="<c:url value="ManageProduct">
           <c:param name="action" value="addProduct"/></c:url>">
    Add Product</a><br>
        
<c:import url="../includes/footer.jsp"/>