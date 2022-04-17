<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form:form>
    <table>
    <thead>
    <tr>
        <th> Employee ID #1 </th>
        <th> Employee ID #2 </th>
        <th> Project ID </th>
        <th> Days worked</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${corowkers}" var="coworker" >
        <tr>
            <td >${coworker.empl1Id}</td>
            <td >${coworker.empl2Id}</td>
            <td >${coworker.projectId}</td>
            <td >${coworker.duration}</td>
        </tr>
    </c:forEach>


</tbody>
</table>
</form:form>
    <div>
    <c:set var="first" value="${corowkers[0]}"/>
    <div> Employee ${first.empl1Id} worked with employee ${first.empl2Id}  this much days:  ${first.duration} on project :  ${first.projectId}
    </div>