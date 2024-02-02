<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>BuySellTrade</title>
</head>
<body>
<h1>BuySellTrade</h1>
<hr>
<h4>Панель администратора</h4>
<table border="3">
    <thead>
    <tr>
        <th>Email</th>
        <th>Номер телефона</th>
        <th>Активность</th>
        <th>Роли</th>
        <th>Бан</th>
        <th>Редактирование</th>
        <th>Подробная информация</th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
        <tr>
            <td>${user.email}</td>
            <td>${user.phone}</td>
            <td><#if user.active>true<#else>false</#if></td>
            <td><#list user.roles as role>${role}</#list></td>
            <td>
                <form action="/admin/user/ban/${user.id}" method="post">
                    <input type="hidden" name="_crsf" value="_crsf.token">
                    <input type="submit" value="Бан">
                </form>
            </td>
            <td><a href="/admin/user/edit/${user.id}">Редактировать</a></td>
            <td><a href="/user/${user.id}">Подробная информация</a></td>
        </tr>
    </#list>
    <#if users?size == 0>
        <tr>
            <td><h3>Пользователей нет</h3></td>
        </tr>
    </#if>
    </tbody>
</table>
</body>
</html>
