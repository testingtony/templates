<?php
   session_start(); // Starting Session
   $error=''; // Variable To Store Error Message
   if (isset($_POST['submit'])) {
      if (empty($_POST['username']) || empty($_POST['password'])) {
         $error = "Username or Password is invalid";
      }
      else
      {
         // Define $username and $password
         $username=$_POST['username'];
         $password=$_POST['password'];
         if ($username=='absample' && $password=='letmein') {
            $_SESSION['login_user']=$username; // Initializing Session
	    $destination = "index.php";
	    if($_POST['location'] != '') {
 	       $destination = $_POST['location'];
	    }
	    header("location: " . $destination);
	    $error = "Location" . $destination;
         } else {
            $error = "Username or Password is invalid";
        }
      }
   }
 ?>
