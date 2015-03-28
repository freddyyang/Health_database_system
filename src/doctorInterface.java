import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;



public class doctorInterface {
	sqlQueries sql = new sqlQueries();
	Connection conn;
	DBconnector dbconn = new DBconnector();
	ArrayList<String> allpatients = null;
	
	private String[] patientAttributes = {"patientId", "patientrole", "givenname", "familyname", 
				"birthtime", "providerId", "purpose", "policyType", "policyHolder", "payerId"};
	private String[] allergyAttributes = {"allergyId", "theStatus", "reaction"};
	private String[] planAttributes = {"planId", "activity", "ScheduledDate"};
	
	private String authorId;
	//data for patients
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
	
	//data for allergies
	// Attributes for Allergies
    private ArrayList<String> allergyId;
    private ArrayList<String> substance;
    private ArrayList<String> theStatus; 
    private ArrayList<String> reaction;
    
    //data for plan
    private ArrayList<String> planId;
    private ArrayList<String> activity;
    private ArrayList<String> ScheduledDate;
	
	public doctorInterface(Connection con){
		conn = con;
	}
	
	public boolean isPatientAttribute(String s){
		for(int i = 0; i < patientAttributes.length; i++)
			if(patientAttributes[i].equals(s))
				return true;
		return false;
	}
	
	public boolean isAllergytAttribute(String s){
		for(int i = 0; i < allergyAttributes.length; i++)
			if(allergyAttributes[i].equals(s))
				return true;
		return false;
	}
	
	public boolean isPlanAttribute(String s){
		for(int i = 0; i < planAttributes.length; i++)
			if(planAttributes[i].equals(s))
				return true;
		return false;
	}
	
	public boolean setDoctor(String Id){
		allpatients = new ArrayList<String>();
		String query = "SELECT * FROM Author where authorId = '"+Id + "'";// where patientId = "+patientId;
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
		authorId = Id;
		return true;
	}
	
	public int viewAllPatient(){
		String query = "SELECT * FROM Patient_AssignedBy_Author WHERE authorId = '" + authorId + "'";// where patientId = "+patientId;
		int n = 0;
		try {
			ResultSet rs = dbconn.executeSelect(conn, query);
			System.out.println("=======================================Patient Table=================================================");
			System.out.println("patientId            participatingRole");
			while(rs.next()){
				n++;
				//print the table 
				System.out.printf("%s%30s",rs.getString("patientId"),rs.getString("participatingRole"));
				allpatients.add(rs.getString("patientId"));
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
		return n;
	}
	
	public void viewOnePatient(String patientId){
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
	
	public void updatePatient(String patientId){
		//viewAllPatient();
		if(!allpatients.contains(patientId))
			System.out.println("Sorry! Patient "+ patientId + " is not your patient.");
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
	
	
	public void viewPatientAllergies(String patientId){
		allergyId = new ArrayList<String>();
	    substance = new ArrayList<String>();
	    theStatus = new ArrayList<String>(); 
	    reaction = new ArrayList<String>();
	   //String query = "SELECT * FROM Patient_Has_Allergie P, Allergies A WHERE P.patientId = "+ 
	    		//patientId + "AND P.allergyId = '" + allergyId + "'";// where patientId = "+patientId;
	    String query = "SELECT * FROM Patient_Has_Allergie WHERE patientId = "+ patientId;
		try {
			ResultSet rs = dbconn.executeSelect(conn, query);
			while(rs.next()){
				allergyId.add(rs.getString("allergyId"));
				theStatus.add(rs.getString("theStatus"));
				reaction.add(rs.getString("reaction"));
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
		
		int i = 0;
		while(i < allergyId.size()){
			query = "SELECT * FROM Allergies WHERE allergyId = "+ allergyId.get(i);
			try {
				ResultSet rs = dbconn.executeSelect(conn, query);
				i++;
				if(rs.next()){
					substance.add(rs.getString("substance"));
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
			System.out.println("AllergyId          substance          reaction            theStatus");
			for(int j  = 0; j < allergyId.size(); j++)
				System.out.printf("%s%25s%25s%25s", allergyId.get(j), substance.get(j), reaction.get(j), theStatus.get(j));
			
		}
	}
	

	public boolean updateAllergies(String patientId, String allergyId){
		viewPatientAllergies(patientId);
		if(!allergyId.contains(allergyId)){
			System.out.println("wrong allergyId. Please input again!");
			return false;
		}
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
		    String query = "UPDATE Patient_Has_Allergie " +
                   "SET ";
		    for(int i = 0; i < attribute.size() - 1; i++){
		    	query = query + attribute.get(i) + " = '" + value.get(i) + "', ";
		    }
		    query = query + attribute.get(attribute.size() - 1) + " = '" + value.get(attribute.size() - 1) + "' ";
		    query = query + " where patientId = "+patientId+ " AND allergyId = '" + allergyId + "'" ;
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
		return true;
	}
	
	
	public void viewPatientPlans(String patientId){
	    planId = new ArrayList<String>();
	    activity = new ArrayList<String>();
	    ScheduledDate = new ArrayList<String>();
	    String query = "SELECT * FROM Plan_AssociatedBy WHERE patientId = "+ patientId;// 
		try {
			ResultSet rs = dbconn.executeSelect(conn, query);
			while(rs.next()){
				planId.add(rs.getString("planId"));
				activity.add(rs.getString("activity"));
				ScheduledDate.add(rs.getString("ScheduledDate"));
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
		System.out.println("planId          activity          ScheduledDate");
		for(int j  = 0; j < allergyId.size(); j++)
			System.out.printf("%s%25s%25s%25s", planId.get(j), activity.get(j), ScheduledDate.get(j));
	}
	
	
	public boolean updatePlans(String patientId, String planId){
		viewPatientPlans(patientId);
		if(!allergyId.contains(allergyId)){
			System.out.println("wrong allergyId. Please input again!");
			return false;
		}
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
		    String query = "UPDATE Plan_AssociatedBy " +
                   "SET ";
		    for(int i = 0; i < attribute.size() - 1; i++){
		    	query = query + attribute.get(i) + " = '" + value.get(i) + "', ";
		    }
		    query = query + attribute.get(attribute.size() - 1) + " = '" + value.get(attribute.size() - 1) + "' ";
		    query = query + " where patientId = "+ patientId + " AND planId = '" + planId + "'" ;
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
		return false;

	}
}
