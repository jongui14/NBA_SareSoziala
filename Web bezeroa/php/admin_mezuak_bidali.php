<?php
	include '../datuak/domeinua.php';
	include '../datuak/datuak.php';
    $wsdl = $admin_wsdl;
	//session_start();
	
	$mezua=$_GET['mezua'];
	
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
		$result = $client->call('MezuGeneralakBidali', array('arg0' =>$mezua) );
		print_r( $result );
	}

?>