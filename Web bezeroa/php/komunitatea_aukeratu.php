<?php
	
	if ( isset($_SESSION["nick"])){
		header('Location: ../logeatua/index.php');
	}
	
	include '../datuak/domeinua.php';
	include '../datuak/datuak.php';
    $wsdl = $usernot_wsdl;
	session_start();

	$nick = $_GET['nick'];//_GET
	$pass = $_GET['pass'];//_POST

	
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
		$result = $client->call('KomunitateaAukeratu', 
			array(	'arg0' =>$nick,
					'arg1' =>$pass,
					//'arg2' =>$izen_osoa,
					//'arg3' =>$ongietorri_mezua,
					//'arg4' =>$saria1,
					//'arg5' =>$saria2,
					//'arg6' =>$saria3,
			)
		);
		$res = $result['return'];
		
		$komunitatea = new Komunitatea();
		$komunitatea->idKomunitatea = $res['idKomunitatea'];
		$komunitatea->nick = $res['nick'];
		$_SESSION['komunitatea_lag']= $komunitatea;
		
		echo $res['idKomunitatea'];
		//print_r ($result['return']);
	}
	

?>