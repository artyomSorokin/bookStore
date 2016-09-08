
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Book</title>
    <link rel="stylesheet" type="text/css" href="/css/bookList.css" />
</head>
<body>
<div id="top_bar_black"> <div id="logo_container"> <div id="logo_image"> </div>  <div id="nav_block"> <div class="nav_button"><a href="main.html">Home </a></div>
    <div class="nav_button"> <a href="contacts.html"> Contact</a></div>
    <div class="nav_button"><a href="/books"> Books</a></div>
    <div class="nav_button"> <a href="/login.jsp">Login</a></div>
    <div class="nav_button"><a href="../registration.jsp">Registration</a></div>
    <div class="nav_button"> Cart</div>
</div>
</div> </div>
<div id="content_container">
    <div id="header"> <div class="header_content_tagline">
        <h2>Book: ${book.name}</h2>
        <img src="/image?id=${book.id}">
        <ul>
            <li>Book id: ${book.id}</li>
            <li>Book name: ${book.name}</li>
            <li>Book author: ${book.author}</li>
            <li>Year of issue: ${book.year}</li>
            <li>Description: ${book.desc}</li>
        </ul>

        <br/>
        <a href="/books">All books </a>

    </div>
    </div>
</div>

<div id="bottom_bar_black"> <div id="main_container">
    <div id="header_lower">  <div id="header_content_lowerline">Contact
        <div id="header_content_lowerboxcontent">148 Blackways Street<br />
            Hargary<br />
            Lingvillage<br />
            HG43 9HA <BR />
            info@domainhappy.com<br />
            www.domainhappy.com<br />
            01982 698 621<BR />
        </div>
    </div>
    </div>

    <div id="header_lower">  <div id="header_content_lowerline">Clients
        <div id="header_content_lowerboxcontent">Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est </div>
    </div>
    </div>


</div>
</div>
<div id="copywriteblock"> Designed by <a href="http://www.pixelateddesign.co.uk/">www.pixelateddesign.co.uk </a></div>

</body>
</html>
