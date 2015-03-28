import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

public class adminInterface {
	
	private Connection conn = null;
	private final DBconnector DB = new DBconnector();
	private final String dbName_out= "healthinformationsystem";

	public String selectStatement(String tableName, String patientId) {
		StringBuilder statement = new StringBuilder();
		String _statement = "SELECT * FROM " + tableName + " WHERE patientId = '" + patientId + "';";
	    return _statement;
	}

	// FUNCTION 1: View number of patients for each type of allergy (substance):	
	public String numPatients_eachType()
	{		
		String sql = "SELECT A.substance, count(*)"
			  + " FROM Allergies A, Patient_Has_Allergie P"
			  + " WHERE A.allergyId = P. allergyId"
			  + " GROUP BY A.substance";
		return sql;
	}
	
	// FUNCTION 2: List the patients who have more than one allergy
	public String patients_allergyMoreThanOne()
	{		
		String sql = "SELECT P.patientId, P.givenName, P.familyName"
			  + " FROM Patient_Insures_Support P"
			  + " WHERE P.patientId IN"
			  + " ( SELECT H.patientId"
			  + " FROM Patient_Has_Allergie H"
			  + " GROUP BY H.patientId"
			  + " HAVING count(*) > 1 )";
		return sql;
	}

	// FUNCTION 3: List the patients who have a plan for surgery today.
	public String patients_planToday()
	{		
		String currentDate;
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Calendar cal = Calendar.getInstance();
		currentDate = dateFormat.format(cal.getTime());
		
		String sql = "	SELECT B.patientId, B.givenName, B.familyName"
			  + " 	FROM Plan_AssociatedBy A, Patient_Insures_Support B"
			  + " 	WHERE A.patientId = B.patientId AND A.ScheduledDate LIKE '" + currentDate + "%'";
		return sql;
	}

	// FUNCTION 4: Identify authors with more than one patient.
	public String authors_moreThanOnePatient()
	{		
		String sql = "	SELECT U.authorId, U.authorFirstName, U.authorLastName"
			  + " 	FROM Author U"
			  + " 	WHERE U.authorId IN"
			  + " 		( SELECT A.authorId"
			  + " 		  FROM Patient_AssignedBy_Author A"
			  + " 	  	  GROUP BY A.authorId"
			  + " 		  HAVING count(*) > 1 )";
		return sql;
	}
	
	public String updateStatement(String tableName, String patientId, String column, String value) {
		String _statement = "UPDATE " + tableName + " SET " + column + " = '" + value
				+ "' WHERE patientId = '" + patientId + "';";
	    return _statement;
	}
	
	public void run (String funcName)
	{
		patients_planToday();
		try {
			conn = DB.getConnection(dbName_out);
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the output database in admin");
			e.printStackTrace();
			return;
		}
		
		try
		{ 
			Statement stmt = conn.createStatement();
			
			// FUNCTION 1: View number of patients for each type of allergy (substance):
			if (funcName.equals("1"))
			{
				String col1;
				String col2;
				String count = numPatients_eachType();
				ResultSet rs_count = stmt.executeQuery(count);
				System.out.print("Substance     Count\n");
				while(rs_count.next())
				{
					col1 = rs_count.getString("substance");
					col2= rs_count.getString("count(*)");
					System.out.print(" " + col1);
					for (int i = 0; i < (15 - col1.length()); i++)
					{
						System.out.print(" ");
					}
					System.out.print(col2 + "\n");
				}
				
			}
			
			
			// FUNCTION 2 and FUNCTION 3
			else if (funcName.equals("2")||funcName.equals("3"))
			{
				ResultSet rs_count = null;
				// FUNCTION 2: List the patients who have more than one allergy
				if (funcName.equals("2"))
				{
					String count = patients_allergyMoreThanOne();
					rs_count = stmt.executeQuery(count);
				}
				// FUNCTION 3: List the patients who have a plan for surgery today.
				else
				{
					String count = patients_planToday();
					rs_count = stmt.executeQuery(count);
				}
				String col1;
				String col2;
				String col3;
				System.out.print("patientID     givenName      familyName\n");				
				while(rs_count.next())
				{
					col1 = rs_count.getString("patientId");
					col2 = rs_count.getString("givenName");
					col3 = rs_count.getString("familyName");
					System.out.print(" " + col1);
					for (int i = 0; i < (15 - col1.length()); i++)
					{
						System.out.print(" ");
					}
					System.out.print(col2);
					for (int i = 0; i < (15 - col2.length()); i++)
					{
						System.out.print(" ");
					}
					System.out.print (col3 + "\n");
					
				}
			}
			// FUNCTION 4: Identify authors with more than one patient.
			else if (funcName.equals("4"))
			{	
				String col1, col2;
				String count = authors_moreThanOnePatient();
				ResultSet rs_count = stmt.executeQuery(count);
				System.out.print("authorFirstName     authorLastName\n");
				while(rs_count.next())
				{
					col1 = rs_count.getString("authorFirstName");
					col2= rs_count.getString("authorLastName");
					System.out.print(" " + col1);
					for (int i = 0; i < (15 - col1.length()); i++)
					{
						System.out.print(" ");
					}
					System.out.print(col2 + "\n");
				}
			}
			
		}
		catch(SQLException se){
		      //Handle errors for JDBC
				System.out.println("ERROR: Could not connect the DB view");
				se.printStackTrace();
		 	}catch(Exception e){
		      //Handle errors for Class.forName
			    System.out.println("ERROR: Could not connect the DB view");
			     e.printStackTrace();
		   }

			
	}
}
