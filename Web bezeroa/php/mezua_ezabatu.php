<?php
	$idMezua = $_GET['idMezua'];
	
	include '../datuak/domeinua.php';
	include '../datuak/datuak.php';
    $wsdl = $user_wsdl;
	session_start();

	error_reporting(E_ERROR | E_PARSE);
	require_once('../lib/nusoap/nusoap.php');
	 
	//Create Client & set WSDL file path
	$client = new nusoap_client($wsdl,'wsdl');
	 
	// Check for any error(s)
	$err = $client->getError();
	if ($err){
		echo '
		<h2>Constructor error</h2>
		<pre>' . $err . '</pre>
		';
		exit();
	
	
	}else{ 
		$mezua = new Mezua();
		$mezua->idMezua = $idMezua;
		
		// specific service call
		$result = $client->call('MezuaEzabatu', array('arg0' =>$mezua));
		print_r($result);
	
	}

?>