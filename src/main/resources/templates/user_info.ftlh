<!DOCTYPE html>
<html>
<head>
    <title>userinfo</title>
</head>
<body>
<h1>BuySellTrade</h1><hr>
<h3>Подробная информация о продавце</h3>
<b>Номер телефона :</b> ${user.phone}<br>
<b>Имя :</b> ${user.name}
<h4>Товары пользователя ${user.name}</h4>
<#list products as product>
    <div>
        <p>
        <hr>${product.title} ${product.city} ${product.price} руб.</p> | <a href="/product/${product.id}">Подробнее</a>
    </div>
    <#else>
        <h3>Товар нет</h3>
</#list>
<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>
</body>
</html>