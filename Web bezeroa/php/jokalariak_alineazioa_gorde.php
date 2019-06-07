<?php
	include '../datuak/domeinua.php';
	include '../datuak/datuak.php';
    $wsdl = $user_wsdl;
	session_start();

	$jokalaria1 = new Jokalaria();
	$jokalaria1->idJokalaria = $_GET['id_jokalaria1'];
	
	$jokalaria2 = new Jokalaria();
	$jokalaria2->idJokalaria = $_GET['id_jokalaria2'];
	
	$jokalaria3 = new Jokalaria();
	$jokalaria3->idJokalaria = $_GET['id_jokalaria3'];
	
	$jokalaria4 = new Jokalaria();
	$jokalaria4->idJokalaria = $_GET['id_jokalaria4'];
	
	$jokalaria5 = new Jokalaria();
	$jokalaria5->idJokalaria = $_GET['id_jokalaria5'];
	
	$mota=$_GET['mota'];
		
    $wsdl = 'http://localhost:9999/userlogged?wsdl';
	
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
		$result = $client->call('AlineazioaGorde', 
			array(	'arg0' =>$_SESSION["erabiltzailea"],
					'arg1' =>$jokalaria1,
					'arg2' =>$jokalaria2,
					'arg3' =>$jokalaria3,
					'arg4' =>$jokalaria4,
					'arg5' =>$jokalaria5,
					'arg6' =>$mota,
				  )
		);
		print_r ($result['return']);
		

		
	}

?>