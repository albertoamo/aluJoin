package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import utils.DAO;

//La clase BeanUser sirve para rellenar el formulario de nuestro form.jsp
public class BeanUser 
{
	//Atributos que corresponden con nuestro formulario
	private String user = "";
	private String name = "";
	private String surname = "";
	private String gender = "";
	private String mail = "";
	private String day = "";
	private String month = "";
	private String year = "";
	private String password = "";
	private String description = "";
	
	//Usamos un array de ints para determinar errores (detección de nombre usuario y mail existentes)
	private int[] error = {0,0};

// --- Setters --- //
	
	public void setUser(String user)
	{
		try
		{
			DAO database = new DAO();
			ResultSet consulta_nom_usuari = database.executeSQL("SELECT * FROM `User` WHERE username IN ('"+user+"');");
		
				if(consulta_nom_usuari.next()) //Si la query contiene una fila, es que existe
					this.error[0] = 1;
		
				else
				{	
					this.error[0] = 0;
					this.user = user;
				}
				
			database.disconnectBD();
		 }
			
		//Catches de excepciones en la creación del DAO y en la ejecución del SQL
		catch (SQLException e) {e.printStackTrace();} 
		catch (Exception e) {e.printStackTrace();}	
	}
	
	public void setMail(String mail)
	{
		try
		{
			DAO database = new DAO();
			ResultSet consulta_mail_usuari = database.executeSQL("SELECT * FROM `User` WHERE mail IN ('"+mail+"');");
		
				if(consulta_mail_usuari.next()) //Si la query contiene una fila, es que existe
					this.error[1] = 1;
		
				else
				{	
					this.error[1] = 0;
					this.mail = mail;
				}
			database.disconnectBD();
		 }
			
		//Catches de excepciones en la creación del DAO y en la ejecución del SQL
		catch (SQLException e) {e.printStackTrace();} 
		catch (Exception e) {e.printStackTrace();}
	
	}

public void setName(String name) {this.name = name;}

public void setPassword(String password) {this.password = password;}
	
public void setDescription(String description) {this.description = description;}

public void setDay(String day) {this.day = day;}

public void setMonth(String month) {this.month = month;}

public void setYear(String year) {this.year = year;}

public void setGender(String gender) {this.gender = gender;}

public void setSurname(String surname) {this.surname = surname;}

public void setError(int[] error) {this.error = error;}
	
	/* Getters */

public String getName() {return name;}

public String getSurname() {return surname;}

public String getUser(){return user;}
	
public String getMail() {return mail;}

public String getDay() {return day;}

public String getMonth() {return month;}

public String getYear() {return year;}

public String getPassword() {return password;}

public String getDescription() {return description;}
	
public int[] getError() {return error;}

public String getGender() {return gender;}

public String toString() {
	String string = "";
	
	string = "User: " + this.user + "\n"+
			"name: " + this.name + "\n"+
			"surname: " + this.surname + "\n"+
			"gender: " + this.gender + "\n"+
			"mail: " + this.mail + "\n"+
			"Date: " + this.day + "/"+ this.month + "/" + this.year + "\n"+
			"Password: " + this.password + "\n" +
			"Description: " + this.description;
	
	return string;
	
}
	
/* Logic Functions */

//comprobamos si todos los campos obligatorios han sido rellenados
public boolean isComplete(){return(hasValue(getUser()) && hasValue(getMail()) && hasValue(getName()) && hasValue(getDay()) && hasValue(getMonth()) && hasValue(getYear()) && hasValue(getPassword()) && hasValue(getGender()) && this.error[0] == 0 && this.error[1] == 0 );}
	
private boolean hasValue(String val) {return((val != null) && (!val.equals("")));}

}
