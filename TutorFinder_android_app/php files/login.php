<?php 
		$con=mysqli_connect("sql12.freemysqlhosting.net","sql12369803","8fzQjG6qQj","sql12369803");
	
		$email = $_POST["email"];
		$pass = $_POST["pass"];

		$sql = "SELECT * FROM user WHERE email='$email' AND pass='$pass'";
		$result = mysqli_query($con,$sql);
        
        if ($result->num_rows > 0) {
          $users = array(); 
          while($row = $result->fetch_assoc()) {
            $temp = [
              'id'=>$row["id"],
              'fullname'=>$row["fullname"],
              'email'=>$row["email"],
              'phone'=>$row["phone"],
              'type'=>$row["type"],
              'address'=>$row["address"],
              'gender'=>$row["gender"]
            ];
            array_push($users, $temp);
          }
          echo json_encode($users);
        } else {
          echo "0 results";
        }
?>