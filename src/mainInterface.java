import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class mainInterface {
	public static void mainInterface()
	{
		String input;
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to HealthSystem! Please enter your status: \"Patient\", \"Doctor\", or \"Administrator\"\n"
				+ "If you have not imported the data yet please enter \"Import\"\n"
				+ "To Exit the HealthSystem, enter \"Exit\"");
	    input = in.nextLine();
	    if (input.equals("Import")){
	    	System.out.println("Please choose the way you want to import:\n1 for importing from database;\n2 for add data from mxl file:\n ");
	    	int n = in.nextInt();
	    	if(n == 1){
	    	initDB app = new initDB();
			app.run();	    
			mainInterface();
	    	}
	    	else if(n == 2){
	    		//if n == 2, then read the xml data into class Message msg and then use msg to add new tuples
	    		parseMessage pm = new parseMessage();
	    		Message msg = pm.getMessage();
	    		String dbName_out= "healthinformationsystem";
	    		DBconnector dbconn = new DBconnector();
	    		try {
	    			Connection conn_out = dbconn.getConnection(dbName_out);
	    			System.out.println("Connected to output database");
	    			sqlQueries Sql = new sqlQueries(); 
	    			   
			    	  /* Retrieve by column name */		         
			          // Attributes for Guardians
			          Tuple guardiansNo = new Tuple ("guardiansNo", msg.getGuardiansNo());
			          Tuple givenName = new Tuple ("givenName", msg.getGivenName());
			          Tuple familyName = new Tuple ("familyName", msg.getFamilyName());
			          Tuple phone = new Tuple ("phone", msg.getPhone());
			          Tuple address = new Tuple ("address", msg.getAddress());
			          Tuple city = new Tuple ("city", msg.getCity());
			          Tuple state = new Tuple ("state", msg.getState());
			          Tuple zip = new Tuple ("zip", msg.getZip());
			         
			          // Attributes for Author
			          Tuple authorId = new Tuple ("authorId", msg.getAuthorId());
			          Tuple authorTitle = new Tuple ("authorTitle", msg.getAuthorTitle());
			          Tuple authorFirstName = new Tuple ("authorFirstName", msg.getAuthorFirstName());
			          Tuple authorLastName = new Tuple ("authorLastName", msg.getAuthorLastName());
			         
			          // Attributes for InsuranceCompany
			          Tuple payerId = new Tuple ("payerId", msg.getPayerId());
			          Tuple payerName = new Tuple ("payerName", msg.getPayerName());
			          //PolicyHolder *****
			         
			          // Attributes for Allergies
			          Tuple allergyId = new Tuple ("allergyId", msg.getAllergyId());
			          Tuple substance = new Tuple ("substance", msg.getSubstance());

			          // Attributes for LabTestReports
			          Tuple LabTestResultID = new Tuple ("LabTestResultID", msg.getLabTestResultID());
			          Tuple PatientVisitId = new Tuple ("PatientVisitId", msg.getPatientVisitId());
			          Tuple LabTestPerformedDate = new Tuple ("LabTestPerformedDate", msg.getLabTestPerformedDate());
			          Tuple LabTestType = new Tuple ("LabTestType", msg.getLabTestType());
			          Tuple TestResultValue = new Tuple ("TestResultValue", msg.getTestResultValue());
			          Tuple ReferenceRangeHigh = new Tuple ("ReferenceRangeHigh", msg.getReferenceRangeHigh());
			          Tuple ReferenceRangeLow = new Tuple ("ReferenceRangeLow", msg.getReferenceRangeLow());
			         
			     	  // Attributes for Patients+ Insure Relation + Support Relation
			          Tuple patientid = new Tuple ("patientid", msg.getPatientid());
			          Tuple patientrole = new Tuple ("patientrole", msg.getParticipatingRole());
			          Tuple givenname = new Tuple ("givenname", msg.getGivenname());
			          Tuple familyname = new Tuple ("familyname", msg.getFamilyname());
			     	  
			     	  
			     	  //suffix= new Tuple ("", rs.getString("suffix");
			     	  //gender = new Tuple ("", rs.getString("suffix");
			          Tuple birthtime = new Tuple ("birthtime", msg.getBirthtime());
			          Tuple providerId = new Tuple ("providerId", msg.getProviderId());
			     	  //xmlCreationdate = new Tuple("xmlCreationdate", ???????);
			          Tuple purpose = new Tuple ("purpose", msg.getPurpose());
			          Tuple policyType = new Tuple ("policyType", msg.getPolicyType());
			     	  
			     	  //PolicyHolder special condition.
			     	  String policyHolder_temp = msg.getPolicyHolder();
			     	  String[] parts;
			     	  if (policyHolder_temp.toLowerCase().contains("'"))
			     	  {
			     		  parts = policyHolder_temp.split("'");
			     		  policyHolder_temp = parts[0] + "''" + parts[1];
			     	  }
			     	 Tuple policyHolder = new Tuple ("policyHolder", policyHolder_temp);
			     	  
			     	  
			          // Attributes for Plan + AssociatedBy Relation
			     	Tuple planId = new Tuple ("planId", msg.getPlanId());
			     	Tuple activity = new Tuple ("activity", msg.getActivity());
			     	Tuple ScheduledDate = new Tuple ("ScheduledDate", msg.getScheduledDate());
			 
			         
			          // Attributes for Obtain Relationship + FamilyHistory
			     	Tuple relativeID = new Tuple ("relativeID", msg.getRelativeID());
			     	Tuple relationship = new Tuple ("relationship", msg.getRelationship());
			     	Tuple age = new Tuple ("age", msg.getAge());
			     	Tuple diagnosis = new Tuple ("diagnosis", msg.getDiagnosis());
			          // patientId;
			         
			          // Attributes for Patient_AssignedBy_Author Relation
			     	Tuple participatingRole = new Tuple ("participatingRole", msg.getParticipatingRole());
			          
			          // Attributes for Patient_Has_Allergie Relation
			     	Tuple theStatus = new Tuple ("theStatus", msg.getTheStatus()); 
			     	Tuple reaction = new Tuple ("reaction", msg.getReaction());
			          
			     	String currentDateTime;
					DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy HH:mm:ss aaa");
					Calendar cal = Calendar.getInstance();
					currentDateTime = dateFormat.format(cal.getTime());
			     	Tuple xmlCreationdate = new Tuple ("creationDateTime", currentDateTime );  
			     	
			          /* create sql statements */
			          String rtvData_guar = Sql.insertStatement("Guardians", guardiansNo, givenName, familyName, phone, address, city, state, zip);
			          String rtvData_author = Sql.insertStatement("Author",authorId, authorTitle, authorFirstName, authorLastName);
			          String rtvData_insurance = Sql.insertStatement("InsuranceCompany", payerId, payerName);
			          String rtvData_allergy = Sql.insertStatement("Allergies", allergyId, substance);
			          String rtvData_lab = Sql.insertStatement("LabTestReports", LabTestResultID, PatientVisitId, LabTestPerformedDate, LabTestType, TestResultValue, ReferenceRangeHigh, ReferenceRangeLow);
			          String rtvData_patient = Sql.insertStatement("Patient_Insures_Support", payerId, patientid, patientrole, givenname, familyname,birthtime, providerId, purpose, policyType, xmlCreationdate, policyHolder);
			          String rtvData_plan = Sql.insertStatement("Plan_AssociatedBy", planId, activity, ScheduledDate, patientid);
			          String rtvData_family = Sql.insertStatement("Obtain_FamilyHistory",relativeID, relationship, age, diagnosis, patientid);
			          String rtvData_assignedby = Sql.insertStatement("Patient_AssignedBy_Author", patientid, authorId, participatingRole);
			          String rtvData_has = Sql.insertStatement("Patient_Has_Allergie", patientid, allergyId, theStatus, reaction);
			          String rtvData_contains = Sql.insertStatement("Patient_Contains_LabTestReports", patientid, LabTestResultID);
			          
			          /* execute the sql statements */
			          Statement stmt2 = conn_out.createStatement();	
			          String sql;
			          ResultSet rs2;
			          
			          //check duplicated in table Guardians
			          sql = "select * from Guardians where guardiansNo = "+guardiansNo.y;	  
					  rs2 = stmt2.executeQuery(sql);
					  if(!rs2.next())
						  dbconn.executeInsert(conn_out, rtvData_guar);
					  
					  //check duplicated in table Author
			          sql = "select * from Author where authorId = '"+authorId.y+ "'";
					  rs2 = stmt2.executeQuery(sql);
					  if(!rs2.next())
			          	dbconn.executeInsert(conn_out, rtvData_author);
					  	 
			          //check duplicated in table InsuranceCompany
			          sql = "select * from InsuranceCompany where payerId = '"+payerId.y + "'";
					  rs2 = stmt2.executeQuery(sql);
					  if(!rs2.next())
			          	dbconn.executeInsert(conn_out, rtvData_insurance);
					  
					  //check duplicated in table Allergies
			          sql = "select * from Allergies where allergyId = '"+allergyId.y + "'";
					  rs2 = stmt2.executeQuery(sql);
					  if(!rs2.next())
			          	dbconn.executeInsert(conn_out, rtvData_allergy);
					  
					  //check duplicated in table LabTestReports
			          sql = "select * from LabTestReports where LabTestResultID = '"+LabTestResultID.y + "'";
					  rs2 = stmt2.executeQuery(sql);
					  if(!rs2.next())
			          	dbconn.executeInsert(conn_out, rtvData_lab); 
					  
					//check duplicated in table Patient_Insures_Support
			          sql = "select * from Patient_Insures_Support where patientId = '"+patientid.y + "'";
					  rs2 = stmt2.executeQuery(sql);
					  if(!rs2.next())
			          	dbconn.executeInsert(conn_out, rtvData_patient);
			          	
					//check duplicated in table Plan_AssociatedBy
			          sql = "select * from Plan_AssociatedBy where planId = '" + planId.y + "' And patientId = '" + patientid.y + "'";
					  rs2 = stmt2.executeQuery(sql);
					  if(!rs2.next())	
			          	dbconn.executeInsert(conn_out, rtvData_plan);
			          	
					//check duplicated in table Obtain_FamilyHistory
			          sql = "select * from Obtain_FamilyHistory where relativeID = '" + relativeID.y + "' And patientId = '" + patientid.y + "'";
					  rs2 = stmt2.executeQuery(sql);
					  if(!rs2.next())	
			          	dbconn.executeInsert(conn_out, rtvData_family);
			          	
					//check duplicated in table Patient_AssignedBy_Author
			          sql = "select * from Patient_AssignedBy_Author where patientId = '" + patientid.y + "' And authorId = '" + authorId.y + "' And participatingRole = '" + participatingRole.y + "'";
					  rs2 = stmt2.executeQuery(sql);
					  if(!rs2.next())	
			          	dbconn.executeInsert(conn_out, rtvData_assignedby);
			          	
					//check duplicated in table Patient_Has_Allergie
			          sql = "select * from Patient_Has_Allergie where patientId = '" + patientid.y + "' And allergyId = '" + allergyId.y + "'";
					  rs2 = stmt2.executeQuery(sql);
					  if(!rs2.next())	 	
			          	dbconn.executeInsert(conn_out, rtvData_has);
			          	
					//check duplicated in table Patient_Contains_LabTestReports
			          sql = "select * from Patient_Contains_LabTestReports where patientId = '" + patientid.y + "' And LabTestResultID = '" + LabTestResultID.y + "'";
					  rs2 = stmt2.executeQuery(sql);
					  if(!rs2.next())	
			          	dbconn.executeInsert(conn_out, rtvData_contains);
	    			
	    		} catch (SQLException e) {
	    			System.out.println("ERROR: Could not connect to the output database");
	    			e.printStackTrace();
	    			return;
	    		}
				mainInterface();
	    	}
	    }
	    else if (input.equals("Exit"))
    		System.out.println("Thank you for using HealthSystem. Byebye.");
	    else if (input.equals("Patient")) {
	    	patientiInterface();
	    }
	    else if (input.equals("Doctor")) {
	    	doctorInterface();
	    }
	    else if (input.equals("Administrator")) 
	    {
	    	adminiInterface();
	    }
	}
	
	public static void adminiInterface()
	{
		String input;
		Scanner in = new Scanner(System.in);
		System.out.println("\nPlease enter \"1\",\"2\",\"3\",or \"4\" to perform the following funcions.\n"
    			+"Function 1: View number of patients for each type of allergy (substance). \n"
    			+"Function 2: List the patients who have more than one allergy. \n"
    			+"Function 3: List the patients who have a plan for surgery today.\n"
    			+"Function 4: Identify authors with more than one patient.\n"
    			+ "To Exit Administrator Status, enter \"Exit Administrator\"\n"
    			+ "To Exit the HealthSystem, enter \"Exit\"");
    	input = in.nextLine();
    	if (input.equals("Exit Administrator"))
    		mainInterface();
    	else if (input.equals("Exit"))
    		System.out.println("Thank you for using HealthSystem. Byebye.");
    	else
    	{
    		adminInterface admin = new adminInterface();
    		admin.run(input);
    		adminiInterface();
    	}
	}
	
	public static void patientiInterface()
	{
		Connection conn;
		DBconnector dbconn = new DBconnector();
		try {
			int k = 0;
			//connect to the new database
			conn = dbconn.getConnection("healthinformationsystem");
			String input;
			Scanner in = new Scanner(System.in);
			patientInterface patient = new patientInterface(conn);
			System.out.println("Please input your patient Id");
			String patientId = in.nextLine();
			while(!patient.getPatient(patientId))
				patientId = in.nextLine();
			while(k == 0){
				patient.viewInformation();
				System.out.println("\nDo you want to update your personal information?");
				input = in.nextLine();
				if(input.equals("yes") || input.equals("Yes") || input.equals("y") || input.equals("Y"))
					patient.updateInformation();
				System.out.println("\nDo you want to update your guardian's information?");
				input = in.nextLine();
				if(input.equals("yes") || input.equals("Yes") || input.equals("y") || input.equals("Y"))
					patient.updateGuardians();
				System.out.println("\nDo you want to exit?");
				input = in.nextLine();
				if(input.equals("yes") || input.equals("Yes") || input.equals("y") || input.equals("Y")){
					k = 1;
					System.out.println("\nThank you for using HealthSystem. Byebye.");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void doctorInterface()
	{
		Connection conn;
		DBconnector dbconn = new DBconnector();
		try {	
			//connect to the new database
			conn = dbconn.getConnection("healthinformationsystem");
			String input;
			Scanner in = new Scanner(System.in);
			doctorInterface doctor = new doctorInterface(conn);
			System.out.println("Please input your doctor Id");
			String doctorId = in.nextLine();
			while(!doctor.setDoctor(doctorId))
				doctorId = in.nextLine();
			
			int n = doctor.viewAllPatient();
			if(n == 0){
				System.out.println("\nYou don't have any patient.");
				System.out.println("\nThank you for using HealthSystem. Byebye.");
			}else{
				int k = 0;
				while(k == 0){	
					System.out.println("\nPlease choose a patient by inputing patientID"); 
					input = in.nextLine();
					doctor.viewOnePatient(input);
					String patientID = input;
					System.out.println("\nDo you want to edit yout patent's information?");
					input = in.nextLine();
					if(input.equals("yes") || input.equals("Yes") || input.equals("y") || input.equals("Y")){
						doctor.updatePatient(patientID);
					}
					System.out.println("\nDo you want to see yout patent's allergies?");	
					input = in.nextLine();
					if(input.equals("yes") || input.equals("Yes") || input.equals("y") || input.equals("Y")){
						doctor.viewPatientAllergies(patientID);
					}
					System.out.println("\nDo you want to edit yout patent's allergies?");	
					input = in.nextLine();
					if(input.equals("yes") || input.equals("Yes") || input.equals("y") || input.equals("Y")){
						System.out.println("\nchoose an allergy.");
						String allergyId = in.nextLine();
						if(!doctor.updateAllergies(patientID, allergyId))
							allergyId = in.nextLine();
					}
					System.out.println("\nDo you want to see yout patent's plans?");	
					input = in.nextLine();
					if(input.equals("yes") || input.equals("Yes") || input.equals("y") || input.equals("Y")){
						doctor.viewPatientPlans(patientID);
					}
					System.out.println("\nDo you want to edit yout patent's plans?");	
					input = in.nextLine();
					if(input.equals("yes") || input.equals("Yes") || input.equals("y") || input.equals("Y")){
						System.out.println("\nchoose a plan.");
						String planId = in.nextLine();
						if(!doctor.updatePlans(patientID, planId))
							planId = in.nextLine();
					}
					System.out.println("\nDo you want to exit?");
					input = in.nextLine();
					if(input.equals("yes") || input.equals("Yes") || input.equals("y") || input.equals("Y")){
						k = 1;
						System.out.println("\nThank you for using HealthSystem. Byebye.");
					}
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
