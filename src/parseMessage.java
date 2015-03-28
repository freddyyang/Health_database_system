import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class parseMessage {

	private Message myMesgs;
	private Document dom;
	
	public parseMessage(){
		
	}
	
	private void parseXmlFile(){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();		
		try {			
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();			
			//parse using builder to get DOM representation of the XML file
			dom = db.parse("message.xml");
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private void parseDocument(){
		
		Message msg = new Message();
		parsePatient(msg);
		parseGuardian(msg);
		parseAuther(msg);
		parseInsuranceCompany(msg);
		parseFamilyHistory(msg);
		parseAllergies(msg);
		parseLabTestReports(msg);
		parsePlan(msg);
		
		myMesgs = msg;
	
	}
	
	private void parsePatient(Message msg){
	
		Element docEle = dom.getDocumentElement();	
		NodeList np = docEle.getElementsByTagName("patient");
		if(np != null && np.getLength() > 0) {
			for(int i = 0 ; i < np.getLength();i++) {				
				//get the element
				Element ep = (Element)np.item(i);		
				String patientId = getTextValue(ep,"patientId");
				String patientRole = getTextValue(ep,"patientRole");
				String GivenName = getTextValue(ep,"GivenName");
				String FamilyName = getTextValue(ep,"FamilyName");
				String BirthTime = getTextValue(ep,"BirthTime");
				String providerId = getTextValue(ep,"providerId");
				//String creationDate = getTextValue(ep,"creationDate");

				msg.setPatientid(patientId);
				msg.setPatientrole(patientRole);
				msg.setGivenname(GivenName);
				msg.setFamilyname(FamilyName);
				msg.setBirthtime(BirthTime);
				msg.setProviderId(providerId);
			}
		}
	}
	
	private void parseGuardian(Message msg){

		Element docEle = dom.getDocumentElement();	
		NodeList ng = docEle.getElementsByTagName("Guardian");
		if(ng != null && ng.getLength() > 0) {
			for(int i = 0 ; i < ng.getLength();i++) {				
				//get the element
				Element eg = (Element)ng.item(i);		
				String GuardianNo = getTextValue(eg,"GuardianNo");
				String Relationship = getTextValue(eg,"Relationship");
				String GivenName = getTextValue(eg,"GivenName");
				String FamilyName = getTextValue(eg,"FamilyName");
				String phone = getTextValue(eg,"phone");
				String address = getTextValue(eg,"address");
				String city = getTextValue(eg,"city");
				String state = getTextValue(eg,"state");
				String zip = getTextValue(eg,"zip");
				//String creationDate = getTextValue(mesgl,"creationDate");

				msg.setGuardiansNo(GuardianNo);
				msg.setRelationship(Relationship);
				msg.setGivenName(GivenName);
				msg.setFamilyName(FamilyName);
				msg.setPhone(phone);
				msg.setAddress(address);
				msg.setCity(city);
				msg.setState(state);
				msg.setZip(zip);
			}
		}
	}
	
	
	private void parseAuther(Message msg){
		Element docEle = dom.getDocumentElement();	
		NodeList ng = docEle.getElementsByTagName("Author");
		if(ng != null && ng.getLength() > 0) {
			for(int i = 0 ; i < ng.getLength();i++) {				
				//get the element
				Element eg = (Element)ng.item(i);		
				String AuthorId = getTextValue(eg,"AuthorId");
				String AuthorTitle = getTextValue(eg,"AuthorTitle");
				String AuthorFirstName = getTextValue(eg,"AuthorFirstName");
				String AuthorLastName = getTextValue(eg,"AuthorLastName");
				String ParticipatingRole = getTextValue(eg,"ParticipatingRole");

				msg.setAuthorId(AuthorId);
				msg.setAuthorTitle(AuthorTitle);
				msg.setAuthorFirstName(AuthorFirstName);
				msg.setAuthorLastName(AuthorLastName);
				msg.setParticipatingRole(ParticipatingRole);
			}
		}
	}
	
	
	private void parseInsuranceCompany(Message msg){

		Element docEle = dom.getDocumentElement();	
		NodeList ng = docEle.getElementsByTagName("InsuranceCompany");
		if(ng != null && ng.getLength() > 0) {
			for(int i = 0 ; i < ng.getLength();i++) {				
				//get the element
				Element eg = (Element)ng.item(i);		
				String PayerId = getTextValue(eg,"PayerId");
				String Name = getTextValue(eg,"Name");
				String PolicyHolder = getTextValue(eg,"PolicyHolder");
				String PolicyType = getTextValue(eg,"PolicyType");
				String Purpose = getTextValue(eg,"Purpose");

				msg.setPayerId(PayerId);
				msg.setPayerName(Name);
				msg.setPolicyHolder(PolicyHolder);
				msg.setPolicyType(PolicyType);
				msg.setPurpose(Purpose);
			}
		}
	}
	
	private void parseFamilyHistory(Message msg){

		Element docEle = dom.getDocumentElement();	
		NodeList ng = docEle.getElementsByTagName("FamilyHistory");
		if(ng != null && ng.getLength() > 0) {
			for(int i = 0 ; i < ng.getLength();i++) {				
				//get the element
				Element eg = (Element)ng.item(i);		
				String RelativeId = getTextValue(eg,"RelativeId");
				String Relationship = getTextValue(eg,"Relationship");
				String age = getTextValue(eg,"age");
				String Diagnosis = getTextValue(eg,"Diagnosis");

				msg.setRelativeID(RelativeId);
				msg.setRelationship(Relationship);
				msg.setAge(age);
				msg.setDiagnosis(Diagnosis);
			}
		}
	}
	
	private void parseAllergies(Message msg){

		Element docEle = dom.getDocumentElement();	
		NodeList ng = docEle.getElementsByTagName("Allergies");
		if(ng != null && ng.getLength() > 0) {
			for(int i = 0 ; i < ng.getLength();i++) {				
				//get the element
				Element eg = (Element)ng.item(i);		
				String Id = getTextValue(eg,"Id");
				String Substance = getTextValue(eg,"Substance");
				String Reaction = getTextValue(eg,"Reaction");
				String Status = getTextValue(eg,"Status");

				msg.setAllergyId(Id);
				msg.setSubstance(Substance);
				msg.setReaction(Reaction);
				msg.setState(Status);
			}
		}
	}
	
	
	private void parseLabTestReports(Message msg){

		Element docEle = dom.getDocumentElement();	
		NodeList ng = docEle.getElementsByTagName("LabTestReports");
		if(ng != null && ng.getLength() > 0) {
			for(int i = 0 ; i < ng.getLength();i++) {				
				//get the element
				Element eg = (Element)ng.item(i);		
				String LabTestResultId = getTextValue(eg,"LabTestResultId");
				String PatientVisitId = getTextValue(eg,"PatientVisitId");
				String LabTestPerformedDate = getTextValue(eg,"LabTestPerformedDate");
				String LabTestType = getTextValue(eg,"LabTestType");
				String TestResultValue = getTextValue(eg,"TestResultValue");
				String ReferenceRangeHigh = getTextValue(eg,"ReferenceRangeHigh");
				String ReferenceRangeLow = getTextValue(eg,"ReferenceRangeLow");

				msg.setLabTestResultID(LabTestResultId);
				msg.setPatientVisitId(PatientVisitId);
				msg.setLabTestPerformedDate(LabTestPerformedDate);
				msg.setLabTestType(LabTestType);
				msg.setTestResultValue(TestResultValue);
				msg.setReferenceRangeHigh(ReferenceRangeHigh);
				msg.setReferenceRangeLow(ReferenceRangeLow);
			}
		}
	}
	
	
	private void parsePlan(Message msg){

		Element docEle = dom.getDocumentElement();	
		NodeList ng = docEle.getElementsByTagName("Plan");
		if(ng != null && ng.getLength() > 0) {
			for(int i = 0 ; i < ng.getLength();i++) {				
				//get the element
				Element eg = (Element)ng.item(i);		
				String PlanId = getTextValue(eg,"PlanId");
				String Activity = getTextValue(eg,"Activity");
				String Date = getTextValue(eg,"Date");

				msg.setPlanId(PlanId);
				msg.setActivity(Activity);
				msg.setScheduledDate(Date);
			}
		}
	}
	
	
	/**
	 * I take a xml element and the tag name, look for the tag and get
	 * the text content 
	 * i.e for <employee><name>John</name></employee> xml snippet if
	 * the Element points to employee node and tagName is name I will return John  
	 * @param ele
	 * @param tagName
	 * @return
	 */
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}
		return textVal;
	}

	/**
	 * Calls getTextValue and returns a int value
	 * @param ele
	 * @param tagName
	 * @return
	 */
	private int getIntValue(Element ele, String tagName) {
		//in production application you would catch the exception
		return Integer.parseInt(getTextValue(ele,tagName));
	}
	
	public Message getMessage(){
		parseXmlFile();
		parseDocument();
		return myMesgs; 
	}
	
}
