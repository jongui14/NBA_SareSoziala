<?php
	include '../datuak/domeinua.php';
	include '../datuak/datuak.php';
    $wsdl = $user_wsdl;
	session_start();
	
	// Require Soap
	error_reporting(E_ERROR | E_PARSE);
	require_once('../lib/nusoap/nusoap.php');

	$jokalaria = new Jokalaria();
	$jokalaria->idJokalaria=$_GET['idJokalaria'];
	
	
	echo '<table class="table">
		  <thead class="thead-dark">
				<tr>
					<th></th>
					<th>Jokalaria</th>
					<th>Soldata</th>
					<th>Taldea</th>
					<th>Posizioa</th>
					<th>Dorsala</th>
					<th>Altuera</th>
					<th>Pisua</th>
					<th>Jaiotze-data</th>
					<th>Nazionalitatea</th>
				</tr>
			</thead>
			<tbody>';
	
	echo '<tr>
			<th scope="row"><div class="text-center"><img src="https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/'.$_GET['idJokalaria'].'.png" class="rounded" width="68" height="50"></div></th>
			<td>'.$_GET['izen_osoa'].'</td>
			<td>'.$_GET['soldata'].'</td>
			<td><a href="./taldeak.php?idTaldea='.$_GET['idTaldea'].'"><div class="text-center"><img src="../img/taldea/'.$_GET['idTaldea'].'.png" class="rounded" width="50" height="50"></div></a></td>
			<td>'.$_GET['posizioa'].'</td>
			<td>'.$_GET['dortsala'].'</td>
			<td>'.$_GET['altuera'].'</td>
			<td>'.$_GET['pisua'].'</td>
			<td>'.substr($_GET['jaiotzeData'],0,10).'</td>
			<td>'.$_GET['nazionalitatea'].'</td>
		</tr>';
	echo '</tbody></table>';
	
	
	
	$client = new nusoap_client($user_wsdl,true);
	 
	// Check for any error(s)
	$err = $client->getError();
	if ($err){
		echo '<h2>Constructor error</h2><pre>' . $err . '</pre>';
		exit();
	
	}else{ 
		// specific service call     
		$result = $client->call('JokalariarenPuntuaketak', 
			array(	'arg0' => $jokalaria )
		);
		//print_r ($result['return']);


		echo '
		<table class="table">
		  <thead class="thead-dark">
			<tr>
			  <th scope="col">Jardunaldia</th>
			  <th scope="col">Puntuak</th>
			</tr>
		  </thead>
		  <tbody>';
		
		$jokalariaren_puntuazioak = $result['return'];
		$astea=sizeof($jokalariaren_puntuazioak);;
		foreach ($jokalariaren_puntuazioak as &$jokalariaren_puntuazioa) {
			echo '<tr>
					  <td>'.$astea.'</td>
					  <td>'.$jokalariaren_puntuazioa['puntuak'].'</td>
				</tr>';
			$astea--;
		}
		
	}

?>
