package service;

import javax.swing.JOptionPane;
import javax.xml.ws.Endpoint;

import businessLogic.AdminGlobal_Implementation;
import businessLogic.UserLogged_Implementation;
import businessLogic.UserNotLogged_Implementation;
import configuration.ConfigXML;
import dataAccess.HibernateDataAccess;

public class Publisher {

	public static void main(String args[]){
		
		HibernateDataAccess dbManager = new HibernateDataAccess();
		dbManager.getAllTadeak();
		dbManager.getAllJokalariak();


		//Localhost-ean irekiko dugu
		//http://localhost:9999/usernotlogged?wsdl;
		String helbidea_usernotlogged= "http://0.0.0.0:9999/usernotlogged";
		Endpoint.publish(helbidea_usernotlogged,new UserNotLogged_Implementation());
		
		String helbidea_userlogged= "http://0.0.0.0:9999/userlogged";
		Endpoint.publish(helbidea_userlogged,new UserLogged_Implementation());
		
		String helbidea_admin= "http://0.0.0.0:9999/admin";
		Endpoint.publish(helbidea_admin,new AdminGlobal_Implementation());
		

		System.out.println("http://0.0.0.0:9999/usernotlogged?wsdl");
		System.out.println("http://0.0.0.0:9999/userlogged?wsdl");
		System.out.println("http://0.0.0.0:9999/admin?wsdl");
		
		JOptionPane.showMessageDialog(null,"Zerbitzaria martxan dago!");
		
	}

}
