<!DOCTYPE html>
<html>
<head>
    <title>BuySellTrade</title>
</head>
<body>
<h1>BuySellTrade</h1><hr>
<h4>Авторизация</h4>
<form action="/login" method="post">
    Эл. почта: <input type="text" name="email"><br><br>
    Пароль: <input type="password" name="pass"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Вход"/>
</form>
<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>
<a href="/registration">Зарегистрироваться</a>
</body>
</html>