<?php
	$nick = $_GET['nick'];

    include '../datuak/domeinua.php';
	include '../datuak/datuak.php';
    $wsdl = $usernot_wsdl;
	//session_start();

	
	// Require Soap
	error_reporting(E_ERROR | E_PARSE);
	require_once('../lib/nusoap/nusoap.php');
	 
	//Create Client & set WSDL file path
	$client = new nusoap_client($wsdl,true);
	 
	// Check for any error(s)
	$err = $client->getError();
	if ($err){
		echo '
		<h2>Constructor error</h2>
		<pre>' . $err . '</pre>
		';
		exit();
	
	
	}else{ 
		// specific service call
		$result = $client->call('KomunitateaLibre', array('arg0' =>$nick ) );
		//print_r( $result );
		echo $result['return'];
	}

?>