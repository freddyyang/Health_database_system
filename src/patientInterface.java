import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class patientInterface {
	sqlQueries sql = new sqlQueries();
	Connection conn;
	DBconnector dbconn = new DBconnector();
	
	String patientId;
	private String patientrole;
	private String givenname;
	private String familyname;
	private String patientRole;
	private String suffix;
	private String gender;
	private String birthtime;  
	private String providerId;   
	private String purpose;           
	private String policyType;       
	private String policyHolder;  
	private String payerId;
	
	private String guardiansNo;
    private String g_givenName;
    private String g_familyName;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zip;
	
    private String[] patientAttributes = {"patientId", "patientrole", "givenname", "familyname", 
			"birthtime", "providerId", "purpose", "policyType", "policyHolder", "payerId"};
    private String[] guardianAttributes = {"guardiansNo", "givenName", "familyName","phone","address","city","state","zip"};
    
	public patientInterface(Connection con){
		conn = con;
	}
	public boolean getPatient(String Id){	
		String query = "SELECT * FROM Patient_Insures_Support where patientId = "+Id;// where patientId = "+patientId;
		try {
			ResultSet rs = dbconn.executeSelect(conn, query);
			if(!rs.next()){
				System.out.println("Wrong Id! Please input agian!");
				return false;
			}		
		} catch(SQLException se){
		      //Handle errors for JDBC
			System.out.println("ERROR: Could not connect the DB");
			se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
		   System.out.println("ERROR: Could not connect the DB");
		   e.printStackTrace();
	   }
		patientId = Id;
		return true;
	}
	
	public boolean isPatientAttribute(String s){
		for(int i = 0; i < patientAttributes.length; i++)
			if(patientAttributes[i].equals(s))
				return true;
		return false;
	}
	
	public boolean isGuardianAttribute(String s){
		for(int i = 0; i < guardianAttributes.length; i++)
			if(guardianAttributes[i].equals(s))
				return true;
		return false;
	}
	
	public void viewInformation(){
		String query = "SELECT * FROM Patient_Insures_Support where patientId = "+patientId;// where patientId = "+patientId;
		try {
			ResultSet rs = dbconn.executeSelect(conn, query);
			if(rs.next()){
				//get data from database
				patientrole = rs.getString("patientRole");
			    givenname = rs.getString("givenname");
			    familyname = rs.getString("familyname");
			    //suffix = rs.getString("suffix");
			    //gender = rs.getString("suffix");
			    birthtime = rs.getString("birthtime");
			    providerId = rs.getString("providerId");
			    //xmlCreationdate = new Tuple("xmlCreationdate", rs.getDate("CreationDateTime").toString());
			    purpose = rs.getString("purpose");
			    policyType = rs.getString("PolicyType");
			    policyHolder = rs.getString("PolicyHolder");
			    payerId = rs.getString("payerId");
			    
			    System.out.println("===================================Your information==================================================");
				//print the table 
			    System.out.println("patientId      patientrole    givenname     familyname     birthtime        "+   
						"           providerId         purpose             policyType               policyHolder   payerId" );
				System.out.printf("%s%15s%15s%15s%30s%15s%25s%25s%10s%15s\n", patientId, patientrole, givenname, familyname, 
						birthtime, providerId, purpose, policyType, policyHolder, payerId);
				
				viewGuardian();
			}
		} catch(SQLException se){
		      //Handle errors for JDBC
			System.out.println("ERROR: Could not connect the DB");
			se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
		   System.out.println("ERROR: Could not connect the DB");
		   e.printStackTrace();
	   }
	}
	
	public void viewGuardian(){
		String query = "SELECT * FROM Patient_Insures_Support where patientId = "+patientId;
		ResultSet rs;
		try {
			rs = dbconn.executeSelect(conn, query);
			if(rs.next())
				guardiansNo = rs.getString("patientrole");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		query = "SELECT * FROM Guardians where guardiansNo = '"+guardiansNo+ "'";
		try {
			rs = dbconn.executeSelect(conn, query);
			if(rs.next()){
				//get data from database
			    g_givenName = rs.getString("givenname");
			    g_familyName = rs.getString("familyname");
			    phone = rs.getString("phone");
			    address = rs.getString("address");
			    city = rs.getString("city");
			    state = rs.getString("state");
			    zip = rs.getString("zip");
			    System.out.println("===================================Your gardian's information=========================================");
			    System.out.println("g_givenName   g_familyName      phone               address"+
			    				"                   city          state   zip");
			    System.out.printf("%s%15s%25s%25s%15s%10s%10s", g_givenName, g_familyName, phone, address, city, state, zip);
			}
		} catch(SQLException se){
		      //Handle errors for JDBC
			System.out.println("ERROR: Could not connect the DB");
			se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
		   System.out.println("ERROR: Could not connect the DB");
		   e.printStackTrace();
	   }
	}
	
	public void updateInformation(){	    
		try {
			Scanner input = new Scanner(System.in);
			System.out.println("Input the attribute you want to update and input 0 to finish inputting");
		    ArrayList<String> attribute = new ArrayList<String>();
		    String attr = input.nextLine();
		    while(!attr.equals("0")){
		    	if(isPatientAttribute(attr))
		    		attribute.add(attr);
		    	else
		    		System.out.println("Wrong attribute! Please input again!");
		    	attr = input.nextLine();
		    }
		    System.out.println("Input the value you want to update and input 0 to finish inputting");
		    ArrayList<String> value = new ArrayList<String>();
		    String val = input.nextLine();
		    while(!val.equals("0")){
		    	value.add(val);
		    	val = input.nextLine();
		    }
		    String query = "UPDATE Patient_Insures_Support " +
                   "SET ";
		    for(int i = 0; i < attribute.size() - 1; i++){
		    	query = query + attribute.get(i) + " = '" + value.get(i) + "', ";
		    }
		    query = query + attribute.get(attribute.size() - 1) + " = '" + value.get(attribute.size() - 1) + "' ";
		    query = query + " where patientId = "+patientId;
		    dbconn.executeUpdate(conn, query);
		    System.out.println("Update successfully!");
	   } catch(SQLException se){
		      //Handle errors for JDBC
			System.out.println("ERROR: Could not connect the DB");
			se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
		   System.out.println("ERROR: Could not connect the DB");
		   e.printStackTrace();
	   }
	}
	
	public void updateGuardians(){	    
		try {
			Scanner input = new Scanner(System.in);
			System.out.println("Input the attribute you want to update and input 0 to finish inputting");
		    ArrayList<String> attribute = new ArrayList<String>();
		    String attr = input.nextLine();
		    while(!attr.equals("0")){
		    	if(isGuardianAttribute(attr))
		    		attribute.add(attr);
		    	else
		    		System.out.println("Wrong attribute! Please input again!");
		    	attr = input.nextLine();
		    }
		    System.out.println("Input the value you want to update and input 0 to finish inputting");
		    ArrayList<String> value = new ArrayList<String>();
		    String val = input.nextLine();
		    while(!val.equals("0")){
		    	value.add(val);
		    	val = input.nextLine();
		    }
		    String query = "UPDATE Guardians " +
                   "SET ";
		    for(int i = 0; i < attribute.size() - 1; i++){
		    	query = query + attribute.get(i) + " = '" + value.get(i) + "', ";
		    }
		    query = query + attribute.get(attribute.size() - 1) + " = '" + value.get(attribute.size() - 1) + "' ";
		    query = query + " where guardiansNo = "+guardiansNo;
		    dbconn.executeUpdate(conn, query);
		    System.out.println("Update successfully!");
	   } catch(SQLException se){
		      //Handle errors for JDBC
			System.out.println("ERROR: Could not connect the DB");
			se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
		   System.out.println("ERROR: Could not connect the DB");
		   e.printStackTrace();
	   }
	}
}
