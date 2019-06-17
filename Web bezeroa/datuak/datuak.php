<?php

$zerbitzaria="localhost";

$admin_wsdl="http://".$zerbitzaria.":9999/admin?wsdl";
$user_wsdl="http://".$zerbitzaria.":9999/userlogged?wsdl";
$usernot_wsdl="http://".$zerbitzaria.":9999/usernotlogged?wsdl";



function erabiltzailearen_nick($idErabiltzailea){
	session_start();
	$erabiltzaile_guztiak=$_SESSION['erabiltzaile_guztiak'];	
	foreach ($erabiltzaile_guztiak as &$erabiltzaile_bakoitza) {
		if($erabiltzaile_bakoitza['idErabiltzailea']==$idErabiltzailea){
			return  $erabiltzaile_bakoitza['nick'];
		}
	}
	return ' ';
}

function Transakzioaren_Mezua($transakzioa){
	list($indizea,$jokalaria)=explode("_",$transakzioa);
	if($indizea==0){
		return 'Ongietorri bonusa';
	}elseif($indizea==1){
		return 'Jardunaldian 1. postua lortzeagatik';
	}elseif($indizea==2){
		return 'Jardunaldian 2. postua lortzeagatik';
	}elseif($indizea==3){
		return 'Jardunaldian 3. postua lortzeagatik';
	}elseif($indizea==4){
		return 'Jokalaria erosi: '.$jokalaria;
	}elseif($indizea==5){
		return 'Jokalaria saldu: '.$jokalaria;
	}
	return $indizea;
}

function DiruaFormatuarekin($dirua){
	$soldata='';
	while( strlen($dirua) > 3){
		$soldata=','.substr($dirua,strlen($dirua)-3,strlen($dirua)).$soldata;
		$dirua=substr($dirua,0,strlen($dirua)-3);
	}
	$soldata='$'.$dirua.$soldata;
	return $soldata;
}










?>