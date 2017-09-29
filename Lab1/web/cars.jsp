<%@ page import="Models.Car" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <title>Список</title>
</head>
<body>
<div class="container col-sm-3 pt-5">

    <table class="table">
        <thead>
            <tr>
                <th>Марка</th>
                <th>Модель</th>
                <th>Год</th>
            </tr>
        </thead>
        <tbody>
        <%
            ArrayList<Car> cars = (ArrayList<Car>) request.getAttribute("cars");

            for(Car car : cars) {
                out.println("<tr>");
                out.println("<td>" + car.Model + "</td>");
                out.println("<td>" + car.SubModel + "</td>");
                out.println("<td>" + car.Year + "</td>");
                out.println("</tr>");
            }
        %>
        </tbody>
    </table>

    <form action="index" method="get">
        <button type="submit" class="btn btn-primary float-left mb-5">Назад</button>
    </form>

</div>

</body>
</html>
