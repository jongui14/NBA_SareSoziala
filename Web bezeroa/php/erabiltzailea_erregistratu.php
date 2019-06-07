<?php
	include '../datuak/domeinua.php';
	include '../datuak/datuak.php';
    $wsdl = $usernot_wsdl;
	session_start();

	//$komunitatea = $_GET['komunitatea'];//$_SESSION['komunitatea_lag'];
	$komunitatea =$_SESSION['komunitatea_lag'];
	
	$nick = $_GET['nick'];
	$email = $_GET['email'];
	$izen_osoa = $_GET['izen_osoa'];
	$pasahitza = $_GET['pasahitza'];
	$admin = $_GET['admin'];
	
	// Require Soap
	error_reporting(E_ERROR | E_PARSE);
	require_once('../lib/nusoap/nusoap.php');
	 
	//Create Client & set WSDL file path
	$client = new nusoap_client($wsdl,'wsdl');
	 
	// Check for any error(s)
	$err = $client->getError();
	if ($err){
		echo '<h2>Constructor error</h2><pre>' . $err . '</pre>';
		exit();
	
	}else{ 
		// specific service call
		$result = $client->call('ErabiltzaileaErregistratu', 
			array(	'arg0' =>$komunitatea,
					'arg1' =>$nick,
					'arg2' =>$email,
					'arg3' =>$izen_osoa,
					'arg4' =>$pasahitza,
					'arg5' =>$admin
			)
		);
		print_r($result);
	
	}
?>