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
var id_jokalaria1=0;
var id_jokalaria2=0;
var id_jokalaria3=0;
var id_jokalaria4=0;
var id_jokalaria5=0;
		
		
var click=false;
var click_id=0;
var elemento_click;
function hover(element,idJugador) {
	element.setAttribute('src', '../img/change2.png');
	if(click==true){
		click=false;
		document.getElementById("pos1").setAttribute('src', '../img/change0.png');
		document.getElementById("pos2").setAttribute('src', '../img/change0.png');
		document.getElementById("pos3").setAttribute('src', '../img/change0.png');
		document.getElementById("pos4").setAttribute('src', '../img/change0.png');
		document.getElementById("pos5").setAttribute('src', '../img/change0.png');
		elemento_click.setAttribute('src', '../img/change1.png');
	}
	
  
}
function hover2(element) {
	if(element.getAttribute('src').includes('change3')){
		element.setAttribute('src', '../img/change2.png');
	}
}
function unhover2(element) {
	if(element.getAttribute('src').includes('change2')){
		element.setAttribute('src', '../img/change3.png');
	}
}

function unhover(element) {
	if(click==false){
		element.setAttribute('src', '../img/change1.png');
	}
}
function elegirJugador(idJugador,posicion,element){
	click=true;
	click_id=idJugador;
	elemento_click=element;
	document.getElementById("pos1").setAttribute('src', '../img/change0.png');
	document.getElementById("pos2").setAttribute('src', '../img/change0.png');
	document.getElementById("pos3").setAttribute('src', '../img/change0.png');
	document.getElementById("pos4").setAttribute('src', '../img/change0.png');
	document.getElementById("pos5").setAttribute('src', '../img/change0.png');
	
	element.setAttribute('src', '../img/change2.png');
	if(posicion.includes('G')){
		document.getElementById("pos1").setAttribute('src', '../img/change3.png');
		document.getElementById("pos2").setAttribute('src', '../img/change3.png');
	}
	if(posicion.includes('F')){
		document.getElementById("pos3").setAttribute('src', '../img/change3.png');
		document.getElementById("pos4").setAttribute('src', '../img/change3.png');
	}
	if(posicion.includes('C')){
		document.getElementById("pos5").setAttribute('src', '../img/change3.png');
	}
}
function cambioJugador(imgPos){
	if(id_jokalaria1!=click_id && id_jokalaria2!=click_id && id_jokalaria3!=click_id && id_jokalaria4!=click_id && id_jokalaria5!=click_id ){
		
		if(imgPos.includes('1')){
			id_jokalaria1=click_id;
		}
		if(imgPos.includes('2')){
			id_jokalaria2=click_id;
		}
		if(imgPos.includes('3')){
			id_jokalaria3=click_id;
		}
		if(imgPos.includes('4')){
			id_jokalaria4=click_id;
		}
		if(imgPos.includes('5')){
			id_jokalaria5=click_id;
		}
		document.getElementById(imgPos).setAttribute('src', 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/'+click_id+'.png');
		
		document.getElementById("pos1").setAttribute('src', '../img/change0.png');
		document.getElementById("pos2").setAttribute('src', '../img/change0.png');
		document.getElementById("pos3").setAttribute('src', '../img/change0.png');
		document.getElementById("pos4").setAttribute('src', '../img/change0.png');
		document.getElementById("pos5").setAttribute('src', '../img/change0.png');
		elemento_click.setAttribute('src', '../img/change1.png');
	}
}
function alineazioa_gorde(){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open("GET", "../php/jokalariak_alineazioa_gorde.php?id_jokalaria1="+id_jokalaria1+"&id_jokalaria2="+id_jokalaria2+"&id_jokalaria3="+id_jokalaria3+"&id_jokalaria4="+id_jokalaria4+"&id_jokalaria5="+id_jokalaria5+"&mota="+false, true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var res=this.responseText;
			alert(res);
			if(res.includes('true')){
				alert("Gordeta!");
			}else{
				alert("Alineazioa ez da zuzena!");
			}
        }
	};
}
function alineazioa_publikatu(){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open("GET", "../php/jokalariak_alineazioa_gorde.php?id_jokalaria1="+id_jokalaria1+"&id_jokalaria2="+id_jokalaria2+"&id_jokalaria3="+id_jokalaria3+"&id_jokalaria4="+id_jokalaria4+"&id_jokalaria5="+id_jokalaria5+"&mota="+true, true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var res=this.responseText;
			alert(res);
        }
	};
}
</script>

</head>
<body>


<?php include "../datuak/menu_log.html"; ?>



