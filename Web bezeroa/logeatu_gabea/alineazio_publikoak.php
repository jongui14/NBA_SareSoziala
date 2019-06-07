<?php
	include '../datuak/domeinua.php';
	include '../datuak/datuak.php';
    $wsdl = $usernot_wsdl;
session_start();

if ( isset($_SESSION["erabiltzailea"])){
	header('Location: ../logeatua/index.php');
}
?>
<html>
<head>

<link rel="stylesheet" href="../css/styles.css">
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css"><!-- Latest compiled and minified CSS -->
<script src="../lib/jquery/jquery-3.3.1.min.js"></script><!-- jQuery library -->
<script src="../lib/bootstrap/js/bootstrap.min.js"></script><!-- Latest compiled JavaScript -->

</head>
<body>

<?php include "../datuak/menu_notlog.html"; ?>



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
		$result = $client->call('AlineazioPublikoak');
		print_r ($result['return']);
		$alineazioak=$result['return'];
		if (array_key_exists("erabiltzailea",$alineazioak)){
			$alineazioak=array($alineazioak);
		}

		
		echo '<h3>Alineazio Publikoak</h3>';
				
		
		foreach ($alineazioak as &$alineazioa) {
			echo '<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col"></th><th scope="col"></th><th scope="col"></th><th scope="col"></th><th scope="col"></th>
						</tr>
					</thead>
				<tbody>
					<tr>
						<td scope="row" align="center"><img src="https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/'.$alineazioa['jokalariaByIdJokalaria1'].'.png" class="rounded" width="68" height="50"></td>
						<td scope="row" align="center"><img src="https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/'.$alineazioa['jokalariaByIdJokalaria2'].'.png" class="rounded" width="68" height="50"></td>
						<td scope="row" align="center"><img src="https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/'.$alineazioa['jokalariaByIdJokalaria3'].'.png" class="rounded" width="68" height="50"></td>
						<td scope="row" align="center"><img src="https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/'.$alineazioa['jokalariaByIdJokalaria4'].'.png" class="rounded" width="68" height="50"></td>
						<td scope="row" align="center"><img src="https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/'.$alineazioa['jokalariaByIdJokalaria5'].'.png" class="rounded" width="68" height="50"></td>
					</tr>
				</tbody></table>';
		}
		
	}

?>



</body>
</html>