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

<script>
var modua=1;//Erabiltailea sortu existitzen den komunitate baten
var komunitateko_nick="<?php echo $_POST['komunitateko_nick'];?>";
var communitypass="<?php echo $_POST['communitypass1'];?>";

<?php
if( !isset($_POST['komunitateko_nick']) && !isset($_POST['communitypass1']) ){
	header('Location: ./komunitatea_aukeratu.php');
}
if(isset($_POST['komunitateko_izena'])){

	echo "modua=0;";
	echo "var komunitateko_izena='".$_POST['komunitateko_izena']."';";
	echo "var ongietorri_mezua='".$_POST['ongietorri_mezua']."';";
	echo "var saria1='".$_POST['saria1']."';";
	echo "var saria2='".$_POST['saria2']."';";
	echo "var saria3='".$_POST['saria3']."';";
}
?>

function erabiltzailea_sortu_admin(){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open("GET", "../php/komunitatea_erregistratu.php?nick="+komunitateko_nick+"&izen_osoa="+komunitateko_izena+"&pasahitza="+communitypass+"&ongietorri_mezua="+ongietorri_mezua+"&saria1="+saria1+"&saria2="+saria2+"&saria3="+saria3, true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			//var komunitatea=this.responseText;alert(komunitatea);
			var nick=document.getElementById("erabiltzaile_nick").value;
			var pasahitza=document.getElementById("pasahitza1").value;
			var email=document.getElementById("erabiltzaile_email").value;
			var izen_osoa=document.getElementById("erabiltzailearen_izena").value;
	
			var xmlhttp2 = new XMLHttpRequest();
			//xmlhttp2.open("GET", "../php/erabiltzailea_erregistratu.php?komunitatea="+komunitatea+"&nick="+nick+"&pasahitza="+pasahitza+"&email="+email+"&izen_osoa="+izen_osoa+"&admin="+true, true);
			xmlhttp2.open("GET", "../php/erabiltzailea_erregistratu.php?nick="+nick+"&pasahitza="+pasahitza+"&email="+email+"&izen_osoa="+izen_osoa+"&admin="+true, true);
			xmlhttp2.send();
			xmlhttp2.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					window.location = "./kontura_sartu.php";
				}
			};
        }
	};
}

function erabiltzailea_sortu(){
	var nick=document.getElementById("erabiltzaile_nick").value;
	var pasahitza=document.getElementById("pasahitza1").value;
	var email=document.getElementById("erabiltzaile_email").value;
	var izen_osoa=document.getElementById("erabiltzailearen_izena").value;
	
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open("GET", "../php/komunitatea_aukeratu.php?nick="+komunitateko_nick+"&pass="+communitypass, true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var komunitatea=this.responseText;
			
			var xmlhttp2 = new XMLHttpRequest();
			xmlhttp2.open("GET", "../php/erabiltzailea_erregistratu.php?komunitatea="+komunitatea+"&nick="+nick+"&pasahitza="+pasahitza+"&email="+email+"&izen_osoa="+izen_osoa+"&admin="+false, true);
			xmlhttp2.send();
			xmlhttp2.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var res=this.responseText;
					if(res.includes('true')){
						window.location = "./kontura_sartu.php";
					}else{
						//document.getElementById("botonFormulario").disabled = true;
					}
				}
			};
        }
	};
}
function erabiltzailea_sortu_generala(){
	//alert("erabiltzailea_sortu_generala");
	//alert(modua);
	if(modua==0){
		erabiltzailea_sortu_admin();
	}else{
		erabiltzailea_sortu();
	}
}
</script>

<script>
var nick_zuzena=false;
var izenosoa_zuzena=true;
var pasahitza1_zuzena=false;
var pasahitza2_zuzena=false;
var email_zuzena=true;

