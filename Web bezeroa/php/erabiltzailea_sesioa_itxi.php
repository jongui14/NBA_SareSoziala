<?php
	//include '../datuak/domeinua.php';
	//include '../datuak/datuak.php';
    //$wsdl = $user_wsdl;
	session_start();
	
	unset($_SESSION["erabiltzailea"]);
	unset($_SESSION["nick"]);
	unset($_SESSION["email"]);
	unset($_SESSION["izenOsoa"]);
	unset($_SESSION["dirua"]);
	unset($_SESSION["administratzailea"]);
	unset($_SESSION["orduDiferentzia"]);
	unset($_SESSION["koloreak"]);
	unset($_SESSION["hizkuntza"]);


	header('Location: ../logeatu_gabea/kontura_sartu.php');


?>