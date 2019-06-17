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
function kontura_sartu() {
	var nick = document.getElementById("erabiltzailea_nick").value;
	var pass = document.getElementById("pasahitza").value;
	
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open("GET", "../php/erabiltzaile_logeatu.php?nick="+nick+"&pass="+pass, true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var erabiltzailea=this.responseText;
			if(erabiltzailea.includes('login_eginda')){
				window.location.href = "../logeatua/index.php";
			}else{
				alert("GAIZKI!");
			}
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


	
<div class="login-form">
	<form>
		<div class="avatar"><i class="material-icons">&#xE7FF;</i></div>
    	<h4 class="modal-title">Zure kontuan sartu</h4>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Erabiltzailea" required="required" id='erabiltzailea_nick'>
        </div>
        <div class="form-group">
            <input type="password" class="form-control" placeholder="Pasahitza" required="required" id='pasahitza'>
        </div>
        <!--<div class="form-group small clearfix">
            <label class="checkbox-inline"><input type="checkbox"> Gogoratu</label>
            <a href="#" class="forgot-link">Pasahitza ahaztu ahal duzu?</a>
        </div> -->
        <input type="button" class="btn btn-primary btn-block btn-lg" value="Login" onclick='kontura_sartu()'>              
		<div class="text-center small">Ez duzu konturik? <a href="./erabiltzailea_sortu.php">Erregistratu</a></div>
	</form>
</div>


</body>

</html>