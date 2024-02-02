<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>BuySellTrade</title>
</head>
<body>
Подробная информация о товаре
<hr>
<#list images as img>
    <img src="/images/${img.id}" href height="60px"><br><br>
</#list>
<b>Название товара:</b>${product.title}<br><br>
<b>Описание:</b>${product.description}<br><br>
<b> Местоположение:</b>${product.city}<br><br>
<b>Цена товара:</b>${product.price}<br><br>
<b>Продавец:</b><a href="/user/${product.user.id}"> ${product.user.name}</a><br><br>
<hr>

</body>
</html>