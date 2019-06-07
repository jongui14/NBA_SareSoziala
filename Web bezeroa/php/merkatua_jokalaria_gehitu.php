<?php
	include '../datuak/domeinua.php';
	include '../datuak/datuak.php';
    $wsdl = $user_wsdl;
	session_start();

	$jokalaria = new Jokalaria();
	$jokalaria->idJokalaria = $_GET['id_jokalaria'];
		
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
		$result = $client->call('MerkatuanJarri', 
			array(	'arg0' =>$_SESSION["erabiltzailea"],
					'arg1' =>$jokalaria,
					'arg2' =>$_GET['hasierakoPrezioa'],
				  )
		);
		print_r ($result['return']);
		

		
	}

?>