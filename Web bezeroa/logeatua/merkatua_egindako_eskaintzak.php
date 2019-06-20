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
	// Require Soap
	error_reporting(E_ERROR | E_PARSE);
	require_once('../lib/nusoap/nusoap.php');
	$client = new nusoap_client($wsdl,true);
	$err = $client->getError();
	if ($err){
		echo '<h2>Constructor error</h2><pre>' . $err . '</pre>';
		exit();
	
	}else{ 
		// specific service call     
		$result = $client->call('EgindakoEskaintzak', 
			array(	'arg0' =>$erabiltzaile )
		);
		//print_r ($result['return']);
		$eskaintzak=$result['return'];
		if (array_key_exists("idEskaintza",$eskaintzak)){
			$eskaintzak=array($eskaintzak);
		}

		echo '<br><h3>Tramitatu gabekoak</h3>';
		echo '
		<table class="table">
		  <thead class="thead-dark">
			<tr>
			  <th scope="col"></th>
			  <th scope="col"><center>Jokalaria</center></th>
			  <th scope="col"><center>Taldea</center></th>
			  <th scope="col"><center>Erabiltzailea</center></th>
			  <th scope="col"><center>Eskainta</center></th>
			</tr>
		  </thead>
		  <tbody>';
		
		foreach ($eskaintzak as &$eskaintza) {
			$merkatukoJokalaria=$eskaintza['merkatukoJokalaria'];
			if($merkatukoJokalaria['tramitatua']=='false'){
				$jokalaria=$merkatukoJokalaria['jokalaria'];
			echo '<tr>
					  <td scope="row"><center><div class="text-center"><img src="https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/'.$jokalaria['idJokalaria'].'.png" class="rounded" width="68" height="50"></div></center></td>
					  <td><center>'.$jokalaria['izena'].' '.$jokalaria['abizena'].'</center></td>
					  <td><center><div class="text-center"><img src="../img/taldea/'.$jokalaria['taldeaByIdTaldea'].'.png" class="rounded" width="50" height="50"></div></center></td>
					  <td><center><i>'.erabiltzailearen_nick($eskaintza['erabiltzailea']).'</i></center></td>
					  <td><center>'.DiruaFormatuarekin($eskaintza['eskaintza']).'</center></td>
					</tr>';
			}
		}
		echo '</tbody></table>';
		
		echo '<hr><br>';
		
		echo '<h3>Tramitatuak</h3>';
				echo '
		<table class="table">
		  <thead class="thead-dark">
			<tr>
			  <th scope="col"></th>
			  <th scope="col"><center>Jokalaria</center></th>
			  <th scope="col"><center>Taldea</center></th>
			  <th scope="col"><center>Erabiltzailea</center></th>
			  <th scope="col"><center>Eskainta</center></th>
			</tr>
		  </thead>
		  <tbody>';
		
		foreach ($eskaintzak as &$eskaintza) {
			$merkatukoJokalaria=$eskaintza['merkatukoJokalaria'];
			if($merkatukoJokalaria['tramitatua']=='true'){
				$jokalaria=$merkatukoJokalaria['jokalaria'];
			echo '<tr>
					  <td scope="row"><center><div class="text-center"><img src="https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/'.$jokalaria['idJokalaria'].'.png" class="rounded" width="68" height="50"></div></center></td>
					  <td><center>'.$jokalaria['izena'].' '.$jokalaria['abizena'].'</center></td>
					  <td><center><div class="text-center"><img src="../img/taldea/'.$jokalaria['taldeaByIdTaldea'].'.png" class="rounded" width="50" height="50"></div></center></td>
					  <td><center><i>'.erabiltzailearen_nick($eskaintza['erabiltzailea']).'</i></center></td>
					  <td><center>'.DiruaFormatuarekin($eskaintza['eskaintza']).'</center></td>
					</tr>';
			}
		}
		echo '</tbody></table>';
		
	}

?>

</body>
</html>