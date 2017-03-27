<%-- 
    Document   : customer_manager
    Created on : 11-Aug-2013, 8:27:38 PM
    Author     : yoji_salut
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../includes/header.jsp"/>

    <h1>Customer Search</h1>
    
    <form action="ManageCustomer" method="get">
        <input type="hidden" name="action" value="search"/>
        <label>Last Name: </label>
        <input type="text" name="lastName"/>
        <input type="submit" value="Search"/>
    </form>
    <br>
    
    <c:if test="${requestScope.customers != null}">
        <c:if test="${requestScope.customers.size() != 0}">
        
        <h1>Results</h1>
        <table>
            <thead>
                <tr>
                    <td>Name</td>
                    <td>Email Address</td>
                    <td>City</td>
                    <td></td>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="eachCustomer" items="${requestScope.customers}">
            <tr>
                <td>${eachCustomer.firstName} ${eachCustomer.lastName}</td>
                <td>${eachCustomer.email}</td>
                <td>${eachCustomer.city}</td>
                <td>
                    <form action="ManageCustomer" method="get">
                    <input type="hidden" name="action" value="updateCustomer">
                    <input type="hidden" name="customerID" value="${eachCustomer.customerID}">
                        <input type="submit" value="Select">
                    </form>
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
        </c:if>
    </c:if>

<c:import url="../includes/footer.jsp"/>
