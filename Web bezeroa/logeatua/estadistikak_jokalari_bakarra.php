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
function jokalariaren_estadistikak(idJokalaria,izen_osoa,idTaldea,postua,soldata){
	document.getElementById("divEstadistika").innerHTML  = "Kargatzen...";
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open("GET", "../php/estadistika_jokalaria.php?idJokalaria="+idJokalaria+"&izen_osoa="+izen_osoa+"&idTaldea="+idTaldea+"&postua="+postua+"&soldata="+soldata, true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var html=this.responseText;
			document.getElementById("divEstadistika").innerHTML  = html;
        }
	};
}
</script>


</head>
<body>


<?php include "../datuak/menu_log.html"; ?>


<div style='height:90%; width:65%;display: inline-block;overflow-y: scroll;' id='divEstadistika'>

</div><div style='height:90%; width:35%;display: inline-block;overflow-y: scroll;'>
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
		$result = $client->call('ErabiltzailearenJokalariakLortu', 
			array(	'arg0' =>$_SESSION["erabiltzailea"])
		);
		//print_r ($result['return']);
		
		echo '
		<table class="table">
		  <thead class="thead-dark">
			<tr>
			  <th scope="col"></th>
			  <th scope="col">Jokalaria</th>
			  <th scope="col">Taldea</th>
			  <th scope="col">Posizioa</th>
			</tr>
		  </thead>
		  <tbody>';
		
		$jokalariak = $result['return'];
		$lehenengo_jokalaria='';
		$luzeera = sizeof($jardunaldiak);
		foreach ($jokalariak as &$jokalaria) {
			echo '<tr style="cursor:hand" onclick="jokalariaren_estadistikak('.$jokalaria['idJokalaria'].','.'\''.$jokalaria['izena'].' '.$jokalaria['abizena'].'\','.$jokalaria['taldeaByIdTaldea'].',\''.$jokalaria['posizioa'].'\','.$jokalaria['soldata'].')">
					  <th scope="row"><div class="text-center"><img src="https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/'.$jokalaria['idJokalaria'].'.png" class="rounded" width="68" height="50"></div></th>
					  <td>'.$jokalaria['izena'].' '.$jokalaria['abizena'].'</td>
					  <td><div class="text-center"><img src="../img/taldea/'.$jokalaria['taldeaByIdTaldea'].'.png" class="rounded" width="50" height="50"></div></td>
					  <td>'.$jokalaria['posizioa'].'</td>
					</tr>';
			if($luzeera==sizeof($jardunaldiak)){
				$lehenengo_jokalaria=$jokalaria['idJokalaria'].','.'"'.$jokalaria['izena'].' '.$jokalaria['abizena'].'",'.$jokalaria['taldeaByIdTaldea'].',"'.$jokalaria['posizioa'].'",'.$jokalaria['soldata'];
				$luzeera--;
			}
		}
				
	}

?>
</div>

<script type="text/javascript">
   jokalariaren_estadistikak(<?php echo $lehenengo_jokalaria; ?>);
</script>

</body>
</html>