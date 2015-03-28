import java.sql.Connection;
import java.sql.SQLException;


public class healthSystem {
	patientInterface patient;
	doctorInterface doctor;
	adminInterface admin;
	mainInterface mainIn;
	
	public healthSystem(Connection conn){
		patient = new patientInterface(conn);
		doctor = new doctorInterface(conn);
		admin = new adminInterface();
		mainIn = new mainInterface();
	}
	
	public void login(){
		mainIn.mainInterface();
		
		//if patient
		//if doctor
		
		//patient.getPatient("12444");
		
		
		//doctor.updatePatient("12345");
		//patient.viewInformation();
		//patient.viewGuardian();
	
		//patient.updateInformation();
		
		//admin.run();
	}
	
	
	/**
	 * 			Main
	 */
	public static void main(String[] args) {
		//initDB app = new initDB();
		//app.run();
		DBconnector dbconn = new DBconnector();
		try {
			//connect to the new database
			Connection conn = dbconn.getConnection("healthinformationsystem");
			healthSystem sys = new healthSystem(conn);	
			sys.login();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
