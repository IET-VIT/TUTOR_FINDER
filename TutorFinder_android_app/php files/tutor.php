<?php 
		$con=mysqli_connect("sql12.freemysqlhosting.net","sql12369803","8fzQjG6qQj","sql12369803");
	
		$t_id = $_POST["t_id"];

		$sql = "SELECT * FROM tutor WHERE t_id='$t_id'";
		$result = mysqli_query($con,$sql);
        
        if ($result->num_rows > 0) {
          $tutor = array(); 
          while($row = $result->fetch_assoc()) {
            $temp = [
              'inst_name'=>$row["inst_name"],
              'prefer_sub'=>$row["prefer_sub"],
              'class'=>$row["class"],
              'medium'=>$row["medium"],
              'prefer_location'=>$row["prefer_location"],
              'salary'=>$row["salary"]
            ];
            array_push($tutor, $temp);
          }
          echo json_encode($tutor);
        } else {
          echo "0 results";
        }
?>