<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Styled Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #fff;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }
        select, input[type="text"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 16px;
        }
        .input-group {
            display: flex;
            margin: 0 auto;
        }
        .input-group > * {
            flex: 1;
        }
        button{
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-left: 5px;
            margin-right: 5px;
        }
        button:hover{
            background-color: #9de3c9;
            transition: 0.7s ease;
        }

        .btn-position{
            margin-top: 5px;
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>System of academic mobility</h2>
    <form action="selectCountries">
        <label for="country1">Country 1</label>
        <select id="country1" name="country1">
            <option selected disabled>Chose your variant</option>
            <c:forEach var="ctr" items="${allCountries}">
                <option value="${ctr.id}">${ctr.name}</option>
            </c:forEach>
        </select>
        <label for="country2" style="margin-top: 10px;">Country 2</label>
        <select id="country2" name="country2">
            <option selected disabled>Chose your variant</option>
            <c:forEach var="ctr" items="${allCountries}">
                <option value="${ctr.id}">${ctr.name}</option>
            </c:forEach>
        </select>
        <div class="input-group btn-position">
            <button type="submit">Select counties</button>
        </div>
    </form>
</div>
</body>
</html>
