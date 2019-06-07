<?php
	include '../datuak/domeinua.php';
	include '../datuak/datuak.php';
    $wsdl = $admin_wsdl;
	session_start();
	
	$urtea=$_GET['urtea'];
	$hilabetea=$_GET['hilabetea'];
	$eguna=$_GET['eguna'];
	
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
		$result = $client->call('JardunaldiakHasieratu', array('arg0' =>$urtea, 'arg1' =>$hilabetea, 'arg2' =>$eguna) );
		print_r( $result );
	}

?>