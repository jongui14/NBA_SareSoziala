<?php
	include '../datuak/domeinua.php';
	include '../datuak/datuak.php';
    $wsdl = $user_wsdl;
	session_start();

if (! isset($_SESSION["erabiltzailea"])){
	header('Location: ../logeatu_gabea/kontura_sartu.php');
}

if($_SESSION["erabiltzailea"]->administratzailea==false){
	header('Location: ./index.php');
}

$erabiltzaile = new Erabiltzailea();
$erabiltzaile = $_SESSION["erabiltzailea"];



?>
<html>
<head>
 <style type ="text/css" >
   .footer{ 
       position: fixed;     
       text-align: center;    
       bottom: 0px; 
       width: 100%;
   }  
</style>


<link rel="stylesheet" href="../css/styles.css">
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css"><!-- Latest compiled and minified CSS -->
<script src="../lib/jquery/jquery-3.3.1.min.js"></script><!-- jQuery library -->
<script src="../lib/bootstrap/js/bootstrap.min.js"></script><!-- Latest compiled JavaScript -->


<script>
function erabiltzailea_ezabatu(idErabiltzailea){
	
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open("GET", "../php/erabiltzailea_ezabatu.php?idErabiltzailea="+idErabiltzailea, true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var res=this.responseText;
			location.reload();
        }
	};
}
</script>

</head>
<body>

<?php include "../datuak/menu_log.html"; ?>


<?php
	error_reporting(E_ERROR | E_PARSE);
	echo '<table class="table">
		  <thead class="thead-dark">
			<tr>
			  <th scope="col"><center>nick</center></th>
			  <th scope="col"></th>
			</tr>
		  </thead>
		';
		
	
		
	$erabiltzaile_guztiak=$_SESSION['erabiltzaile_guztiak'];
	//print_r ($erabiltzaile_guztiak);
	
	foreach ($erabiltzaile_guztiak as &$erabiltzaile_bakoitza) {
		echo '<tr>					
				<td><center>'.$erabiltzaile_bakoitza['nick'].'</center></td>';
					
			if($erabiltzaile_bakoitza['administratzailea']=='false'){
				echo '<td><center><img src="../img/tick_gorria.png" class="rounded" width="25" height="25" onclick="erabiltzailea_ezabatu('.$erabiltzaile_bakoitza['idErabiltzailea'].')"></center></td>';
			}else{
				echo '<td></td>';
			}
			echo  '</tr>';
			
	}
	echo '</table>';

?>



</body>
</html>