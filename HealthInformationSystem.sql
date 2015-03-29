/*
    Schema Design
*/


/* Entity Tables */

CREATE TABLE Guardians(
    guardiansNo CHAR(100),
    givenName CHAR(100),
    familyName CHAR(100),
    phone CHAR(100),
    address CHAR(100),
    city CHAR(100),
    state CHAR(100),
    zip CHAR(100),
    PRIMARY KEY(guardiansNo)
);

CREATE TABLE Author(
    authorId CHAR(100),
    authorTitle CHAR(100),
    authorFirstName CHAR(100),
    authorLastName CHAR(100),
    PRIMARY KEY(authorId)
);

CREATE TABLE InsuranceCompany(
    payerId CHAR(100),
    payerName CHAR(100),
    PRIMARY KEY(payerId)
);

CREATE TABLE Allergies(
    allergyId CHAR(100),
    substance CHAR(100),
    PRIMARY KEY(allergyId)   /* added substance to PRIMARY KEY */
 );

CREATE TABLE LabTestReports(
    LabTestResultID CHAR(100),
    PatientVisitId CHAR(100),
    LabTestPerformedDate CHAR(100),  /* Changed to DATETIME */
    LabTestType CHAR(100),
    TestResultValue CHAR(100),
    ReferenceRangeHigh CHAR(100),
    ReferenceRangeLow CHAR(100),
    PRIMARY KEY(LabTestResultID)
);


/* Relational Entity Tables*/

CREATE TABLE Patient_Insures_Support(  
    patientId CHAR(100),  /* auto_increment?? */
    patientRole CHAR(100) NOT NULL,
    givenName CHAR(100),
    familyName CHAR(100),
    suffix CHAR(100),
    gender CHAR(100),
    birthtime CHAR(100),     /* Changed to DATETIME */
    providerId CHAR(100),
    creationDateTime CHAR(100),    
    purpose CHAR(100),            /*  attributes to relationship */
    policyType CHAR(100),         /*  attributes to relationship */
    policyHolder CHAR (100),     /*  attributes to relationship */
    payerId CHAR(100) NOT NULL,           /* added for foreign keying to insurance */
    PRIMARY KEY(patientId),
    FOREIGN KEY (patientRole) REFERENCES Guardians (guardiansNo),
    FOREIGN KEY (payerId) REFERENCES InsuranceCompany (payerId)     /* providerID is not PayerId */
);


CREATE TABLE Plan_AssociatedBy(
    planId CHAR(100), 
    activity CHAR(100),
    ScheduledDate CHAR(100),    /*  attributes to relationship */
    patientId CHAR(100) NOT NULL,
    PRIMARY KEY(planId, patientId),   /* added patientId because of "Duplicate entry 'null' for key 'PRIMARY'" */
    FOREIGN KEY (patientId) REFERENCES Patient_Insures_Support(patientId)
);

CREATE TABLE Obtain_FamilyHistory(
    relativeID CHAR(100),
    relationship CHAR(100),
    age CHAR(100),
    diagnosis CHAR(100),
    patientId CHAR(100) NOT NULL,
    PRIMARY KEY (relativeID, patientId),
    FOREIGN KEY (patientId) REFERENCES Patient_Insures_Support(patientId) ON DELETE CASCADE
);

/* Relationship Tables*/

CREATE TABLE Patient_AssignedBy_Author(
    patientId CHAR(100),
    authorId CHAR(100),
    participatingRole CHAR(100),    
    PRIMARY KEY(patientId, authorId, participatingRole),
    FOREIGN KEY (patientId) REFERENCES Patient_Insures_Support(patientId),
    FOREIGN KEY (authorId) REFERENCES Author(authorId) 
);


CREATE TABLE Patient_Has_Allergie(
    patientId CHAR(100),
    allergyId CHAR(100),
    theStatus CHAR(100),     /*  attributes to relationship */
    reaction CHAR(100),      /*  attributes to relationship */
    PRIMARY KEY(patientId, allergyId),
    FOREIGN KEY (patientId) REFERENCES Patient_Insures_Support(patientId),
    FOREIGN KEY (allergyId) REFERENCES Allergies(allergyId)
);

CREATE TABLE Patient_Contains_LabTestReports(
    patientId CHAR(100),
    LabTestResultID CHAR(100),
    PRIMARY KEY(patientId, LabTestResultID),
    FOREIGN KEY (patientId) REFERENCES Patient_Insures_Support(patientId),
    FOREIGN KEY (LabTestResultID) REFERENCES LabTestReports(LabTestResultID)
);



