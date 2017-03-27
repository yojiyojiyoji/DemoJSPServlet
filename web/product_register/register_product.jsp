<%-- 
    Document   : display_products
    Created on : Aug 13, 2013, 2:23:54 PM
    Author     : yuno
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../includes/header.jsp"/>
        
<h1>Register Product</h1>

<c:if test="${requestScope.pageType=='registerProduct'}">
    
    <form action="<c:url value="RegisterProduct"/>" method="get">
        <input type="hidden" name="action" value="registerProductExe">
        <input type="hidden" name="customerID" value="${requestScope.customer.customerID}">

        <label>Customer: </label>
        ${requestScope.customer.firstName} ${requestScope.customer.lastName}<br>
        <label>Product: </label>
        <select name="productCode">
            <c:forEach var="eachProduct" items="${requestScope.products}">
                <option value="${eachProduct.productCode}">${eachProduct.name}</option>            
            </c:forEach>
        </select><br>
        <label>&nbsp;</label>
        <input type="submit" value="Register Product">
    </form>

</c:if>

<c:if test="${requestScope.pageType=='registerProductExe'}">
    <p>Product (${requestScope.productCode}) was successfully registered.</p>
</c:if>
        
<c:import url="../includes/footer.jsp"/>
