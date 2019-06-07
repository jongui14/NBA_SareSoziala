<?php
	include '../datuak/domeinua.php';
	include '../datuak/datuak.php';
    $wsdl = $user_wsdl;
	session_start();

if (! isset($_SESSION["erabiltzailea"])){
	header('Location: ../logeatu_gabea/kontura_sartu.php');
}

if($_SESSION["erabiltzailea"]->administratzailea==false){
	header('Location: ./index.php');
}


$erabiltzaile = new Erabiltzailea();
$erabiltzaile = $_SESSION["erabiltzailea"];

$komunitatea=$_SESSION["komunitatea"];


?>
<html>
<head>

<script>
function sariak_aldatu(){
	var saria1=document.getElementById("saria1").value;
	var saria2=document.getElementById("saria2").value;
	var saria3=document.getElementById("saria3").value;
	
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open("GET", "../php/sariak_aldatu.php?saria1="+saria1+"&saria2="+saria2+"&saria3="+saria3, true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var res=this.responseText;
			location.reload();
        }
	};
}
</script>

<link rel="stylesheet" href="../css/styles.css">
<link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.min.css"><!-- Latest compiled and minified CSS -->
<script src="../lib/jquery/jquery-3.3.1.min.js"></script><!-- jQuery library -->
<script src="../lib/bootstrap/js/bootstrap.min.js"></script><!-- Latest compiled JavaScript -->


</head>
<body>


<?php include "../datuak/menu_log.html"; ?>


<div class="signup-form">
    <form action="./erabiltzailea_sortu.php" method="post">
		<h2>Komunitatea</h2>
		<p class="hint-text">Sariak aldatu</p>
		<div class="form-group">
        	<font>Saria 1:</font><div class="col-xs-6"><input type="number" class="form-control"  name='saria1' id='saria1' value='<?php echo $komunitatea->saria1; ?>' required="required"><br>
        	<font>Saria 2:</font><input type="number" class="form-control"  name='saria2' id='saria2' value='<?php echo $komunitatea->saria2; ?>' required="required"><br>
        	<font>Saria 3:</font><input type="number" class="form-control"  name='saria3' id='saria3' value='<?php echo $komunitatea->saria3; ?>' required="required"><br>
        </div>
		<div class="form-group">
            <input type="button" onclick='sariak_aldatu()'  class="btn btn-success btn-lg btn-block"  value='Sariak aldatu'></input>
        </div>
    </form>
</div>


</body>
</html>