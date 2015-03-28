

public class Message {
	// Attributes for Guardians
    private String guardiansNo;
    private String givenName;
    private String familyName;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zip;
    
    // Attributes for Author
    private String authorId;
    private String authorTitle;
    private String authorFirstName;
    private String authorLastName;
    
    // Attributes for InsuranceCompany
    private String payerId;
    private String payerName;
    
    // Attributes for Allergies
    private String allergyId;
    private String substance;

    // Attributes for LabTestReports
    private String LabTestResultID;
    private String PatientVisitId;
    private String LabTestPerformedDate;
    private String LabTestType;
    private String TestResultValue;
    private String ReferenceRangeHigh;
    private String ReferenceRangeLow;
    
	// Attributes for Patients+ Insure Relation + Support Relation
	private String patientid;
	private String patientrole;
	private String givenname;
	private String familyname;
	private String suffix;
	private String gender;
	private String birthtime;
	private String providerId;
	private String xmlCreationdate;	
	private String purpose;
	private String policyType;
	private String policyHolder;
	
    // Attributes for Plan + AssociatedBy Relation
    private String planId;
    private String activity;
    private String ScheduledDate;
    //private String patientId;
    
    // Attributes for Obtain Relationship + FamilyHistory
    private String relativeID;
    private String relationship;
    private String age;
    private String diagnosis;
    //private String patientId;
    
    // Attributes for Patient_AssignedBy_Author Relation
    private String participatingRole;
    
    // Attributes for Patient_Has_Allergie Relation
    private String theStatus; 
    private String reaction;
	public String getGuardiansNo() {
		return guardiansNo;
	}
	public void setGuardiansNo(String guardiansNo) {
		this.guardiansNo = guardiansNo;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getAuthorTitle() {
		return authorTitle;
	}
	public void setAuthorTitle(String authorTitle) {
		this.authorTitle = authorTitle;
	}
	public String getAuthorFirstName() {
		return authorFirstName;
	}
	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}
	public String getAuthorLastName() {
		return authorLastName;
	}
	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}
	public String getPayerId() {
		return payerId;
	}
	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}
	public String getPayerName() {
		return payerName;
	}
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	public String getAllergyId() {
		return allergyId;
	}
	public void setAllergyId(String allergyId) {
		this.allergyId = allergyId;
	}
	public String getSubstance() {
		return substance;
	}
	public void setSubstance(String substance) {
		this.substance = substance;
	}
	public String getLabTestResultID() {
		return LabTestResultID;
	}
	public void setLabTestResultID(String labTestResultID) {
		LabTestResultID = labTestResultID;
	}
	public String getPatientVisitId() {
		return PatientVisitId;
	}
	public void setPatientVisitId(String patientVisitId) {
		PatientVisitId = patientVisitId;
	}
	public String getLabTestPerformedDate() {
		return LabTestPerformedDate;
	}
	public void setLabTestPerformedDate(String labTestPerformedDate) {
		LabTestPerformedDate = labTestPerformedDate;
	}
	public String getLabTestType() {
		return LabTestType;
	}
	public void setLabTestType(String labTestType) {
		LabTestType = labTestType;
	}
	public String getTestResultValue() {
		return TestResultValue;
	}
	public void setTestResultValue(String testResultValue) {
		TestResultValue = testResultValue;
	}
	public String getReferenceRangeHigh() {
		return ReferenceRangeHigh;
	}
	public void setReferenceRangeHigh(String referenceRangeHigh) {
		ReferenceRangeHigh = referenceRangeHigh;
	}
	public String getReferenceRangeLow() {
		return ReferenceRangeLow;
	}
	public void setReferenceRangeLow(String referenceRangeLow) {
		ReferenceRangeLow = referenceRangeLow;
	}
	public String getPatientid() {
		return patientid;
	}
	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}
	public String getPatientrole() {
		return patientrole;
	}
	public void setPatientrole(String patientrole) {
		this.patientrole = patientrole;
	}
	public String getGivenname() {
		return givenname;
	}
	public void setGivenname(String givenname) {
		this.givenname = givenname;
	}
	public String getFamilyname() {
		return familyname;
	}
	public void setFamilyname(String familyname) {
		this.familyname = familyname;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthtime() {
		return birthtime;
	}
	public void setBirthtime(String birthtime) {
		this.birthtime = birthtime;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public String getXmlCreationdate() {
		return xmlCreationdate;
	}
	public void setXmlCreationdate(String xmlCreationdate) {
		this.xmlCreationdate = xmlCreationdate;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	public String getPolicyHolder() {
		return policyHolder;
	}
	public void setPolicyHolder(String policyHolder) {
		this.policyHolder = policyHolder;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getScheduledDate() {
		return ScheduledDate;
	}
	public void setScheduledDate(String scheduledDate) {
		ScheduledDate = scheduledDate;
	}
	public String getRelativeID() {
		return relativeID;
	}
	public void setRelativeID(String relativeID) {
		this.relativeID = relativeID;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getParticipatingRole() {
		return participatingRole;
	}
	public void setParticipatingRole(String participatingRole) {
		this.participatingRole = participatingRole;
	}
	public String getTheStatus() {
		return theStatus;
	}
	public void setTheStatus(String theStatus) {
		this.theStatus = theStatus;
	}
	public String getReaction() {
		return reaction;
	}
	public void setReaction(String reaction) {
		this.reaction = reaction;
	}
}
