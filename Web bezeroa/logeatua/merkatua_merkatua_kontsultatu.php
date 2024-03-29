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

<script>
function oferta_egin(idMerkatukoJokalaria){
	var ofertarenPrezioa=document.getElementById("oferta"+idMerkatukoJokalaria).value;
	
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open("GET", "../php/merkatua_jokalaria_oferta.php?idMerkatukoJokalaria="+idMerkatukoJokalaria+"&ofertarenPrezioa="+ofertarenPrezioa, true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var res=this.responseText;
			if(res.toLowerCase().includes("true")){
				document.getElementById("ok_msg"+idMerkatukoJokalaria).innerHTML="Eskaintza ondo egin da!";
				document.getElementById("error"+idMerkatukoJokalaria).innerHTML="";
			}else{
				document.getElementById("error"+idMerkatukoJokalaria).innerHTML="Errorea eskaintza egitean!";
				document.getElementById("ok_msg"+idMerkatukoJokalaria).innerHTML="";
			}
        }
	};
}
</script>

</head>
<body>

<?php include "../datuak/menu_log.html"; ?>

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
		$result = $client->call('MerkatuaKontsultatu', 
			array(	'arg0' =>$_SESSION['komunitatea'])
		);
		//print_r ($result['return']);
		
		echo '
		<table class="table">
		  <thead class="thead-dark">
			<tr>
			  <th scope="col"></th>
			  <th scope="col">Jokalaria</th>
			  <th scope="col">Taldea</th>
			  <th scope="col"><center>Erabiltzailea</center></th>
			  <th scope="col">Soldata</th>
			  <th scope="col">Posizioa</th>
			  <th scope="col">Has. Prezioa</th>
			  <th scope="col">Eskaintza</th>
			  <th scope="col"></th>
			</tr>
		  </thead>
		  <tbody>';
		
		$merkatuko_jokalariak = $result['return'];
		foreach ($merkatuko_jokalariak as &$merkatuko_jokalaria) {
			$jokalaria=$merkatuko_jokalaria['jokalaria'];
			echo '<tr>
					  <th scope="row"><div class="text-center"><img src="https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/'.$jokalaria['idJokalaria'].'.png" class="rounded" width="68" height="50"></div></th>
					  <td>'.$jokalaria['izena'].' '.$jokalaria['abizena'].'</td>
					  <td><div class="text-center"><img src="../img/taldea/'.$jokalaria['taldeaByIdTaldea'].'.png" class="rounded" width="50" height="50"></div></td>
					  <td><center><i>'.erabiltzailearen_nick($merkatuko_jokalaria['erabiltzaileaByIdErabiltzaileaJabea']).'</i></center></td>
					  <td>'.DiruaFormatuarekin($jokalaria['soldata']).'</td>
					  <td>'.$jokalaria['posizioa'].'</td>
					  <td>'.DiruaFormatuarekin($merkatuko_jokalaria['hasierakoPrezioa']).'</td>
					  <td><input type="text" class="form-control" id="oferta'.$merkatuko_jokalaria['idMerkatukoJokalaria'].'" >
						<center><span id="ok_msg'.$merkatuko_jokalaria['idMerkatukoJokalaria'].'" style="color:green"></span> <span id="error'.$merkatuko_jokalaria['idMerkatukoJokalaria'].'" style="color:red"></span></center></td>
					  <td><button type="button" class="btn btn-success btn-lg btn-block" onclick="oferta_egin(\''.$merkatuko_jokalaria['idMerkatukoJokalaria'].'\')">Oferta egin</button></td>
					</tr>';
		}
		
		//print_r ($result['return']);
		
	}

?>



</body>
</html>