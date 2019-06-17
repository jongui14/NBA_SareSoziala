<?php
	include '../datuak/domeinua.php';
	include '../datuak/datuak.php';
	$wsdl = $usernot_wsdl;
	
	// Require Soap
	error_reporting(E_ERROR | E_PARSE);
	require_once('../lib/nusoap/nusoap.php');
	
	$taldea=new Taldea();
	$taldea->idTaldea=$_GET["idTaldea"];
	
	//Create Client & set WSDL file path
	$client = new nusoap_client($wsdl,true);
	 
	// Check for any error(s)
	$err = $client->getError();
	if ($err){
		echo '<h2>Constructor error</h2><pre>' . $err . '</pre>';
		exit();
	
	}else{ 
		// specific service call     
		$result = $client->call('TaldearenDatuakLortu', array('arg0' =>$taldea ));
		$taldea=$result['return'];
		//echo $taldea['izenOsoa'];
		echo '<table class="table">
			  <thead class="thead-dark">
				<tr>
					<th></th>
					<th>Izena</th>
					<th>Soldata</th>
					<th>Posizioa</th>
				</tr>
			</thead>
			<tbody>';	
		
		$jokalariak = $taldea['jokalariasForIdTaldea'];
		foreach ($jokalariak as &$jokalaria) {
			echo '<tr>
					  <th scope="row"><a href="./jokalariak.php?idJokalaria='.$jokalaria['idJokalaria'].'"><div class="text-center"><img src="https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/'.$jokalaria['idJokalaria'].'.png" class="rounded" width="68" height="50"></div></a></th>
					  <td><a href="./jokalariak.php?idJokalaria='.$jokalaria['idJokalaria'].'">'.$jokalaria['izena'].' '.$jokalaria['abizena'].'</a></td>
					  <td>'.DiruaFormatuarekin($jokalaria['soldata']).'</td>
					  <td>'.$jokalaria['posizioa'].'</td>
					</tr>';
		}
		echo '</tbody></table>';
	}

?>