<%-- 
    Document   : select_technician
    Created on : 15-Aug-2013, 9:23:02 PM
    Author     : yoji_salut
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../includes/header.jsp"/>
    <h1>Select Technician</h1>
    
    <table>
        <thead>
            <tr>
                <td>Name</td>
                <td>Open Incidents</td>
                <td></td>
            </tr>
            
        </thead>
        <tbody>
            <c:forEach var="eachTechAvailability" items="${requestScope.techAvailabilities}">
                <tr>
                    <td>${eachTechAvailability.techFirstName} ${eachTechAvailability.techLastName}</td>
                    <td>${eachTechAvailability.numOpenIncidents}</td>
                    <td><form action="ManageIncident" method="get">
                            <input type="hidden" name="action" value="assignConf">
                            <input type="hidden" name="incidentID" value="${requestScope.incidentID}">
                            <input type="hidden" name="techID" value="${eachTechAvailability.techID}">
                            <input type="submit" value="Select">
                        </form></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
<c:import url="../includes/footer.jsp"/>
