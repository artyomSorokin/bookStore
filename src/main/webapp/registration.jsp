<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" href="/css/login.css" />
</head>
<body>
<div id="top_bar_black"> <div id="logo_container"> <div id="logo_image"> </div>  <div id="nav_block"> <div class="nav_button"><a href="main.jsp">Home </a></div>
    <div class="nav_button"> <a href="contacts.jsp"> Contact</a></div>
    <div class="nav_button"><a href="/books"> Books</a></div>
    <div class="nav_button"><a href="/login.jsp">Login</a></div>
    <div class="nav_button"> Registration </div>
    <div class="nav_button"> <a href="/bucket">Cart</a></div>
</div>
</div> </div>
<div id="content_container">
    <div id="header"> <div class="header_content_tagline">
        <h2><font color="red">${errorRegistered}</font></h2>
        <form method="post" action="RegistrationController">
            <center>
                <table border="1" width="100%" cellpadding="5">
                    <thead>
                    <tr>
                        <th colspan="2">Enter Information Here</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Email</td>
                        <td><input type="text" name="email" /></td>
                    </tr>
                    <tr>
                        <td>User Name</td>
                        <td><input type="text" name="name" /></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="password" /></td>
                    </tr>
                    <tr>
                        <td>Phone Number</td>
                        <td><input type="text" name="phoneNumber" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Submit" /></td>
                        <td><input type="reset" value="Reset" /></td>
                    </tr>
                    <tr>
                        <td colspan="2">Already registered!! <a href="login.jsp">Login Here</a></td>
                    </tr>
                    </tbody>
                </table>
            </center>
        </form>
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

