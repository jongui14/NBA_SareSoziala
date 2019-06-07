<?php

	include '../datuak/domeinua.php';
	include '../datuak/datuak.php';
    $wsdl = $user_wsdl;
	session_start();

	$merkatukoJokalaria = new MerkatukoJokalaria();
	$merkatukoJokalaria->idMerkatukoJokalaria = $_GET['idMerkatukoJokalaria'];
		
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
		$result = $client->call('OfertaEgin', 
			array(	'arg0' =>$merkatukoJokalaria,
					'arg1' =>$_SESSION["erabiltzailea"],
					'arg2' =>$_GET['ofertarenPrezioa'],
				  )
		);
		print_r ($result['return']);
		

		
	}

?>