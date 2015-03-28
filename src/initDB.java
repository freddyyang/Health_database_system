
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

/* initDB class take the data from inputting sql file and put these data into our database.
 * 
 * 
 */


/*  Extra concerns: Corner cases needed (Duplicates, Null conditions); Accessed Time needs to be taken care;
 *  Functional (optional); missing attributes "suffix" and "gender"; single quote escape in PolicyHolder
 *
 * 
 * 
 * 
 * 
 * 
 */
public class initDB {
	
	
	// Attributes for Guardians
    private Tuple guardiansNo;
    private Tuple givenName;
    private Tuple familyName;
    private Tuple phone;
    private Tuple address;
    private Tuple city;
    private Tuple state;
    private Tuple zip;
    
    // Attributes for Author
    private Tuple authorId;
    private Tuple authorTitle;
    private Tuple authorFirstName;
    private Tuple authorLastName;
    
    // Attributes for InsuranceCompany
    private Tuple payerId;
    private Tuple payerName;
    
    // Attributes for Allergies
    private Tuple allergyId;
    private Tuple substance;

    // Attributes for LabTestReports
    private Tuple LabTestResultID;
    private Tuple PatientVisitId;
    private Tuple LabTestPerformedDate;
    private Tuple LabTestType;
    private Tuple TestResultValue;
    private Tuple ReferenceRangeHigh;
    private Tuple ReferenceRangeLow;
    
	// Attributes for Patients+ Insure Relation + Support Relation
	private Tuple patientid;
	private Tuple patientrole;
	private Tuple givenname;
	private Tuple familyname;
	private Tuple suffix;
	private Tuple gender;
	private Tuple birthtime;
	private Tuple providerId;
	private Tuple xmlCreationdate;	
	private Tuple purpose;
	private Tuple policyType;
	private Tuple policyHolder;
	
    // Attributes for Plan + AssociatedBy Relation
    private Tuple planId;
    private Tuple activity;
    private Tuple ScheduledDate;
    //private Tuple patientId;
    
    // Attributes for Obtain Relationship + FamilyHistory
    private Tuple relativeID;
    private Tuple relationship;
    private Tuple age;
    private Tuple diagnosis;
    //private Tuple patientId;
    
    // Attributes for Patient_AssignedBy_Author Relation
    private Tuple participatingRole;
    
    // Attributes for Patient_Has_Allergie Relation
    private Tuple theStatus; 
    private Tuple reaction;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private final String dbName_in = "healthmessagesexchange3";
	private final String dbName_out= "healthinformationsystem";
	
	// Connect to inputting DB (dbName_in)
	private Connection conn = null;
	// Connect to outputting DB (dbName_out)
	private Connection conn_out = null;
	sqlQueries sql = new sqlQueries(); 
	DBconnector dbconn = new DBconnector();
	
