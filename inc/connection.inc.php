<?php

$con = new mysqli('sql12.freemysqlhosting.net', 'sql12369803', '8fzQjG6qQj', 'sql12369803');

if($con->connect_errno > 0){
    die('Unable to connect to database [' . $con->connect_error . ']');
}

?>