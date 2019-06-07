<?php
class Komunitatea{ 
	var $idKomunitatea; 
	var $nick; 
	var $saria1; 
	var $saria2; 
	var $saria3; 
	/*public function __toString() {
		return $this->idKomunitatea;
	}*/
}
class Erabiltzailea{ 
	var $idErabiltzailea;
	//var $komunitatea;
	var $nick;
	var $email;
	var $pasahitza;
	var $izenOsoa;
	var $dirua;
	var $administratzailea;
	var $hizkuntza;
	var $koloreak;
	var $orduDiferentzia;	
	
	/*public function __toString() {
		return $this->idErabiltzailea;
	}*/

}


class Jokalaria{ 
	var $idJokalaria; 
	
	/*public function __toString() {
		return $this->idJokalaria;
	}*/
}

class MerkatukoJokalaria{ 
	var $idMerkatukoJokalaria; 
}

class Eskaintza{ 
	var $idEskaintza; 
}

class Jardunaldia{ 
	var $idJardunaldia; 
	var $hasierakoEguna; 
}

class Taldea{
	var $idTaldea;
}

class Mezua{
	var $idMezua;
}

?>
