<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%--<!doctype html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <meta name="viewport"--%>
<%--          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">--%>
<%--    <meta http-equiv="X-UA-Compatible" content="ie=edge">--%>
<%--    <title>University</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--    <h1>All University</h1>--%>
<%--    <br>--%>
<%--    <form action="">--%>
<%--        <select name="uniName" id="uniName">--%>
<%--            <c:forEach var="uni" items="${allUniversities}">--%>
<%--                    <option>${uni.name}</option>--%>
<%--            </c:forEach>--%>
<%--        </select>--%>
<%--    </form>--%>
<%--</body>--%>
<%--</html>--%>

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
            /*padding-top: 8px;*/
            /*padding-bottom: 8px;*/
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

        .home-button {
            position: fixed;
            top: 20px;
            left: 20px;
            z-index: 9999;
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
</head>
<body>

<button class="home-button" onclick="window.location.href ='/Multi_agent_system_data_collection/'">Change countries</button>

<div class="container">
    <h2>System of academic mobility</h2>
    <form action="handleSyllabus">
        <div class="form-group">
            <label for="university1">University 1</label>
            <select id="university1" name="university1">
                <option selected disabled>Chose your variant</option>
                <c:forEach var="uni" items="${localUniversities1}">
                    <option>${uni.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="university2">University 2</label>
            <select id="university2" name="university2">
                <option selected disabled>Chose your variant</option>
                <c:forEach var="uni" items="${localUniversities2}">
                    <option>${uni.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="subjectField">Subject Field</label>
            <input type="text" id="subjectField" name="subjectField" placeholder="Enter subject">
        </div>
        <div class="input-group">
            <button type="submit">Submit</button>
            <button type="reset">Reset</button>
        </div>
    </form>
</div>
</body>
</html>
