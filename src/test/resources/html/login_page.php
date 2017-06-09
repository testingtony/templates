<?php
include('login.php'); // Includes Login Script
?>
<!DOCTYPE html>
<html>
  <head>
    <title>Login Form in PHP with Session</title>
    <link href="style.css" rel="stylesheet" type="text/css">
  </head>
  <body>
    <div id="main">
      <h1>PHP Login Session Example</h1>
      <div id="login">
	<h2>Login Form</h2>
        <form action="" method="post">
	  <label>UserName :</label>
	  <?php
	   echo '<input type="hidden" name="location" value="';
           if(isset($_GET['location'])) {
              echo htmlspecialchars($_GET['location']);
           } else if (isset($_POST['location'])) {
              echo htmlspecialchars($_POST['location']);
	   }
           echo '">';
          ?>
	  <input id="name" name="username" placeholder="username" type="text">
	  <label>Password :</label>
	  <input id="password" name="password" placeholder="**********" type="password">
	  <input name="submit" type="submit" value=" Login ">
	  <span class="error"><?php echo $error; ?></span>
	</form>
      </div>
    </div>
  </body>
</html>
