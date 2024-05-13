<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        .universities{
            display: flex;
            justify-content: space-between;
        }
        .uni-info{
            width: 50%;
            border-radius: 15px;
            border: 3px solid black;
            font-family: Arial, sans-serif;
        }
    </style>
</head>
<body>
    <section>
        <div class="universities">
            <div class="uni-info">
                <h1>First Uni: ${firstUniName}</h1>
                <div>
                    <p>
                        ${firstUni}
                    </p>
                </div>
            </div>
            <div class="uni-info">
                <h1>Second Uni: ${secondUniName}</h1>
                <div>
                    <p>
                        ${secondUni}
                    </p>
                </div>
            </div>
        </div>
    </section>
</body>
</html>