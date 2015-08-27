package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import utils.DAO;

public class BeanLogin {

	private String user = "";
	private String password = "";
	private int[] error = {0,0};
	
	public String getUser(){
		return user;
	}
	
	public void setUser(String user){
		try
		{
			DAO database = new DAO();
			ResultSet consulta_nom_usuari = database.executeSQL("SELECT * FROM `User` WHERE username IN ('"+user+"');");
		
				if(consulta_nom_usuari.next())//Preguntamos si existe el usuario solicitado
				{
					this.error[0] = 0;
					this.user = user;
				}
				else
				{	
					this.error[0] = 1;
				}
				
			database.disconnectBD();
		 }
			
		//Catches de excepciones en la creación del DAO y en la ejecución del SQL
		catch (SQLException e) {e.printStackTrace();} 
		catch (Exception e) {e.printStackTrace();}
	}
	
	public int[] getError() {
		return error;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		try
		{
			DAO database = new DAO();
			
			String cons1 = "select username, pass from `User` where username = \""+ this.user + "\"and pass = \"" + password + "\";";
			
			ResultSet consulta1 = database.executeSQL(cons1);
			
			if(consulta1.next()) //Si la query contiene una fila, es que existe y por lo tanto existe usuario i contraseña
				{
				 this.error[0] = 0;
				 this.password = password;
				}
			else
			{	
				this.error[1] = 1;
			}
			
			database.disconnectBD();
		 }
			
		//Catches de excepciones en la creación del DAO y en la ejecución del SQL
		catch (SQLException e) {e.printStackTrace();} 
		catch (Exception e) {e.printStackTrace();}
	}
	
	public boolean isComplete() {
		
	    return(hasValue(getUser()) && hasValue(getPassword()) && this.error[0] == 0 && this.error[1] == 0);
	}
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
	
}
