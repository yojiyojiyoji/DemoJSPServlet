<%@include file="includes/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h3>Administrators</h3>
<a href="<c:url value="ManageProduct"/>">Manage Products</a><br>
<a href="<c:url value="ManageTechnician"/>">Manage Technicians</a><br>
<a href="<c:url value="ManageCustomer"/>">Mange Customers</a><br>
<a href="<c:url value="ManageIncident">
           <c:param name="action" value="getCustomer"/></c:url>">
    Create Incidents</a><br>
<a href="<c:url value="ManageIncident">
           <c:param name="action" value="selectUnassignedIncident"/></c:url>">
    Assign Incidents</a><br>
    <a href="<c:url value="ManageIncident">
           <c:param name="action" value="displayUnassignedIncidents"/></c:url>">
    Display Incidents</a><br>

<h3>Technicians</h3>
<a href="<c:url value="ManageIncident">
            <c:choose>
                <c:when test="${sessionScope.technicianEmail == null}">
                      <c:param name="action" value="technicianLogin"/>     
                </c:when>
                <c:when test="${sessionScope.technicianEmail != null}">
                      <c:param name="action" value="selectAssignedIncident"/>     
                </c:when>
            </c:choose>
         </c:url>">

    Update Incident</a> 
                <c:if test="${sessionScope.technicianEmail != null}">
                      (You are logged in at ${sessionScope.technicianEmail})
                </c:if><br>

<h3>Customers</h3>
<a href="<c:url value="RegisterProduct"/>">Register Product</a><br>

<jsp:include page="./includes/footer.jsp" />

