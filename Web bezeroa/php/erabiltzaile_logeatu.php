<?php
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
		$result = $client->call('ErabiltzaileaLogeatu', 
			array(	'arg0' =>$nick,
					'arg1' =>$pass,
			)
		);
		$erabiltzailea=$result['return'];
		if($erabiltzailea['idErabiltzailea']>1){
			echo 'login_eginda<br>';
			print_r ($result['return']);
		

			$erabiltzaile = new Erabiltzailea();
			$erabiltzaile->idErabiltzailea = $erabiltzailea['idErabiltzailea'];
			$erabiltzaile->nick = $erabiltzailea['nick'];
			$erabiltzaile->email = $erabiltzailea['email'];
			$erabiltzaile->izenOsoa = $erabiltzailea['izenOsoa'];
			$erabiltzaile->dirua = $erabiltzailea['dirua'];
			if($erabiltzailea['administratzailea']=='true'){
				$erabiltzaile->administratzailea=true;
			}else{
				$erabiltzaile->administratzailea=false;
			}
			$erabiltzaile->hizkuntza = $erabiltzailea['hizkuntza'];
			$erabiltzaile->koloreak = $erabiltzailea['koloreak'];
			$erabiltzaile->orduDiferentzia = $erabiltzailea['orduDiferentzia'];	
			$_SESSION["erabiltzailea"]=$erabiltzaile;
			
			$komunitatea = new Komunitatea();
			$komunitatea->idKomunitatea = $erabiltzailea['komunitatea'];
			$_SESSION["komunitatea"]=$komunitatea;
			
			
			
			$client = new nusoap_client($user_wsdl,true);
					$result = $client->call('KomunitateaLortu', 
				array(	'arg0' =>$komunitatea)
			);
			$res=$result['return'];
			$komunitatea->saria1=$res['saria1'];
			$komunitatea->saria2=$res['saria2'];
			$komunitatea->saria3=$res['saria3'];
			
			
			  
			$result = $client->call('ErabiltzaileakLortu', 
				array(	'arg0' =>$komunitatea)
			);echo '<br>';
			print_r ($result['return']);

			$erabiltzaile_guztiak=$result['return'];
			if (array_key_exists("idErabiltzailea",$erabiltzaile_guztiak)){
				$erabiltzaile_guztiak=array($erabiltzaile_guztiak);
			}
			$_SESSION["erabiltzaile_guztiak"]=$erabiltzaile_guztiak;
			
			
			
		}else{
			echo 'login_okerra';
		}
		
		
		
	}
	

?>