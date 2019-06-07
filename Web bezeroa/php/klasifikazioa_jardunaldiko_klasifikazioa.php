<?php

	include '../datuak/domeinua.php';
	include '../datuak/datuak.php';
    $wsdl = $user_wsdl;
	session_start();

	
	$jardunaldia = new Jardunaldia();
	$jardunaldia->idJardunaldia=$_GET['idJardunaldia'];
	
	
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
		$result = $client->call('JornadakoKlasifikazioa', 
			array(	'arg0' => $_SESSION["komunitatea"], 'arg1' => $jardunaldia )
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
