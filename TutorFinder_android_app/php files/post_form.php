<?php
    $link=mysqli_connect("sql12.freemysqlhosting.net","sql12369803","8fzQjG6qQj","sql12369803");
 
    // $postby_id = ;
    // $subject = $_POST["subject"];
    // $class = $_POST["class"];
    // $medium = $_POST["medium"];
    // $salary = $_POST["salary"];
    // $location = $_POST["location"];
    // $p_university = $_POST["p_university"];
    // $deadline = $_POST["deadline"];
    
    $sql = "INSERT INTO post (postby_id,subject,class,medium,salary,location,p_university,deadline) VALUES ('$_POST[postby_id]','$_POST[subject]','$_POST[class]','$_POST[medium]','$_POST[salary]','$_POST[location]','$_POST[p_university]','$_POST[deadline]')";
    if(mysqli_query($link, $sql)){
        echo "Post Added Successfully.";
    } else{
        echo "ERROR: Could not able to execute $sql. " . mysqli_error($link);
    }
     
    // Close connection
    mysqli_close($link);
?>