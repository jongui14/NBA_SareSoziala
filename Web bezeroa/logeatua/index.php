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


</head>
<body>

<?php include "../datuak/menu_log.html"; ?>

<?php
        $wsdl = $usernot_wsdl;
	
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
		if ( isset($_GET["year"]) && isset($_GET["month"]) && isset($_GET["day"])  ){
			$year=$_GET['year'];
			$month=$_GET['month'];
			$day=$_GET['day'];
		}else{
			$year=date('Y');
			$month=date('m');
			$day=date('d');
		}
		$result = $client->call('JornadakoPartiduak', 
			array(	'arg0' =>"$year-$month-$day")
		);
		//print_r ($result['return']);
		
		$date = DateTime::createFromFormat("Y-m-d", "$year-$month-$day");
		$date1 = DateTime::createFromFormat("Y-m-d", "$year-$month-$day");$date1->modify('-1 day');
		$date2 = DateTime::createFromFormat("Y-m-d", "$year-$month-$day");$date2->modify('+1 day');
		
		
		$year1=date_format($date1, 'Y');
		$month1=date_format($date1, 'm');
		$day1=date_format($date1, 'd');

		$year2=date_format($date2, 'Y');
		$month2=date_format($date2, 'm');
		$day2=date_format($date2, 'd');
		
		echo 
		'<table class="table">
		  <thead class="thead-dark">
			<tr>
			  <th style="cursor:hand" onclick="window.location.href = \'./partiduen_emaitzak.php?year='.$year1.'&month='.$month1.'&day='.$day1.'\';" scope="col" align="left"><center>'.$year1.'-'.$month1.'-'.$day1.'</center></th>
			  <th></th>
			  <th style="cursor:hand" scope="col"><center>'.$year.'-'.$month.'-'.$day.'</center></th>
			  <th></th>
			  <th style="cursor:hand" onclick="window.location.href = \'./partiduen_emaitzak.php?year='.$year2.'&month='.$month2.'&day='.$day2.'\';" scope="col" align="right"><center>'.$year2.'-'.$month2.'-'.$day2.'</center></th>
			</tr>
		  </thead>
		</table>';
		
		echo 
		'<table class="table">
			<thead class="thead-light">
			  <tr>
				<th><center>Etxekoa</center></th>
				<th></th>
				<th></th>
				<th></th>
				<th><center>Kanpokoa</center></th>
			  </tr>
			</thead>
			<tbody>';
		
		$partiduak=$result['return'];
		if (array_key_exists("etxeko_taldea",$partiduak)){
			$partiduak=array($partiduak);
		}
		foreach ($partiduak as &$partidua) {
			$etxeko_taldea=$partidua['etxeko_taldea'];
			$kanpoko_taldea=$partidua['kanpoko_taldea'];
			
			list($ordua,$min,$seg)=explode(":",$partidua['orduaString']);
			if($min<10){
				$min='0'.$min;
			}
			
			if( $partidua['partiduaren_egoera']==1){
				echo '<tr>
					<td><center><img src="../img/taldea/'.$etxeko_taldea['idTaldea'].'.png" class="rounded" width="50" height="50"></center></td>
					<td><center>'.$etxeko_taldea['urlIzena'].'</center></td>
					<td><center>'.($ordua-18).':'.$min.'</center></td>
					<td><center>'.$kanpoko_taldea['urlIzena'].'</center></td>
					<td><center><img src="../img/taldea/'.$kanpoko_taldea['idTaldea'].'.png" class="rounded" width="50" height="50"></center></td>
				  </tr>';
				  
			}else if( $partidua['partiduaren_egoera']==2){
				
				
			}else if( $partidua['partiduaren_egoera']==3){
				echo '<tr>
					<td><center><img src="../img/taldea/'.$etxeko_taldea['idTaldea'].'.png" class="rounded" width="50" height="50"></center></td>
					<td><center>'.$etxeko_taldea['urlIzena'].'<br>'.$partidua['etxeko_puntuak'].'</center></td>
					<td><center>Final</center></td>
					<td><center>'.$kanpoko_taldea['urlIzena'].'<br>'.$partidua['kanpoko_puntuak'].'</center></td>
					<td><center><img src="../img/taldea/'.$kanpoko_taldea['idTaldea'].'.png" class="rounded" width="50" height="50"></center></td>
				  </tr>';
			}
			
		}
		
	}

?>



</body>
</html>





