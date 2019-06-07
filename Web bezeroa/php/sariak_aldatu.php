<?php
	$saria1 = $_GET['saria1'];
	$saria2 = $_GET['saria2'];
	$saria3 = $_GET['saria3'];
	
	include '../datuak/domeinua.php';
	include '../datuak/datuak.php';
    $wsdl = $user_wsdl;
	session_start();
	
	$komunitatea=$_SESSION["komunitatea"];
	$komunitatea->saria1=$saria1;
	$komunitatea->saria2=$saria2;
	$komunitatea->saria3=$saria3;


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
		// specific service call
		$result = $client->call('KomunitateaGorde', array('arg0' =>$komunitatea ));
		print_r($result);
	
	}

?>