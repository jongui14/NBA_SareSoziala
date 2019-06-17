<?php
session_start();

if ( isset($_SESSION["erabiltzailea"])){
	header('Location: ../logeatua/index.php');
}
?>
<html>
<head>

<link rel="icon" type="image/png" href="../img/ic_launcher.png">

<link rel="stylesheet" href="../css/styles.css">
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css"><!-- Latest compiled and minified CSS -->
<script src="../lib/jquery/jquery-3.3.1.min.js"></script><!-- jQuery library -->
<script src="../lib/bootstrap/js/bootstrap.min.js"></script><!-- Latest compiled JavaScript -->

	
<script>
var nick_zuzena=false;
var izenosoa_zuzena=true;
var pasahitza1_zuzena=false;
var pasahitza2_zuzena=false;
var ongietorrimezua_zuzena=true;
var sariak_zuzena=true;

function komuitate_nick_konprobatu(nick){
	var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
				var res=this.responseText;
				if(res.includes('true')){
					document.getElementById("komunitateko_nick").style='border: 2px solid green;';nick_zuzena=true;
				}else{
					document.getElementById("komunitateko_nick").style='border: 2px solid red;';nick_zuzena=false;
				}
				parametro_guztiak_konprobatu();
            }
        };
        xmlhttp.open("GET", "../php/komunitatea_libre.php?nick="+nick, true);
        xmlhttp.send();
}
function komuitate_pass_konprobatu(pass){
	var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
				var res=this.responseText;
				if(res.includes('true')){
					document.getElementById("communitypass1").style='border: 2px solid green;';pasahitza1_zuzena=true;
				}else{
					document.getElementById("communitypass1").style='border: 2px solid red;';pasahitza1_zuzena=false;
				}
				parametro_guztiak_konprobatu();
            }
        };
        xmlhttp.open("GET", "../php/pasahitza_onargarria.php?pass="+pass, true);
        xmlhttp.send();
}
function parametro_guztiak_konprobatu(){
	if( nick_zuzena==true && izenosoa_zuzena==true && pasahitza1_zuzena==true && pasahitza2_zuzena==true && ongietorrimezua_zuzena==true && sariak_zuzena==true ){
		document.getElementById("botonFormulario").disabled = false;
	}else{
		document.getElementById("botonFormulario").disabled = true;
	}
}


</script>


<script type="text/javascript">
$(document).ready(function(){
	$("#komunitateko_nick").keyup(function() { 
		var nick = document.getElementById("komunitateko_nick").value;
		komuitate_nick_konprobatu(nick);
	});
	
	$("#communitypass1").keyup(function() { 
		var pass = document.getElementById("communitypass1").value;
		komuitate_pass_konprobatu(pass);
		
		var pass1 = document.getElementById("communitypass1").value;
		var pass2 = document.getElementById("communitypass2").value;
		if(pass1!=pass2){
			document.getElementById("communitypass2").style='border: 2px solid red;';pasahitza2_zuzena=false;
		
		}else{
			document.getElementById("communitypass2").style='border: 2px solid green;';pasahitza2_zuzena=true;
		}
		parametro_guztiak_konprobatu();
	});
	
	$("#communitypass2").keyup(function() { 
		var pass1 = document.getElementById("communitypass1").value;
		var pass2 = document.getElementById("communitypass2").value;
		if(pass1!=pass2){
			document.getElementById("communitypass2").style='border: 2px solid red;';pasahitza2_zuzena=false;
		}else{
			document.getElementById("communitypass2").style='border: 2px solid green;';pasahitza2_zuzena=true;
		}
		parametro_guztiak_konprobatu();
	});
	
});    
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



<div class="signup-form">
    <form action="./erabiltzailea_sortu.php" method="post">
		<h2>Erregistratu</h2>
		<p class="hint-text">Komunitate berria sortu.</p>
		<div class="form-group">
        	<input type="text" class="form-control" id="komunitateko_nick" name="komunitateko_nick" placeholder="Komunitateko nick-a" required="required">
        </div>
		<div class="form-group">
        	<input type="text" class="form-control" id="komunitateko_izena" name="komunitateko_izena" placeholder="Komunitateko izen-osoa" required="required">
        </div>
		<div class="form-group">
            <input type="password" class="form-control"  name='communitypass1' id='communitypass1' placeholder="Komunitateko pasahitza" required="required">
        </div>
		<div class="form-group">
            <input type="password" class="form-control" name='communitypass2' id='communitypass2' placeholder="Komunitateko errepikatu" required="required">
        </div>   
		<div class="form-group">
        	<input type="text" class="form-control" id="ongietorri_mezua" name="ongietorri_mezua" placeholder="Ongietorri mezua" required="required">
        </div>
		<div class="form-group">
        	<font>Saria 1:</font><div class="col-xs-6"><input type="number" class="form-control"  name='saria1' id='saria1' value='5000000' required="required"><br>
        	<font>Saria 2:</font><input type="number" class="form-control"  name='saria2' id='saria2' value='2500000' required="required"><br>
        	<font>Saria 3:</font><input type="number" class="form-control"  name='saria3' id='saria3' value='1500000' required="required"><br>
        </div>
		<div class="form-group">
            <button type="submit" id='botonFormulario' class="btn btn-success btn-lg btn-block"  name='komunitatea_sortu' id='komunitatea_sortu' disabled>Komunitatea sortu</button>
        </div>
    </form>
</div>



</body>

</html>