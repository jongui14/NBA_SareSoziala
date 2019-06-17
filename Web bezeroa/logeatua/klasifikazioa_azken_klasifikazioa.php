<?php
	include '../datuak/domeinua.php';
	include '../datuak/datuak.php';
    $wsdl = $user_wsdl;
	session_start();

if (! isset($_SESSION["erabiltzailea"])){
	header('Location: ../logeatu_gabea/kontura_sartu.php');
}

$erabiltzaile = new Erabiltzailea();
$erabiltzaile = $_SESSION["erabiltzailea"];


?>
<html>
<head>

<link rel="icon" type="image/png" href="../img/ic_launcher.png">

<link rel="stylesheet" href="../css/styles.css">
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css"><!-- Latest compiled and minified CSS -->
<script src="../lib/jquery/jquery-3.3.1.min.js"></script><!-- jQuery library -->
<script src="../lib/bootstrap/js/bootstrap.min.js"></script><!-- Latest compiled JavaScript -->

<script>
function jardunaldiko_klasifikazioa(idJardunaldia){
	document.getElementById("klasifikazioa").innerHTML  = "Kargatzen...";
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open("GET", "../php/klasifikazioa_jardunaldiko_klasifikazioa.php?idJardunaldia="+idJardunaldia, true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var html=this.responseText;
			document.getElementById("klasifikazioa").innerHTML  = html;
        }
	};
}

function asteaAldatu(){
	var idJardunaldia = document.getElementById("asteaSelect").value;
	jardunaldiko_klasifikazioa(idJardunaldia);
}
</script>

</head>
<body>


<?php include "../datuak/menu_log.html"; ?>


<?php	
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
		$result = $client->call('OrainArtekoJardunaldiak');
		//print_r( $result['return'] );
		
		echo '<select id="asteaSelect" class="form-control form-control-lg" onchange="asteaAldatu()">';	

		$jardunaldiak = $result['return'];
		$idJardunaldia=0;
		$astea = sizeof($jardunaldiak);
		foreach ($jardunaldiak as &$jardunaldia) {
			$date = DateTime::createFromFormat("Y-m-d", substr($jardunaldia['hasierakoEguna'],0,10) );$date->modify('+6 day');
			
			$year1=date_format($date, 'Y');
			$month1=date_format($date, 'm');
			$day1=date_format($date, 'd');
		
			echo '<option value="'.$jardunaldia['idJardunaldia'].'">'.$astea.'. astea ('.substr($jardunaldia['hasierakoEguna'],0,10).' '.$year1.'-'.$month1.'-'.$day1.')</option>';
			if($astea==sizeof($jardunaldiak)){
				$idJardunaldia=$jardunaldia['idJardunaldia'];
			}
			$astea--;
		}
		
		echo '</select>';

		
	}

?>
<div id='klasifikazioa'>

</div>

<script type="text/javascript">
   jardunaldiko_klasifikazioa(<?php echo $idJardunaldia; ?>);
</script>

</body>
</html>