	/**
	 * Connect to MySQL and do SQL
	 */
	public void run() {

		
		try {
			conn = dbconn.getConnection(dbName_in);
			System.out.println("Connected to input database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the input database");
			e.printStackTrace();
			return;
		}
		
		
		// Connect to outputting DB (dbName_out)
		
		try {
			conn_out = dbconn.getConnection(dbName_out);
			System.out.println("Connected to output database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the output database");
			e.printStackTrace();
			return;
		}
		
		
		try {
			/* Retrieve from inputting DB */
			Statement stmt = conn.createStatement();		
			String rtvData = "SELECT * FROM messages";
		    ResultSet rs = stmt.executeQuery(rtvData);

		    /* Extract data from result set */
		    while(rs.next()){
		          
		    	  /* Retrieve by column name */		         
		          // Attributes for Guardians
		          guardiansNo = new Tuple ("guardiansNo", rs.getString("GuardianNo"));
		          givenName = new Tuple ("givenName", rs.getString("GivenName"));
		          familyName = new Tuple ("familyName", rs.getString("FamilyName"));
		          phone = new Tuple ("phone", rs.getString("phone"));
		          address = new Tuple ("address", rs.getString("address"));
		          city = new Tuple ("city", rs.getString("city"));
		          state = new Tuple ("state", rs.getString("state"));
		          zip = new Tuple ("zip", rs.getString("zip"));
		         
		          // Attributes for Author
		          authorId = new Tuple ("authorId", rs.getString("AuthorId"));
		          authorTitle = new Tuple ("authorTitle", rs.getString("AuthorTitle"));
		          authorFirstName = new Tuple ("authorFirstName", rs.getString("AuthorFirstName"));
		          authorLastName = new Tuple ("authorLastName", rs.getString("AuthorLastName"));
		         
		          // Attributes for InsuranceCompany
		          payerId = new Tuple ("payerId", rs.getString("PayerId"));
		          payerName = new Tuple ("payerName", rs.getString("Name"));
		          //PolicyHolder *****
		         
		          // Attributes for Allergies
		          allergyId = new Tuple ("allergyId", rs.getString("Id"));
		          substance = new Tuple ("substance", rs.getString("Substance"));

		          // Attributes for LabTestReports
		          LabTestResultID = new Tuple ("LabTestResultID", rs.getString("LabTestResultId"));
		          PatientVisitId = new Tuple ("PatientVisitId", rs.getString("PatientVisitId"));
		          LabTestPerformedDate = new Tuple ("LabTestPerformedDate", rs.getString("LabTestPerformedDate"));
		          LabTestType = new Tuple ("LabTestType", rs.getString("LabTestType"));
		          TestResultValue = new Tuple ("TestResultValue", rs.getString("TestResultValue"));
		          ReferenceRangeHigh = new Tuple ("ReferenceRangeHigh", rs.getString("ReferenceRangeHigh"));
		          ReferenceRangeLow = new Tuple ("ReferenceRangeLow", rs.getString("ReferenceRangeLow"));
		         
		     	  // Attributes for Patients+ Insure Relation + Support Relation
		     	  patientid = new Tuple ("patientid", rs.getString("patientId"));
		     	  patientrole = new Tuple ("patientrole", rs.getString("GuardianNo"));
		     	  givenname = new Tuple ("givenname", rs.getString("FirstName"));
		     	  familyname = new Tuple ("familyname", rs.getString("LastName"));
		     	  
		     	  
		     	  //suffix= new Tuple ("", rs.getString("suffix");
		     	  //gender = new Tuple ("", rs.getString("suffix");
		     	  birthtime = new Tuple ("birthtime", rs.getString("BirthTime"));
		     	  providerId = new Tuple ("providerId", rs.getString("providerId"));
		     	  //xmlCreationdate = new Tuple("xmlCreationdate", rs.getDate("CreationDateTime").toString());
		     	  purpose = new Tuple ("purpose", rs.getString("purpose"));
		     	  policyType = new Tuple ("policyType", rs.getString("PolicyType"));
		     	  
		     	  //PolicyHolder special condition.
		     	  String policyHolder_temp = rs.getString("PolicyHolder");
		     	  String[] parts;
		     	  if (policyHolder_temp.toLowerCase().contains("'"))
		     	  {
		     		  parts = policyHolder_temp.split("'");
		     		  policyHolder_temp = parts[0] + "''" + parts[1];
		     	  }
		     	  policyHolder = new Tuple ("policyHolder", policyHolder_temp);
		     	  
		     	  
		          // Attributes for Plan + AssociatedBy Relation
		          planId = new Tuple ("planId", rs.getString("PlanId"));
		          activity = new Tuple ("activity", rs.getString("Activity"));
		          ScheduledDate = new Tuple ("ScheduledDate", rs.getString("ScheduledDate"));
		 
		         
		          // Attributes for Obtain Relationship + FamilyHistory
		          relativeID = new Tuple ("relativeID", rs.getString("RelativeId"));
		          relationship = new Tuple ("relationship", rs.getString("Relation"));
		          age = new Tuple ("age", rs.getString("age"));
		          diagnosis = new Tuple ("diagnosis", rs.getString("Diagnosis"));
		          // patientId;
		         
		          // Attributes for Patient_AssignedBy_Author Relation
		          participatingRole = new Tuple ("participatingRole", rs.getString("ParticipatingRole"));
		          
		          // Attributes for Patient_Has_Allergie Relation
		          theStatus = new Tuple ("theStatus", rs.getString("Status")); 
		          reaction = new Tuple ("reaction", rs.getString("Reaction"));
		          
		          
			  	  String currentDateTime;
				  DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy HH:mm:ss aaa");
				  Calendar cal = Calendar.getInstance();
				  currentDateTime = dateFormat.format(cal.getTime());
				
		          xmlCreationdate = new Tuple ("creationDateTime", currentDateTime );
		          
		          
		          /* create sql statements */
		          String rtvData_guar = sql.insertStatement("Guardians", guardiansNo, givenName, familyName, phone, address, city, state, zip);
		          String rtvData_author = sql.insertStatement("Author",authorId, authorTitle, authorFirstName, authorLastName);
		          String rtvData_insurance = sql.insertStatement("InsuranceCompany", payerId, payerName);
		          String rtvData_allergy = sql.insertStatement("Allergies", allergyId, substance);
		          String rtvData_lab = sql.insertStatement("LabTestReports", LabTestResultID, PatientVisitId, LabTestPerformedDate, LabTestType, TestResultValue, ReferenceRangeHigh, ReferenceRangeLow);
		          String rtvData_patient = sql.insertStatement("Patient_Insures_Support", payerId, patientid, patientrole, givenname, familyname,birthtime, providerId, purpose, policyType, xmlCreationdate, policyHolder);
		          String rtvData_plan = sql.insertStatement("Plan_AssociatedBy", planId, activity, ScheduledDate, patientid);
		          String rtvData_family = sql.insertStatement("Obtain_FamilyHistory",relativeID, relationship, age, diagnosis, patientid);
		          String rtvData_assignedby = sql.insertStatement("Patient_AssignedBy_Author", patientid, authorId, participatingRole);
		          String rtvData_has = sql.insertStatement("Patient_Has_Allergie", patientid, allergyId, theStatus, reaction);
		          String rtvData_contains = sql.insertStatement("Patient_Contains_LabTestReports", patientid, LabTestResultID);
		          
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
				  
				  
		      }
	      rs.close();

	   } catch(SQLException se){
	      //Handle errors for JDBC
			System.out.println("ERROR: Could not connect the DB");
			se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
		    System.out.println("ERROR: Could not connect the DB");
		     e.printStackTrace();
	   }
		
		
	   System.out.println("Finished retriving from inputDB and inserting into outputDB.");
	}
	
	public Connection getConnector(){return conn_out;}
}

