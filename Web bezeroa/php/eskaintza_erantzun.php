<?php
	$idEskaintza = $_GET['idEskaintza'];
	$idMerkatukoJokalaria = $_GET['idMerkatukoJokalaria'];
	$erantzuna= $_GET['erantzuna'];
	
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
		// specific service call
		$merkatukoJokalaria = new MerkatukoJokalaria();
		$merkatukoJokalaria->idMerkatukoJokalaria=$idMerkatukoJokalaria;
		
		$eskaintza = new Eskaintza();
		$eskaintza->idEskaintza=$idEskaintza;

		
		$result = $client->call('EskaintzaErantzun', 
			array(	'arg0' =>$merkatukoJokalaria, 
					'arg1'=> $eskaintza, 
					'arg2'=> $erantzuna 
				  )
						);
		print_r($result);
	
	}

?>