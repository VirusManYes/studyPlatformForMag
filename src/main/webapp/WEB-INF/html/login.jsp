

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../static/css/style.css">
    <link rel="stylesheet" href="/resources/css/constantsStyle.css">
    <script src="../../static/js/script.js"></script>

</head>
<body style="margin: 0%;">

    <form method="post" action="/login">
        <div id="enterBar">
            <p>
                <input id="login" name="login" title="login" placeholder="login">
            </p>
            <p>
                <input id="password" name="password" type="password" title="password" placeholder="password">
            </p>
            <p>
                <input type="submit" value="Enter" >
                <input  class="button"  type="button" value="Cancel" onclick="backToMainPage()">
            </p>
        </div>
    </form>

    
</body>
</html>