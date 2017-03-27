<%-- 
    Document   : add_technician
    Created on : 12-Aug-2013, 9:24:59 PM
    Author     : yoji_salut
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="mma" uri="/WEB-INF/tlds/SportsPro.tld" %>
<c:import url="../includes/header.jsp"/>

    <h1>Add Technician</h1>
    
    <p> Please fill out all the fields with <mma:ifEmptyMark color="red" field=""/> marks.</p>
    
    <form action="<c:url value="ManageTechnician"/>" method="post">
        <input type="hidden" name="action" value="addTechnicianExe">
        
        <label>First Name</label>
        <input type="text" name="firstName" value="${requestScope.technician.firstName}">
        <mma:ifEmptyMark color="red" field="${requestScope.technician.firstName}"/><br>
        <label>Last Name</label>
        <input type="text" name="lastName" value="${requestScope.technician.lastName}">
        <mma:ifEmptyMark color="red" field="${requestScope.technician.lastName}"/><br>
        <label>Email</label>
        <input type="text" name="email" value="${requestScope.technician.email}">
        <mma:ifEmptyMark color="red" field="${requestScope.technician.email}"/><br>
        <label>Phone</label>
        <input type="text" name="phone" value="${requestScope.technician.phone}">
        <mma:ifEmptyMark color="red" field="${requestScope.technician.phone}"/><br>
        <label>Password</label>
        <input type="password" name="password" value="${requestScope.technician.password}">
        <mma:ifEmptyMark color="red" field="${requestScope.technician.password}"/><br>
        
        <label>&nbsp;</label>
        <input type="submit" value="Add Technician"><br>
        
    </form><br>
    
    <a href="<c:url value="ManageTechnician">
           <c:param name="action" value=""/></c:url>">
    View Technician List</a><br>
        
<c:import url="../includes/footer.jsp"/>
