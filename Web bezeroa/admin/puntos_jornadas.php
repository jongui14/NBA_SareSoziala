<html>
<head>

<link rel="stylesheet" href="../css/styles.css">
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css"><!-- Latest compiled and minified CSS -->
<script src="../lib/jquery/jquery-3.3.1.min.js"></script><!-- jQuery library -->
<script src="../lib/bootstrap/js/bootstrap.min.js"></script><!-- Latest compiled JavaScript -->

<script>
function puntuak_eguneratu(idJardunaldia,hasierakoEguna){
	
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open("GET", "../php/admin_jardunaldiko_puntuak.php?idJardunaldia="+idJardunaldia+"&hasierakoEguna="+hasierakoEguna, true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var res=this.responseText;
			alert(res);
        }
	};
}
</script>

</head>
<body>

<?php

	include '../datuak/domeinua.php';
	include '../datuak/datuak.php';
    $wsdl = 'http://'.$zerbitzaria.':9999/admin?wsdl';
	
	// Require Soap
	error_reporting(E_ERROR | E_PARSE);
	require_once('../lib/nusoap/nusoap.php');
	 
	//Create Client & set WSDL file path
	$client = new nusoap_client($wsdl,true);
	 
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
		$result = $client->call('JardunaldiakLortu' );
		//print_r( $result['return'] );
		
		echo '
		<table class="table">
		  <thead class="thead-dark">
			<tr>
			  <th scope="col">Id</th>
			  <th scope="col">Hasiera</th>
			  <th scope="col">Bukaera</th>
			  <th scope="col"></th>
			</tr>
		  </thead>
		  <tbody>';
		
		$jardunaldiak = $result['return'];
		foreach ($jardunaldiak as &$jardunaldia) {
			$date = DateTime::createFromFormat("Y-m-d", substr($jardunaldia['hasierakoEguna'],0,10) );$date->modify('+6 day');
			
			$year1=date_format($date, 'Y');
			$month1=date_format($date, 'm');
			$day1=date_format($date, 'd');
		
			echo '<tr>
					  <td>'.$jardunaldia['idJardunaldia'].'</td>
					  <td>'.substr($jardunaldia['hasierakoEguna'],0,10).'</td>
					  <td>'.$year1.'-'.$month1.'-'.$day1.'</td>
					  <td><button type="button" class="btn btn-success btn-lg btn-block" onclick="puntuak_eguneratu(\''.$jardunaldia['idJardunaldia'].'\',\''.substr($jardunaldia['hasierakoEguna'],0,10).'\')">Puntuak</button></td>
					</tr>';
		}
	}

?>