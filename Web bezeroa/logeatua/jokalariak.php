<?php
	include '../datuak/domeinua.php';
	include '../datuak/datuak.php';
    $wsdl = $usernot_wsdl;
	session_start();

if (! isset($_SESSION["erabiltzailea"])){
	header('Location: ../logeatu_gabea/kontura_sartu.php');
}

$erabiltzaile = new Erabiltzailea();
$erabiltzaile = $_SESSION["erabiltzailea"];



?>
<html>
<head>

<link rel="stylesheet" href="../css/styles.css">
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css"><!-- Latest compiled and minified CSS -->
<script src="../lib/jquery/jquery-3.3.1.min.js"></script><!-- jQuery library -->
<script src="../lib/bootstrap/js/bootstrap.min.js"></script><!-- Latest compiled JavaScript -->


<link rel='stylesheet' type='text/css' href='../lib/datatable/jquery.dataTables.min.css'>
<script type="text/javascript" charset="utf8" src="../lib/datatable/jquery.dataTables.min.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	$('#example').DataTable({
		"paging": false,
		"bInfo" : false
	});
});
</script>

</head>
<body>


<?php include "../datuak/menu_log.html"; ?>


<?php
    $wsdl = 'http://localhost:9999/usernotlogged?wsdl';
	
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
		$result = $client->call('JokalariakLortu' );
		//print_r ($result);
		echo '
		<table id="example" style="tablecontent03" cellspacing="0" width="100%">
			<thead>
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
		
		$jokalariak = $result['return'];
		foreach ($jokalariak as &$jokalaria) {
			echo '<tr>
					  <th scope="row"><div class="text-center"><img src="https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/'.$jokalaria['idJokalaria'].'.png" class="rounded" width="68" height="50"></div></th>
					  <td>'.$jokalaria['izena'].' '.$jokalaria['abizena'].'</td>
					  <td>'.$jokalaria['soldata'].'</td>
					  <td><div class="text-center"><img src="../img/taldea/'.$jokalaria['taldeaByIdTaldea'].'.png" class="rounded" width="50" height="50"></div></td>
					  <td>'.$jokalaria['posizioa'].'</td>
					  <td>'.$jokalaria['dortsala'].'</td>
					  <td>'.$jokalaria['altuera'].'</td>
					  <td>'.$jokalaria['pisua'].'</td>
					  <td>'.substr($jokalaria['jaiotzeData'],0,10).'</td>
					  <td>'.$jokalaria['nazionalitatea'].'</td>
					</tr>';
		}
		
		//print_r ($result['return']);
		
	}

?>


</body>
</html>