function erabiltzaile_nick_konprobatu(nick){
	var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
				var res=this.responseText;
				if(res.includes('true')){
					document.getElementById("erabiltzaile_nick").style='border: 2px solid green;';nick_zuzena=true;
				}else{
					document.getElementById("erabiltzaile_nick").style='border: 2px solid red;';nick_zuzena=false;
				}
				parametro_guztiak_konprobatu();
            }
        };
        xmlhttp.open("GET", "../php/erabiltzaile_nick_libre.php?nick="+nick, true);
        xmlhttp.send();
}
function erabiltzaile_pass_konprobatu(pass){
	var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
				var res=this.responseText;
				if(res.includes('true')){
					document.getElementById("pasahitza1").style='border: 2px solid green;';pasahitza1_zuzena=true;
				}else{
					document.getElementById("pasahitza2").style='border: 2px solid red;';pasahitza1_zuzena=false;
				}
				parametro_guztiak_konprobatu();
            }
        };
        xmlhttp.open("GET", "../php/pasahitza_onargarria.php?pass="+pass, true);
        xmlhttp.send();
}
function erabiltzaile_email_konprobatu(email){
	var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
				var res=this.responseText;
				if(res.includes('true')){
					document.getElementById("erabiltzaile_email").style='border: 2px solid green;';email_zuzena=true;
				}else{
					document.getElementById("erabiltzaile_email").style='border: 2px solid red;';email_zuzena=false;
				}
				parametro_guztiak_konprobatu();
            }
        };
        xmlhttp.open("GET", "../php/erabiltzaile_nick_email.php?email="+email, true);
        xmlhttp.send();
}
function parametro_guztiak_konprobatu(){
	if( nick_zuzena==true && izenosoa_zuzena==true && pasahitza1_zuzena==true && pasahitza2_zuzena==true && email_zuzena==true ){
		document.getElementById("botonFormulario").disabled = false;
	}else{
		document.getElementById("botonFormulario").disabled = true;
	}
}


</script>

<script type="text/javascript">
$(document).ready(function(){
	$("#erabiltzaile_nick").keyup(function() { 
		var nick = document.getElementById("erabiltzaile_nick").value;
		erabiltzaile_nick_konprobatu(nick);
	});
	
	$("#erabiltzaile_email").keyup(function() { 
		var email = document.getElementById("erabiltzaile_email").value;
		erabiltzaile_email_konprobatu(email);
	});
	
	$("#pasahitza1").keyup(function() { 
		var pass = document.getElementById("pasahitza1").value;
		erabiltzaile_pass_konprobatu(pass);
		
		var pass1 = document.getElementById("pasahitza1").value;
		var pass2 = document.getElementById("pasahitza2").value;
		if(pass1!=pass2){
			document.getElementById("pasahitza2").style='border: 2px solid red;';pasahitza2_zuzena=false;
		}else{
			document.getElementById("pasahitza2").style='border: 2px solid green;';pasahitza2_zuzena=true;
		}
		parametro_guztiak_konprobatu();
	});
	
	$("#pasahitza2").keyup(function() { 
		var pass1 = document.getElementById("pasahitza1").value;
		var pass2 = document.getElementById("pasahitza2").value;
		if(pass1!=pass2){
			document.getElementById("pasahitza2").style='border: 2px solid red;';pasahitza2_zuzena=false;
		}else{
			document.getElementById("pasahitza2").style='border: 2px solid green;';pasahitza2_zuzena=true;
		}
		parametro_guztiak_konprobatu();
	});
	
});    
</script>
	
<div class="signup-form">
    <form action="/examples/actions/confirmation.php" method="post">
		<h2>Erregistratu</h2>
		<p class="hint-text">Erabiltzaile berria sortu.</p>
		<div class="form-group">
        	<input type="text" class="form-control" id="erabiltzaile_nick" name="erabiltzaile_nick" placeholder="Erabiltzailearen nick-a" required="required">
        </div>
		<div class="form-group">
        	<input type="text" class="form-control" id="erabiltzailearen_izena" name="erabiltzailearen_izena" placeholder="Erabiltzailearen izen-osoa" required="required">
        </div>
        <div class="form-group">
        	<input type="email" class="form-control" id="erabiltzaile_email" name="erabiltzaile_email" placeholder="Erabiltzailearen email-a" required="required">
        </div>
		<div class="form-group">
            <input type="password" class="form-control"  name='pasahitza1' id='pasahitza1' placeholder="Erabiltzailearen pasahitza" required="required">
        </div>
		<div class="form-group">
            <input type="password" class="form-control" name='pasahitza2' id='pasahitza2' placeholder="Pasahitza errepikatu" required="required">
        </div>        
        <!--<div class="form-group">
			<label class="checkbox-inline"><input type="checkbox" required="required"> I accept the <a href="#">Terms of Use</a> &amp; <a href="#">Privacy Policy</a></label>
		</div>-->
		<div class="form-group">
            <button type="button" id='botonFormulario' class="btn btn-success btn-lg btn-block" onclick='erabiltzailea_sortu_generala()' disabled>Erabiltzailea sortu</button>
        </div>
    </form>
</div>



</body>

</html>