<?php
	include '../datuak/domeinua.php';
	include '../datuak/datuak.php';
    $wsdl = $user_wsdl;
	session_start();

if (! isset($_SESSION["erabiltzailea"])){
	header('Location: ../logeatu_gabea/kontura_sartu.php');
}

$erabiltzaile = new Erabiltzailea();
$erabiltzaile = $_SESSION["erabiltzailea"];



?>
<html>
<head>

<link rel="icon" type="image/png" href="../img/ic_launcher.png">

<link rel="stylesheet" href="../css/styles.css">
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css"><!-- Latest compiled and minified CSS -->
<script src="../lib/jquery/jquery-3.3.1.min.js"></script><!-- jQuery library -->
<script src="../lib/bootstrap/js/bootstrap.min.js"></script><!-- Latest compiled JavaScript -->


</head>
<body>


<?php include "../datuak/menu_log.html"; ?>



<?php	
	// Require Soap
	error_reporting(E_ERROR | E_PARSE);
	require_once('../lib/nusoap/nusoap.php');
	 
	//Create Client & set WSDL file path
	$client = new nusoap_client($wsdl,true);
	 
	// Check for any error(s)
	$err = $client->getError();
	if ($err){
		echo '<h2>Constructor error</h2><pre>' . $err . '</pre>';
		exit();
	
	}else{ 
		// specific service call     
		$result = $client->call('KlasifikazioOrokorra', 
			array(	'arg0' => $_SESSION["komunitatea"])
		);
		//print_r ($result['return']);


		echo '
		<table class="table">
		  <thead class="thead-dark">
			<tr>
			  <th scope="col">Postua</th>
			  <th scope="col">Erabiltzailea</th>
			  <th scope="col">Puntuak</th>
			</tr>
		  </thead>
		  <tbody>';
		
		$erabiltzailearen_puntuazioak = $result['return'];
		$pos=0;
		foreach ($erabiltzailearen_puntuazioak as &$erabiltzailearen_puntuazioa) {
			$pos++;
			$erabiltzailea=$erabiltzailearen_puntuazioa['erabiltzailea'];
			echo '<tr>
					  <td>'.$pos.'</td>
					  <td>'.$erabiltzailea['nick'].'</td>
					  <td>'.$erabiltzailearen_puntuazioa['puntuak'].'</td>
				</tr>';
		}
		
	}

?>



</body>
</html>