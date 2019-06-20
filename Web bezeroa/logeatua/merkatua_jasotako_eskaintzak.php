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
function erantzun(idMerkatukoJokalaria,erantzuna){
	
	var idEskaintza=document.getElementsByName("merkatukoJokalaria"+idMerkatukoJokalaria)[0].value;
	
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open("GET", "../php/eskaintza_erantzun.php?idMerkatukoJokalaria="+idMerkatukoJokalaria+"&idEskaintza="+idEskaintza+"&erantzuna="+erantzuna, true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var res=this.responseText;
			location.reload();
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
		$result = $client->call('JasotakoEskaintzak', 
			array(	'arg0' =>$_SESSION["erabiltzailea"])
		);
		//print_r ($result['return']);
		$eskaintzak=$result['return'];
		if (array_key_exists("idEskaintza",$eskaintzak)){
			$eskaintzak=array($eskaintzak);
		}

		$merkatukoJokalariakID=array(0);

		echo '<br><h3>Tramitatu gabekoak</h3>';
		echo '
		<table class="table">
		  <thead class="thead-dark">
			<tr>
			  <th scope="col"></th>
			  <th scope="col"><center>Jokalaria</center></th>
			  <th scope="col"><center>Taldea</center></th>
			  <th scope="col"><center>Eskaturiko balioa</center></th>
			  <th scope="col"><center>Eskaintzak</center></th>
			</tr>
		  </thead>
		  <tbody>';
		
		foreach ($eskaintzak as &$eskaintza) {
			$merkatukoJokalaria=$eskaintza['merkatukoJokalaria'];
			if($merkatukoJokalaria['tramitatua']=='false'){
				if(! in_array($merkatukoJokalaria['idMerkatukoJokalaria'],$merkatukoJokalariakID) ){
					array_push($merkatukoJokalariakID,$merkatukoJokalaria['idMerkatukoJokalaria']);
					$onartua="";
					$jokalaria=$merkatukoJokalaria['jokalaria'];
					if($merkatukoJokalaria['onartua']=='true'){
						$onartua=DiruaFormatuarekin($merkatukoJokalaria['eskaintzaIrabazlea']);
					}
					echo '<tr>
					  <td scope="row"><center><div class="text-center"><img src="https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/'.$jokalaria['idJokalaria'].'.png" class="rounded" width="68" height="50"></div></center></td>
					  <td><center>'.$jokalaria['izena'].' '.$jokalaria['abizena'].'</center></td>
					  <td><center><div class="text-center"><img src="../img/taldea/'.$jokalaria['taldeaByIdTaldea'].'.png" class="rounded" width="50" height="50"></div></center></td>
					  <td><center>'.DiruaFormatuarekin($merkatukoJokalaria['hasierakoPrezioa']).'</center></td><td>';
					
					foreach ($eskaintzak as &$eskaintza2) {
						$merkatukoJokalaria2=$eskaintza2['merkatukoJokalaria'];
						if($merkatukoJokalaria2['idMerkatukoJokalaria']==$merkatukoJokalaria['idMerkatukoJokalaria']){
							$aukeratua="";
							if($merkatukoJokalaria['onartua']=='true' && $eskaintza2['eskaintza']==$merkatukoJokalaria['eskaintzaIrabazlea'] && $merkatukoJokalaria['erabiltzaileaByIdErabiltzaileaIrabazlea']==$eskaintza2['erabiltzailea']){
								$aukeratua="checked";
							}
							echo '<input type="radio" name="merkatukoJokalaria'.$merkatukoJokalaria['idMerkatukoJokalaria'].'" value=" '.$eskaintza2['idEskaintza'].'" '.$aukeratua.'>'.DiruaFormatuarekin($eskaintza2['eskaintza']).' <i> '.erabiltzailearen_nick($eskaintza2['erabiltzailea']).'</i><br>';
						}
					}
					echo '
					<table>
					<tr>
					<td>'.$onartua.'</td>
					<td><button type="button" class="btn btn-success btn-lg btn-block" onclick="erantzun('.$merkatukoJokalaria['idMerkatukoJokalaria'].',true)">Onartu</button></td>
					<td><button type="button" class="btn btn-success btn-lg btn-block" onclick="erantzun('.$merkatukoJokalaria['idMerkatukoJokalaria'].',false)"> Ezeztatu</button> </td>
					</tr>
					</table>
					';
					
					echo '</td></tr>';
				}
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
			  <th scope="col"><center>Eskaturiko balioa</center></th>
			  <th scope="col"><center>Eskaintzak</center></th>
			</tr>
		  </thead>
		  <tbody>';
		
		foreach ($eskaintzak as &$eskaintza) {
			$merkatukoJokalaria=$eskaintza['merkatukoJokalaria'];
			if($merkatukoJokalaria['tramitatua']=='true'){
				if(! in_array($merkatukoJokalaria['idMerkatukoJokalaria'],$merkatukoJokalariakID) ){
					array_push($merkatukoJokalariakID,$merkatukoJokalaria['idMerkatukoJokalaria']);
					
					$jokalaria=$merkatukoJokalaria['jokalaria'];
					echo '<tr>
					  <td scope="row"><center><div class="text-center"><img src="https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/'.$jokalaria['idJokalaria'].'.png" class="rounded" width="68" height="50"></div></center></td>
					  <td><center>'.$jokalaria['izena'].' '.$jokalaria['abizena'].'</center></td>
					  <td><center><div class="text-center"><img src="../img/taldea/'.$jokalaria['taldeaByIdTaldea'].'.png" class="rounded" width="50" height="50"></div></center></td>
					  <td><center><i>'.erabiltzailearen_nick($eskaintza['erabiltzailea']).'</i></center></td>
					  <td><center>'.DiruaFormatuarekin($eskaintza['eskaintza']).'</center></td><td>';
					
					foreach ($eskaintzak as &$eskaintza2) {
						$merkatukoJokalaria2=$eskaintza2['merkatukoJokalaria'];
						if($merkatukoJokalaria2['idMerkatukoJokalaria']==$merkatukoJokalaria['idMerkatukoJokalaria']){
							$aukeratua="";
							if($merkatukoJokalaria['onartua']=='true' && $eskaintza2['eskaintza']==$merkatukoJokalaria['eskaintzaIrabazlea'] && $merkatukoJokalaria['erabiltzaileaByIdErabiltzaileaIrabazlea']==$eskaintza2['erabiltzailea']){
								$aukeratua="checked";
							}
							echo '<input type="radio" name="merkatukoJokalaria'.$merkatukoJokalaria['idMerkatukoJokalaria'].'" value=" '.$eskaintza2['idEskaintza'].'" '.$aukeratua.' disabled>'.DiruaFormatuarekin($eskaintza2['eskaintza']).'<br>';
						}
					}
					echo '</td></tr>';
				}
				
			}
		}
		echo '</tbody></table>';
		
	}

?>

</body>
</html>