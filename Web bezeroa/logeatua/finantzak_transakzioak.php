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
		$result = $client->call('ErabiltzailearenTransakzioak', 
			array(	'arg0' =>$erabiltzaile)
		);
		$res=$result['return'];
		//print_r ($res);
		
		echo '
		<table class="table">
		  <thead class="thead-dark">
			<tr>
			  <th scope="col"></th>
			  <th scope="col"></th>
			  <th scope="col"></th>
			  <th scope="col">'.$erabiltzaile->dirua.'</th>
			</tr>
		  </thead>
		</table><hr>';

		echo '
		<table class="table">
		  <thead class="thead-dark">
			<tr>
			  <th scope="col"><center>Kantitatea</center></th>
			  <th scope="col"><center>Transakzioa</center></th>
			  <th scope="col"><center>Data</center></th>
			</tr>
		  </thead>
		';
		
	
		
		$transakzioak=$res;
		if (array_key_exists("idTransakzioa",$transakzioak)){
			$transakzioak=array($transakzioak);
		}
		foreach ($transakzioak as &$transakzioa) {
			$nick=erabiltzailearen_nick($mezua['erabiltzailea']);
			echo '<tr>
					<td><center>'.$transakzioa['kantitatea'].'</center></td>
					<td><center>'.Transakzioaren_Mezua($transakzioa['mezua']).'</center></td>
					<td><center>'.$transakzioa['eguna'].'</center></td>
				</tr>';
			
		}
		echo '</table>';
		
			
					
		
	}

?>



</body>
</html>