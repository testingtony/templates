<?php
   include('session.php');
?>
<!DOCTYPE html>
<html>
  <head>
    <title>Your Profile</title>
    <link href="style.css" rel="stylesheet" type="text/css">
  </head>	
  <body>
    <h1>Welcome <?= $_SESSION['login_user'] ?></h1>
    <p>We love you</p>
    <p><a class="logout" href="logout.php">logout</a></p>
  </body>
</html>

