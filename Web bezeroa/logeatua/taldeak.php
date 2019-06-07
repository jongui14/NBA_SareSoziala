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

<script>
function taldearen_jokalaria(idTaldea){
	document.getElementById("divTaldearenJokalariak").innerHTML  = "Kargatzen...";
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open("GET", "../php/taldearen_informazioa.php?idTaldea="+idTaldea, true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var html=this.responseText;
			document.getElementById("divTaldearenJokalariak").innerHTML  = html;
        }
	};
}
</script>

</head>
<body>


<?php include "../datuak/menu_log.html"; ?>


<div style='height:90%; width:35%;display: inline-block;overflow-y: scroll;' >
<?php
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
		$result = $client->call('TaldeakLortu');
		//print_r ($result);
		echo '
		<table id="example" style="tablecontent03" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th></th>
					<th>Taldea</th>
				</tr>
			</thead>
			<tbody>';
		
		$taldeak = $result['return'];
		foreach ($taldeak as &$taldea) {
			echo '<tr>
					  <td><a href="./taldeak.php?idTaldea='.$taldea['idTaldea'].'"><div class="text-center"><img src="../img/taldea/'.$taldea['idTaldea'].'.png" class="rounded" width="50" height="50"></div></a></td>
					  <td><a href="./taldeak.php?idTaldea='.$taldea['idTaldea'].'">'.$taldea['izenOsoa'].'</a></td>
					</tr>';
		}
		echo '</tbody></table>';
	}
?>
</div><div style='width:65%; display: inline-block;height:90%; overflow-y: scroll;' id='divTaldearenJokalariak'>
</div>

<script>
<?php
	$idTaldea='1610612737';
	if ( isset($_GET["idTaldea"])){
		$idTaldea=$_GET["idTaldea"];
	}
	echo "taldearen_jokalaria($idTaldea);";
?>
</script>

</body>
</html>