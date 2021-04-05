<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Events</title>
</head>
<body>
<h3>Events</h3>

${message}<br>
${events}<br>

<tbody>
    <tr><td>Events found by given tile</td></tr>
    <tr th:each="event: ${events}">
        <td th:field="${event}"> BB </td>
    </tr>
</tbody>

</body>
</html>
