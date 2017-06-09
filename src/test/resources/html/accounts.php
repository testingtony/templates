<?php
   include('session.php');
?>
<!DOCTYPE html>
<html>
  <head>
    <title>Your Accounts</title>
    <link href="style.css" rel="stylesheet" type="text/css">
  </head>	
  <body>
    <h1>Your Accounts <?= $_SESSION['login_user'] ?></h1>
    <table>
      <tr>
	<th>Id</th><th>Name</th>
      </tr>
      <tr>
	<th>1</th><th>Account 1</th>
      </tr>
      <tr>
	<th>2</th><th>Account 2</th>
      </tr>
      </table>
    <p><a class="logout" href="logout.php">logout</a></p>
  </body>
</html>

