<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>BuySellTrade</title>
</head>
<body>
<h1>BuySellTrade </h1><hr>
<#if user.email??>
    <h3>Имя пользователя : <i>${user.name}</i></h3>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Выйти"/>
    </form>
<#else>
    <a href="/login">Войти</a>
</#if>
<hr>
<form action="/" method="get">
    Поиск : <input type="text" name="title" placeholder="Поиск по объявлениям"><br>
    <input type="submit" value="Поиск"/>
</form>
<#list products as product>
    <div>
        <p>
        <hr>${product.title} ${product.city} ${product.price} руб.</p> | <a href="/product/${product.id}">Подробнее</a>
    </div>
<#else>
    <h3>Товаров нет</h3>
</#list>
<#if user.email??>
    <hr>
    <h3>Создать новый товар</h3>
    <form action="/product/create" method="post" enctype="multipart/form-data">
        Название объявления: <input type="text" name="title"><br><br>
        Описание : <input type="text" name="description"><br><br>
        Цена : <input type="text" name="price"><br><br>
        Город : <input type="text" name="city"><br><br>
        Автор : <input type="text" name="author"><br><br>
        Фото 1 : <input type="file" name="file1"><br><br>
        Фото 2 : <input type="file" name="file2"><br><br>
        Фото 3 : <input type="file" name="file3"><br><br>

        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Добавить  товар">
    </form>
</#if>
</body>
</html>