<div style='width:35%;display: inline-block;'>
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
		$result = $client->call('AlineazioaLortu', 
			array(	'arg0' =>$_SESSION["erabiltzailea"])
		);
		//print_r ($result['return']);echo '<br>';
		$erantzuna=$result['return'];//print_r ($erantzuna);
		$id_jokalaria1=$erantzuna['jokalariaByIdJokalaria1'];
		$id_jokalaria2=$erantzuna['jokalariaByIdJokalaria2'];
		$id_jokalaria3=$erantzuna['jokalariaByIdJokalaria3'];
		$id_jokalaria4=$erantzuna['jokalariaByIdJokalaria4'];
		$id_jokalaria5=$erantzuna['jokalariaByIdJokalaria5'];
		//echo $id_jokalaria1.' '.$id_jokalaria2.' '.$id_jokalaria3.' '.$id_jokalaria4.' '.$id_jokalaria5;
		
		echo 
		"<script>
		id_jokalaria1=$id_jokalaria1;
		id_jokalaria2=$id_jokalaria2;
		id_jokalaria3=$id_jokalaria3;
		id_jokalaria4=$id_jokalaria4;
		id_jokalaria5=$id_jokalaria5;
		</script>";
		
		echo '
		<table class="table">
		  <thead class="thead-dark">
			<tr>
			  <th scope="col"><center>Posizioa</center></th>
			  <th scope="col"></th>
			  <th scope="col"></th>
			</tr>
		  </thead>
		  <tbody>
			<tr>
				<td align="center">G</td>
				<td scope="row" align="center"><img id="imgPos1" src="https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/'.$id_jokalaria1.'.png" class="rounded" width="68" height="50"></td>
				<td align="center"><img id="pos1" onclick="cambioJugador(\'imgPos1\')" onmouseover="hover2(this);" onmouseout="unhover2(this);" src="../img/change0.png" width="40" height="40"></td>
			</tr>
			<tr>
				<td align="center">G</td>
				<td align="center" scope="row"><img id="imgPos2" src="https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/'.$id_jokalaria2.'.png" class="rounded" width="68" height="50"></td>
				<td align="center"><img id="pos2" onclick="cambioJugador(\'imgPos2\')" onmouseover="hover2(this);" onmouseout="unhover2(this);" src="../img/change0.png" width="40" height="40"></td>
			</tr>
			<tr>
				<td align="center">F</td>
				<td align="center" scope="row"><img id="imgPos3" src="https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/'.$id_jokalaria3.'.png" class="rounded" width="68" height="50"></td>
				<td align="center"><img id="pos3" onclick="cambioJugador(\'imgPos3\')" onmouseover="hover2(this);" onmouseout="unhover2(this);" src="../img/change0.png" width="40" height="40"></td>
			</tr>
			<tr>
				<td align="center">F</td>
				<td align="center" scope="row"><img id="imgPos4" src="https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/'.$id_jokalaria4.'.png" class="rounded" width="68" height="50"></td>
				<td align="center"><img id="pos4" onclick="cambioJugador(\'imgPos4\')" onmouseover="hover2(this);" onmouseout="unhover2(this);" src="../img/change0.png" width="40" height="40"></td>
			</tr>
			<tr>
				<td align="center">C</td>
				<td align="center" scope="row"><img id="imgPos5" src="https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/'.$id_jokalaria5.'.png" class="rounded" width="68" height="50"></td>
				<td align="center"><img id="pos5" onclick="cambioJugador(\'imgPos5\')" onmouseover="hover2(this);" onmouseout="unhover2(this);" src="../img/change0.png" width="40" height="40"></td>
			</tr>
			</tbody></table>';

		echo '
		<div class="form-group" style="width:45%;display: inline-block;">
            <button type="button" class="btn btn-success btn-lg btn-block" onclick="alineazioa_gorde()" >Gorde</button>
        </div><div style="width:10%;display: inline-block;"></div><div class="form-group" style="width:45%;display: inline-block;">
            <button type="button" class="btn btn-success btn-lg btn-block" onclick="alineazioa_publikatu()" >Publikatu</button>
        </div>';
	}

?>
</div><div style='height:90%; width:65%;display: inline-block;overflow-y: scroll;' >
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
			  <th scope="col">Posizioa</th>
			  <th scope="col"></th>
			</tr>
		  </thead>
		  <tbody>';
		
		$jokalariak = $result['return'];
		foreach ($jokalariak as &$jokalaria) {
			echo '<tr>
					  <th scope="row"><div class="text-center"><img src="https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/'.$jokalaria['idJokalaria'].'.png" class="rounded" width="68" height="50"></div></th>
					  <td>'.$jokalaria['izena'].' '.$jokalaria['abizena'].'</td>
					  <td>'.$jokalaria['posizioa'].'</td>
					  <td align="center"><img src="../img/change1.png" width="40" height="40" onmouseover="hover(this,'.$jokalaria['idJokalaria'].');" onmouseout="unhover(this);" onclick="elegirJugador('.$jokalaria['idJokalaria'].',\''.$jokalaria['posizioa'].'\',this)"></td>
					</tr>';
		}
		echo '</tbody></table>';
		
		//print_r ($result['return']);
		
	}

?>
</div>


</body>
</html>