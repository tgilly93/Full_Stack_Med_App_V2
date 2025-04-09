BEGIN TRANSACTION;

DROP TABLE IF EXISTS Office;
DROP TABLE IF EXISTS Staff;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS Patient;
DROP TABLE IF EXISTS Clinician;
DROP TABLE IF EXISTS Appointment;
DROP TABLE IF EXISTS Prescription;
DROP TABLE IF EXISTS Availability;
DROP TABLE IF EXISTS Transactions;
DROP TABLE IF EXISTS Messages;
DROP TABLE IF EXISTS Notification;

--------------INIT DATABASE----------------
CREATE TABLE Office (
	office_id serial NOT NULL,
	office_name varchar(50) UNIQUE NOT NULL,
	office_phone_number varchar(15),
	office_address varchar(100) NOT NULL,
	office_city varchar(100) NOT NULL,
	state varchar(2) NOT NULL,
	zip_code varchar(10),
	office_open time,
	office_close time,
	CONSTRAINT PK_Office PRIMARY KEY(office_id)
);

CREATE TABLE Staff (
	staff_id serial NOT NULL,
	office_id int NOT NULL,
	staff_first_name varchar(50) NOT NULL,
	staff_last_name varchar(50) NOT NULL,
	staff_address varchar(100) NOT NULL,	
	staff_phone_number varchar(15),
	CONSTRAINT PK_Staff PRIMARY KEY(staff_id),
	CONSTRAINT FK_Staff_Office FOREIGN KEY(office_id) REFERENCES Office(office_id)
);

CREATE TABLE users (
    	user_id SERIAL,
    	username varchar(50) NOT NULL UNIQUE,
    	password_hash varchar(200) NOT NULL,
    	role varchar(50) NOT NULL,
    	name varchar(50) NOT NULL,
    	address varchar(100) NULL,
    	city varchar(50) NULL,
    	state_code char(2) NULL,
    	zip varchar(5) NULL,
    	CONSTRAINT PK_user PRIMARY KEY (user_id)

);

CREATE TABLE Patient (
	patient_id serial NOT NULL,
	user_id int NOT NULL,
	patient_first_name varchar(50) NOT NULL,
	patient_last_name varchar(50) NOT NULL,
	patient_date_of_birth date NOT NULL,
	patient_address varchar(100) NOT NULL,
	patient_city varchar(100) NOT NULL,
	patient_state varchar(2) NOT NULL,
	zip_code varchar(10),
	patient_phone_number varchar(15),
	CONSTRAINT PK_Patient PRIMARY KEY(patient_id),
	CONSTRAINT FK_Patient_User FOREIGN KEY(user_id) REFERENCES users(user_id)	
);

CREATE TABLE Clinician (
	npi_number int UNIQUE NOT NULL,
	user_id int NOT NULL,
	staff_id int NOT NULL,	
	primary_office int,
	clinician_rate_per_hour DECIMAL,
	CONSTRAINT PK_Clinician PRIMARY KEY(npi_number),
	CONSTRAINT FK_Clinician_User FOREIGN KEY(user_id) REFERENCES users(user_id),
	CONSTRAINT FK_Clinician_Office FOREIGN KEY(primary_office) REFERENCES Office(office_id),
	CONSTRAINT FK_Clinician_Staff FOREIGN KEY(staff_id) REFERENCES Staff(staff_id)
);

CREATE TABLE Appointment (
	npi_number int NOT NULL,
	patient_id int NOT NULL, 
	date date NOT NULL,
	start_time time NOT NULL,
	end_time time NOT NULL,
	appointment_type varchar(50) NOT NULL,
	appointment_status varchar(50) NOT NULL,
	CONSTRAINT PK_Appointment PRIMARY KEY(npi_number, patient_id, date, start_time),
	CONSTRAINT FK_Appointment_Clinician FOREIGN KEY(npi_number) REFERENCES Clinician(npi_number),
	FOREIGN KEY(patient_id) REFERENCES Patient(patient_id)
);

CREATE TABLE Prescription (
	prescription_id serial UNIQUE NOT NULL,
	prescription_name text NOT NULL,
	patient_id int NOT NULL,
	npi_number int NOT NULL,
	prescription_details text,
	prescription_cost decimal,
	insurance_coverage text, ---added from outline, but do we need this? Will we add demo insurance info? or just coverage status (Active, Inactive, or insurance Name? Unclear)----
	prescription_status varchar(50) NOT NULL,
	CONSTRAINT PK_Prescription PRIMARY KEY(prescription_id),
	CONSTRAINT FK_Appointment_Clinician FOREIGN KEY(npi_number) REFERENCES Clinician(npi_number),
	FOREIGN KEY(patient_id) REFERENCES Patient(patient_id)
);
	
CREATE TABLE Availability (
	availability_id serial NOT NULL,
	npi_number int NOT NULL,
	office_id int NOT NULL,
	date date,
	day_of_week varchar(10),
	start_time time,
	end_time time,
	is_available varchar(5) NOT NULL,
	CONSTRAINT PK_Availability PRIMARY KEY(availability_id),
	CONSTRAINT FK_Availability_Clinician FOREIGN KEY(office_id) REFERENCES Office(office_id),
	FOREIGN KEY(npi_number) REFERENCES Clinician(npi_number)	
);

CREATE TABLE Transactions (
	transaction_id SERIAL NOT NULL,
	sender_id int NOT NULL,
	receiver_id int NOT NULL,
	CONSTRAINT PK_TRANSACTION PRIMARY KEY(transaction_id),
	CONSTRAINT FK_Transaction_Sender FOREIGN KEY(sender_id) REFERENCES Users(user_id),
    	CONSTRAINT FK_Transaction_Receiver FOREIGN KEY(receiver_id) REFERENCES Users(user_id)
);

CREATE TABLE Messages (
	message_id serial NOT NULL,
	sender_id int NOT NULL,
	receiver_id int NOT NULL,
	message_content text,
	date_time timestamp NOT NULL,
	message_type varchar(20),
	CONSTRAINT PK_Message PRIMARY KEY(message_id),
    	CONSTRAINT FK_Message_Sender FOREIGN KEY(sender_id) REFERENCES Users(user_id),
    	CONSTRAINT FK_Message_Receiver FOREIGN KEY(receiver_id) REFERENCES Users(user_id)	
);

CREATE TABLE Notification (
	notification_id serial NOT NULL,
	user_id int NOT NULL,
	date_time timestamp NOT NULL,
	notification_content text,
	notification_status varchar(10) NOT NULL,
	CONSTRAINT PK_Notification PRIMARY KEY(notification_id),
	CONSTRAINT FK_Notification FOREIGN KEY(user_id) REFERENCES users(user_id)
		
);

COMMIT TRANSACTION;
