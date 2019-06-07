package configuration;

public class ConfigXML {

	private String businessLogicNode="0.0.0.0";
	
	private int businessLogicPort=9999;

	private String businessLogicName="nbasaresoziala";
		
	private int databasePort=3306;
	
	private String Datu_Basearen_Helbidea="jdbc:mysql://localhost:3306/nba_liga_saresoziala?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String Datu_Basearen_Administratzailea="root";
	private String Datu_Basearen_Pasahitza="123456789";
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	
	private static ConfigXML theInstance = new ConfigXML();
	
	public static ConfigXML getInstance() {
		return theInstance;
	}
	
	public String businessLogicName() {return businessLogicName;}
	
	
	
	
	
	//ConfigXML fitxategiaren datuak irakurri
	private ConfigXML() {
		
	}
	


	public String getBusinessLogicNode() {
		return businessLogicNode;
	}
	public int getBusinessLogicPort() {
		return businessLogicPort;
	}
	public String getBusinessLogicName() {
		return businessLogicName;
	}
	public int getDatabasePort() {
		return databasePort;
	}
	public String getDatu_Basearen_Helbidea() {
		return Datu_Basearen_Helbidea;
	}
	public String getDatu_Basearen_Administratzailea() {
		return Datu_Basearen_Administratzailea;
	}
	public String getDatu_Basearen_Pasahitza() {
		return Datu_Basearen_Pasahitza;
	}
	public String getDriver() {
		return driver;
	}
	public static ConfigXML getTheInstance() {
		return theInstance;
	}




}
