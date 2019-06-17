<?php
	include '../datuak/domeinua.php';
	include '../datuak/datuak.php';
    $wsdl = $usernot_wsdl;
	
session_start();

if ( isset($_SESSION["erabiltzailea"])){
	header('Location: ../logeatua/index.php');
}

error_reporting(E_ERROR | E_PARSE);
require_once('../lib/nusoap/nusoap.php');
?>
<html>
<head>

<link rel="icon" type="image/png" href="../img/ic_launcher.png">

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

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
   <div class="container">
      <a class="navbar-brand" href="./index.php"><b>NBAko sare-soziala</b></a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
         <ul class="navbar-nav mr-auto">
            <li class="nav-item">
               <a class="nav-link" href="./partiduen_emaitzak.php">Partiduen emaitzak</a>
            </li>
            <li class="nav-item">
               <a class="nav-link" href="./jokalariak.php">Jokalariak</a>
            </li>
			<li class="nav-item">
               <a class="nav-link" href="./taldeak.php">Taldeak</a>
            </li>
			<li class="nav-item">
               <a class="nav-link" href="./alineazio_publikoak.php">Alineazio publikoak</a>
            </li>
         </ul>
		 <a class="btn btn-outline-success d-lg-inline-block mb-3 mb-md-0 ml-md-3" href="./komunitatea_sortu.php">Komunitate berria</a>
         <a class="btn btn-outline-success d-lg-inline-block mb-3 mb-md-0 ml-md-3" href="./komunitatea_aukeratu.php">Erabiltzaile berria</a>
         <a class="btn btn-outline-info d-lg-inline-block mb-3 mb-md-0 ml-md-3" href="./kontura_sartu.php">Kontura sartu</a>
      </div>
   </div>
</nav>


<div style='height:90%; width:35%;display: inline-block;overflow-y: scroll;' >
<?php
		 
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