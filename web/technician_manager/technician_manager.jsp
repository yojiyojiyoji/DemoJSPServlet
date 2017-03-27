<%-- 
    Document   : technician_manager
    Created on : 11-Aug-2013, 8:17:43 PM
    Author     : yoji_salut
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../includes/header.jsp"/>

    <h1>Technician List</h1>

    <table>
        <thead>
            <tr>
                <td>First Name</td>
                <td>Last Name</td>
                <td>Email</td>
                <td>Phone</td>
                <td>Password</td>
                <td></td>
            </tr>
        </thead>
        <tbody>
            
            <c:forEach var="eachTechnician" items="${requestScope.technicians}">
            <tr>
                <td>${eachTechnician.firstName}</td>
                <td>${eachTechnician.lastName}</td>
                <td>${eachTechnician.email}</td>
                <td>${eachTechnician.phone}</td>
                <td>${eachTechnician.password}</td>
                <td><form action="<c:url value="/ManageTechnician"/>" method="get">
                        <input type="hidden" name="action" value="deleteTechnician">
                        <input type="hidden" name="techID" value="${eachTechnician.techID}">
                        <input type="submit" value="Delete">
                    </form></td>
            </tr>
            </c:forEach>
        </tbody>
    </table><br>
    
    <a href="<c:url value="/ManageTechnician">
           <c:param name="action" value="addTechnician"/></c:url>">
        Add Technician</a><br> 
        
<c:import url="../includes/footer.jsp"/>
