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
 <style type ="text/css" >
   .footer{ 
       position: fixed;     
       text-align: center;    
       bottom: 0px; 
       width: 100%;
   }  
</style>


<link rel="stylesheet" href="../css/styles.css">
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css"><!-- Latest compiled and minified CSS -->
<script src="../lib/jquery/jquery-3.3.1.min.js"></script><!-- jQuery library -->
<script src="../lib/bootstrap/js/bootstrap.min.js"></script><!-- Latest compiled JavaScript -->


<script>
function mezua_idatzi(){
	var mezua=document.getElementById("mezu_edukia").value;
	
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open("GET", "../php/mezua_bidali.php?mezua="+mezua, true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var res=this.responseText;
			location.reload();
        }
	};
}
function mezua_ezabatu(idMezua){
	
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open("GET", "../php/mezua_ezabatu.php?idMezua="+idMezua, true);
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
		$result = $client->call('KomunitateaLortu', 
			array(	'arg0' =>$_SESSION["komunitatea"])
		);
		$res=$result['return'];
		//print_r ($res);
		
		echo '
		<table class="table">
		  <thead class="thead-dark">
			<tr>
			  <th scope="col"></th>
			  <th scope="col">'.$res['nick'].'</th>
			  <th scope="col"></th>
			  <th scope="col">'.$res['ongietorriMezua'].'</th>
			</tr>
		  </thead>
		</table><hr>';




		$result = $client->call('MezuakLortu', 
			array(	'arg0' =>$_SESSION["komunitatea"])
		);
		$res=$result['return'];
		
		echo '
		<table class="table">
		  <thead class="thead-dark">
			<tr>
			  <th scope="col"><center>nick</center></th>
			  <th scope="col"><center>Mezua</center></th>
			  <th scope="col"><center>Data</center></th>
			  <th scope="col"></th>
			</tr>
		  </thead>
		';
		
	
		
		$mezuak=$result['return'];
		if (array_key_exists("idMezua",$mezuak)){
			$mezuak=array($mezuak);
		}
		foreach ($mezuak as &$mezua) {
			$nick=erabiltzailearen_nick($mezua['erabiltzailea']);
			echo '<tr>
					
					<td><center>'.$nick.'</center></td>
					<td><center>'.$mezua['mezua'].'</center></td>
					<td><center>'.$mezua['eguna'].'</center></td>';
					
			if($mezua[ezabagarria]==true && $_SESSION["erabiltzailea"]->administratzailea==true){
				echo '<td><center><img src="../img/tick_gorria.png" class="rounded" width="25" height="25" onclick="mezua_ezabatu('.$mezua['idMezua'].')"></center></td>';
			}else{
				echo '<td></td>';
			}
			echo  '</tr>';
			
		}
		echo '</table>';
		
			
		echo '<div class="footer">
		<input type="text" class="form-control" id="mezu_edukia">
		<button type="button" class="btn btn-success btn-lg btn-block" onclick="mezua_idatzi()">Mezua bidali</button>
		</div>';
					
		
	}

?>



</body>
</html>