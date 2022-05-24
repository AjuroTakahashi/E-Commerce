<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ include file="header.jsp"%>

<main role="main">
    <form:form action="/client/add" modelAttribute="client" method='POST'>
        <table>
            <tr>
                <td>User:</td><td><input type='text' name='username' value=''></td>
            </tr>
            <tr>
                <td>Password:</td><td><input type='password' name='password' /></td>
            </tr>
            <tr>
                <td>Repeat password:</td><td><input type='password' name='matchingPassword'/></td>
            </tr>
            <tr>
                <td><input name="submit" type="submit" value="submit" /></td>
            </tr>
        </table>
    </form:form>
</main>
<%@ include file="footer.jsp"%>