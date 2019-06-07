<?php
	include '../datuak/datuak.php';
	include '../datuak/domeinua.php';
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
function jokalariaren_estadistikak(idJokalaria,izen_osoa,idTaldea,posizioa,soldata,dortsala,altuera,pisua,jaiotzeData,nazionalitatea){
	document.getElementById("divEstadistikaJokalaria").innerHTML  = "Kargatzen...";
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open("GET", "../php/estadistika_jokalaria.php?idJokalaria="+idJokalaria+"&izen_osoa="+izen_osoa+"&idTaldea="+idTaldea+"&posizioa="+posizioa+"&soldata="+soldata
					+"&dortsala="+dortsala+"&altuera="+altuera+"&pisua="+pisua+"&jaiotzeData="+jaiotzeData+"&nazionalitatea="+nazionalitatea, true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var html=this.responseText;
			document.getElementById("divEstadistikaJokalaria").innerHTML  = html;
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
		$result = $client->call('JokalariakLortu' );
		//print_r ($result);
		echo '
		<table id="example" style="tablecontent03" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th></th>
					<th>Jokalaria</th>'.
					//<th>Soldata</th>
					'<th>Taldea</th>'.
					//<th>Posizioa</th>
					//<th>Dorsala</th>
					//<th>Altuera</th>
					//<th>Pisua</th>
					//<th>Jaiotze-data</th>
					//<th>Nazionalitatea</th>
				'</tr>
			</thead>
			<tbody>';
		
		$jokalariak = $result['return'];
		$idJokalaria=0;$luzeera = sizeof($jokalariak);
		if ( isset($_GET["idJokalaria"])){
			$idJokalaria=$_GET["idJokalaria"];
		}
		foreach ($jokalariak as &$jokalaria) {
			$jok_inf_lag=$jokalaria['idJokalaria'].",'".$jokalaria['izena']." ".$jokalaria['abizena']."',".$jokalaria['taldeaByIdTaldea'].",'".$jokalaria['posizioa']."',".$jokalaria['soldata'].",".$jokalaria['dortsala'].",".$jokalaria['altuera'].",".$jokalaria['pisua'].",'".substr($jokalaria['jaiotzeData'],0,10)."','".$jokalaria['nazionalitatea']."'";
			echo '<tr>
					  <th scope="row"><div class="text-center"><img style="cursor:hand" onclick="jokalariaren_estadistikak('.$jok_inf_lag.')" src="https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/'.$jokalaria['idJokalaria'].'.png" class="rounded" width="68" height="50"></div></th>
					  <td><p class="text-primary" style="cursor:hand" onclick="jokalariaren_estadistikak('.$jok_inf_lag.')">'.$jokalaria['izena'].' '.$jokalaria['abizena'].'</p></td>'.
					  //<td>'.$jokalaria['soldata'].'</td>
					  '<td><a href="./taldeak.php?idTaldea='.$jokalaria['taldeaByIdTaldea'].'"><div class="text-center"><img src="../img/taldea/'.$jokalaria['taldeaByIdTaldea'].'.png" class="rounded" width="50" height="50"></div></a></td>'.
					  //<td>'.$jokalaria['posizioa'].'</td>
					  //<td>'.$jokalaria['dortsala'].'</td>
					  //<td>'.$jokalaria['altuera'].'</td>
					  //<td>'.$jokalaria['pisua'].'</td>
					  //<td>'.substr($jokalaria['jaiotzeData'],0,10).'</td>
					  //<td>'.$jokalaria['nazionalitatea'].'</td>
					'</tr>';
					
			if($idJokalaria==0 && $luzeera==sizeof($jokalariak) || $idJokalaria==$jokalaria['idJokalaria']){
				$jok_inf=$jok_inf_lag;
				$idJokalaria=$jokalaria['idJokalaria'];$luzeera--;
			}
		}
		echo '</tbody></table>';
		//print_r ($result['return']);
		
	}

?>
</div><div id='divEstadistikaJokalaria' style='width:65%; display: inline-block;height:90%; overflow-y: scroll;' >

</div>


<script>
<!--jokalariaren_estadistikak(idJokalaria,izen_osoa,idTaldea,posizioa,soldata,dortsala,altuera,pisua,jaiotzeData,nazionalitatea);-->
jokalariaren_estadistikak(<?php echo $jok_inf;?>);
</script>

</body>
</html>