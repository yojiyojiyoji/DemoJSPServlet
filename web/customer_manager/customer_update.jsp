<%-- 
    Document   : customer_update
    Created on : 11-Aug-2013, 11:13:04 PM
    Author     : yoji_salut
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../includes/header.jsp"/>

    <h1>View/Update Customer</h1>
    
   <c:if test="${requestScope.pageType == 'updateCustomer'}">

    <form action="ManageCustomer" method="post">
        <input type="hidden" name="action" value="updateCustomerExe">
        <input type="hidden" name="customerID" 
               value="${requestScope.customer.customerID}"/>

        <label>First Name:</label>
        <input type="text" name="firstName" 
               value="${requestScope.customer.firstName}"/><br>
        
        <label>Last Name:</label>
        <input type="text" name="lastName" 
               value="${requestScope.customer.lastName}"/><br>
        
        <label>Address:</label>
        <input type="text" name="address" 
               value="${requestScope.customer.address}"><br>
        
        <label>City:</label>
        <input type="text" name="city" 
               value="${requestScope.customer.city}"><br>
        
        <label>State:</label>
        <input type="text" name="state" 
               value="${requestScope.customer.state}"><br>
        
        <label>Postal Code:</label>
        <input type="text" name="postalCode" 
               value="${requestScope.customer.postalCode}"><br>
        
        <label>Country Code:</label>
        <input type="text" name="countryCode" 
               value="${requestScope.customer.countryCode}"><br>
        
        <label>Phone:</label>
        <input type="text" name="phone" 
               value="${requestScope.customer.phone}"><br>
        
        <label>Password:</label>
        <input type="text" name="email" 
               value="${requestScope.customer.email}"><br>
        
        <label>First Name:</label>
        <input type="password" name="password" 
               value="${requestScope.customer.password}"><br>

        <label>&nbsp;</label>
        <input type="submit" value="Update Customer">
    </form><br><br>
            
    </c:if>
    
    <c:if test="${requestScope.pageType == 'updateCustomerExe'}">
        <p>Customer information was successfully updated.</p>
    </c:if>

    
    <a href="<c:url value="ManageCustomer">
                <c:param name="action" value=""/></c:url>">
        Search Customer</a><br>
        
<c:import url="../includes/footer.jsp"/>
