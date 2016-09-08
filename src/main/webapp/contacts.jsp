<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Contacts</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="top_bar_black"> <div id="logo_container"> <div id="logo_image"> </div>  <div id="nav_block"> <div class="nav_button"><a href="main.jsp">Home </a></div>
    <div class="nav_button"> Contact</div>
    <div class="nav_button"><a href="/books">Books</a></div>
    <c:choose>
        <c:when test="${user.name==null}">
            <div class="nav_button"> <a href="/login.jsp">Login</a></div>
            <div class="nav_button"><a href="registration.jsp">Registration</a></div>
            <div class="nav_button"> <a href="/bucket">Cart</a></div>
        </c:when>
        <c:otherwise>
            <div class="nav_button"><a href="/logout"> Logout</a></div>
            <div class="nav_button"> <a href="/bucket">Cart</a></div>
            <div class="nav_button"> ${user.name}</div>
        </c:otherwise>
    </c:choose>
</div>
</div> </div>
<div id="content_container">
    <div id="header"> <div class="header_content_tagline">
        Hargary<br />
        Lingvillage<br />
        HG43 9HA <BR />
        info@domainhappy.com<br />
        www.domainhappy.com<br />
        01982 698 621<BR />
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
