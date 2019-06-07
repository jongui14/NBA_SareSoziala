<?php
	//_POST   _GET
	$nick = $_GET['nick'];
	$izen_osoa = $_GET['izen_osoa'];
	$pasahitza = $_GET['pasahitza'];
	$ongietorri_mezua = $_GET['ongietorri_mezua'];
	$saria1 = $_GET['saria1'];
	$saria2 = $_GET['saria2'];
	$saria3 = $_GET['saria3'];

    include '../datuak/domeinua.php';
	include '../datuak/datuak.php';
    $wsdl = $usernot_wsdl;
	session_start();

	
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
		$result = $client->call('KomunitateaErregistratu', 
			array(	'arg0' =>$nick,
					'arg1' =>$pasahitza,
					'arg2' =>$izen_osoa,
					'arg3' =>$ongietorri_mezua,
					'arg4' =>$saria1,
					'arg5' =>$saria2,
					'arg6' =>$saria3,
			)
		);
		//print_r( $result );
		$_SESSION['komunitatea_lag']=$result['return'];
		print_r ($result['return']);
	
	}
	

?>