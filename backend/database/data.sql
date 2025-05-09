BEGIN TRANSACTION;

---------------------------------------------------------------------------
--------------------------test data for view modeling----------------------

WITH user_insert_clinician AS (
    INSERT INTO users (username, password_hash, role, name, address, city, state_code, zip)
    VALUES
    ('clinician_test', '$2y$10$LSUmYkcPkE7jIr/f/7nf4u5hLYPXDM3qTT3gOmZdoYLVS0OVyw.7S', 'ROLE_CLINICIAN', 'Clinician Test', '123 test lane', 'Newark', 'DE', '19702')
    RETURNING user_id
),
user_insert_patient AS (
    INSERT INTO users (username, password_hash, role, name, address, city, state_code, zip)
    VALUES
    ('patient_test', '$2y$10$LSUmYkcPkE7jIr/f/7nf4u5hLYPXDM3qTT3gOmZdoYLVS0OVyw.7S', 'ROLE_PATIENT', 'Patient Test', '123 test lane', 'Newark', 'DE', '19702')
    RETURNING user_id
),
office_insert AS (
    INSERT INTO office (office_name, office_address, office_phone_number, office_city, state, zip_code, office_open, office_close)
    VALUES
    ('test_name', '123 test lane', '000-555-5555', 'Newark', 'DE', '19702', '08:00:00', '19:00:00')
    RETURNING office_id
),
patient_insert AS (
    INSERT INTO patient (user_id, patient_first_name, patient_last_name, patient_date_of_birth, patient_address, patient_city, patient_state, zip_code, patient_phone_number)
    SELECT user_id, 'patient_test_fname', 'patient_test_lname', '1900-01-01', '123 test lane', 'Newark', 'DE', '19702', '555-000-5555'
    FROM user_insert_patient
    RETURNING patient_id
),
staff_insert AS (
    INSERT INTO staff (office_id, staff_first_name, staff_last_name, staff_address, staff_phone_number)
    SELECT office_id, 'test_fname', 'test_lname', '456 Test Lane', '555-000-5555'
    FROM office_insert
    RETURNING staff_id
),
clinician_insert AS (
    INSERT INTO clinician (user_id, npi_number, staff_id, primary_office, clinician_rate_per_hour)
    SELECT user_id, '1000004441', staff_id, office_id, '89.00'
    FROM user_insert_clinician, staff_insert, office_insert
    RETURNING user_id
),
prescription_insert_1 AS (
    INSERT INTO prescription (prescription_name, patient_id, npi_number, prescription_details, prescription_cost, insurance_coverage, prescription_status)
    SELECT 'Zoloft', patient_id, '1000004441', 'Treats depression, anxiety, obsessive-compulsive disorder (OCD), post-traumatic stress disorder (PTSD), and premenstrual dysphoric disorder (PMDD)', '22.98', 'Uninsured', 'Active'
    FROM patient_insert
),
prescription_insert_2 AS (
    INSERT INTO prescription (prescription_name, patient_id, npi_number, prescription_details, prescription_cost, insurance_coverage, prescription_status)
    SELECT 'Ibuprofen', patient_id, '1000004441', 'Pain reliever', '22.98', 'Uninsured', 'Active'
    FROM patient_insert
),
appointment_insert_1 AS (
    INSERT INTO appointment (npi_number, patient_id, date, start_time, end_time, appointment_type, appointment_status)
    SELECT '1000004441', patient_id, '2024-08-22', '08:00', '09:00', 'Well Visit', 'Confirmed'
    FROM patient_insert
),
appointment_insert_2 AS (
    INSERT INTO appointment (npi_number, patient_id, date, start_time, end_time, appointment_type, appointment_status)
    SELECT '1000004441', patient_id, '2024-09-23', '10:00', '12:00', 'Outpatient Visit', 'Confirmed'
    FROM patient_insert
),
appointment_insert_3 AS (
    INSERT INTO appointment (npi_number, patient_id, date, start_time, end_time, appointment_type, appointment_status)
    SELECT '1000004441', patient_id, '2024-10-09', '13:00', '13:30', 'OBGYN Visit', 'Confirmed'
    FROM patient_insert
),
availability_insert_1 AS (
    INSERT INTO availability (npi_number, office_id, date, day_of_week, start_time, end_time, is_available)
    SELECT '1000004441', office_id, '2024-08-22', 'Wednesday', '08:00', '09:00', 'true'
    FROM clinician_insert, office_insert
),
availability_insert_2 AS (
    INSERT INTO availability (npi_number, office_id, date, day_of_week, start_time, end_time, is_available)
    SELECT '1000004441', office_id, '2024-10-09', 'Thursday', '10:00', '11:00', 'true'
    FROM clinician_insert, office_insert
),
availability_insert_3 AS (
   INSERT INTO availability (npi_number, office_id, date, day_of_week, start_time, end_time, is_available)
   SELECT '1000004441', office_id, '2024-09-23', 'Thursday', '11:00', '12:00', 'true'
   FROM clinician_insert, office_insert
),
availability_insert_4 AS (
   INSERT INTO availability (npi_number, office_id, date, day_of_week, start_time, end_time, is_available)
   SELECT '1000004441', office_id, '2024-10-09', 'Friday', '13:00', '14:00', 'true'
   FROM clinician_insert, office_insert
)
SELECT 'Data seeded successfully';

---------------------------------------------------------------------------------------------------
------------------------------------Views----------------------------------------------------------

CREATE VIEW patient_contact AS
SELECT
	patient_first_name,
	patient_last_name,
	patient_address,
	patient_city,
	patient_state,
	zip_code,
	patient_phone_number
FROM
	patient;

CREATE VIEW clinician_availability AS
SELECT
    c.npi_number,
	c.primary_office as Location,
	s.staff_last_name as Clinician,
	a.day_of_week AS day_of_week,
	a.start_time,
	a.end_time,
	a.is_available
FROM
	clinician c
	INNER JOIN availability a on a.npi_number = c.npi_number
	INNER JOIN staff s on s.staff_id = c.staff_id;

CREATE VIEW time_blocks AS
SELECT
	npi_number,
    	start_time || ' - ' || end_time AS "Time Block",
    	start_time,
    	end_time
FROM
    	appointment
GROUP BY
    	npi_number, start_time, end_time
ORDER BY
    	start_time, end_time;


CREATE VIEW scheduling_blocks AS
SELECT
	ROW_NUMBER() OVER (ORDER BY start_time, end_time) AS block_id,
	npi_number,
    	"Time Block",
    	start_time,
    	end_time
FROM
	time_blocks;

CREATE VIEW scheduled_appointments AS
SELECT
    a.appointment_id AS appointment_id,
	a.date as "Date",
	TO_CHAR(a.date, 'Day') AS "Day_of_Week",
	s.staff_last_name ||', ' || s.staff_first_name AS "Doctor",
	a.npi_number as "NPI",
	a.patient_id as "Patient",
	p.patient_first_name || ' ' || p.patient_last_name AS "Patient Name",
	sc.block_id as "Time Block",
	sc.start_time,
	sc.end_time,
	a.appointment_type as "Type",
	a.appointment_status as "Status"
FROM
	appointment a
	JOIN clinician c on c.npi_number = a.npi_number
	JOIN staff s on s.staff_id = c.staff_id
	JOIN patient p on p.patient_id = a.patient_id
	JOIN scheduling_blocks sc on sc.start_time = a.start_time AND sc.end_time = a.end_time;

CREATE OR REPLACE FUNCTION update_scheduled_appointments()
RETURNS TRIGGER AS $$
BEGIN
    -- Update the appointment table
    UPDATE appointment
    SET
        date = NEW."Date",
        start_time = NEW.start_time,
        end_time = NEW.end_time,
        appointment_type = NEW."Type",
        appointment_status = NEW."Status"
    WHERE appointment_id = NEW.appointment_id;
     -- Update the staff table

        UPDATE staff
    SET
        staff_last_name = split_part(NEW."Doctor", ', ', 1),
        staff_first_name = split_part(NEW."Doctor", ', ', 2)
    WHERE staff_id = (SELECT staff_id FROM clinician WHERE npi_number = (SELECT npi_number FROM appointment WHERE appointment_id = NEW.appointment_id));

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_scheduled_appointments_trigger
INSTEAD OF UPDATE ON scheduled_appointments
FOR EACH ROW
EXECUTE FUNCTION update_scheduled_appointments();

CREATE OR REPLACE FUNCTION insert_scheduled_appointments()
RETURNS TRIGGER AS $$
BEGIN
    -- Insert into staff table if the doctor doesn't exist
        INSERT INTO staff (staff_last_name, staff_first_name, office_id)
        SELECT
            TRIM(split_part(NEW."Doctor", ', ', 1)),
            TRIM(split_part(NEW."Doctor", ', ', 2)),
            COALESCE(
                (
                    SELECT c.primary_office
                    FROM clinician c
                    WHERE c.staff_id = (
                            SELECT s.staff_id
                            FROM staff s
                            WHERE TRIM(s.staff_last_name) || ', ' || TRIM(s.staff_first_name) = TRIM(NEW."Doctor")
                            )
                            LIMIT 1
                            ),
                            1
                )
        WHERE NOT EXISTS (
            SELECT 1
            FROM staff
            WHERE staff_last_name = TRIM(split_part(NEW."Doctor", ', ', 1))
              AND staff_first_name = TRIM(split_part(NEW."Doctor", ', ', 2))
        );

    -- Insert into appointment table
    INSERT INTO appointment (date, start_time, end_time, appointment_type, appointment_status, npi_number, patient_id)
    VALUES (
        NEW."Date",
        NEW.start_time,
        NEW.end_time,
        NEW."Type",
        NEW."Status",
        (SELECT npi_number 
         FROM clinician 
         WHERE staff_id = (SELECT staff_id 
                           FROM staff 
                           WHERE TRIM(staff_last_name) || ', ' || TRIM(staff_first_name) = TRIM(NEW."Doctor"))
        ),
        NEW."Patient"
    );

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION delete_scheduled_appointments()
RETURNS TRIGGER AS $$
BEGIN
    -- Delete from appointment table
    DELETE FROM appointment
    WHERE date = OLD."Date"
      AND npi_number = (SELECT npi_number FROM clinician 
         		   WHERE staff_id = (SELECT staff_id 
                           FROM staff 
                           WHERE staff_last_name || ', ' || staff_first_name = OLD."Doctor")
      )	
      AND patient_id = OLD."Patient"
      AND start_time = OLD.start_time;
    
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER insert_scheduled_appointments_trigger
INSTEAD OF INSERT ON scheduled_appointments
FOR EACH ROW
EXECUTE FUNCTION insert_scheduled_appointments();

CREATE TRIGGER delete_scheduled_appointments_trigger
INSTEAD OF DELETE ON scheduled_appointments
FOR EACH ROW
EXECUTE FUNCTION delete_scheduled_appointments();

CREATE VIEW office_info AS
SELECT
	office_name as Name,
	office_address as Location,
	office_city as City,
	state as State,
	zip_code as "Zip Code",
	office_phone_number as "Phone Number",
	office_open || ' - ' || office_close as "Hours of Operation"
FROM
	office;

CREATE VIEW clinician_office_info AS
SELECT 
	s.staff_first_name ||' ' || s.staff_last_name AS Physician,
	o.office_name as "Primary Location",
	o.office_address as Address,
	o.office_city as City,
	o.state as State,
	o.zip_code as "Zip Code",
	o.office_phone_number as "Phone Number",
	o.office_open || ' - ' || o.office_close as "Hours of Operation"
FROM
	office o
	JOIN staff s on s.office_id = o.office_id
	JOIN clinician c on c.staff_id = s.staff_id;
	
CREATE VIEW patient_active_prescription AS
SELECT
	pr.patient_id as "Patient ID",
	p.patient_first_name || ' ' || p.patient_last_name as "Name",
	p.patient_date_of_birth as "DOB",
	p.patient_address as "Street Address",
	p.patient_city as "City",
	p.patient_state as "State",
	p.zip_code as "Zip Code",
	p.patient_phone_number as "Phone",
	pr.prescription_id as "Prescription ID",
	pr.prescription_name as "Common Name",
	pr.prescription_details as "Description",
	pr.prescription_status as "Prescription Status",
	pr.npi_number as "Prescribing Clinician"

FROM
	prescription pr
	JOIN patient p on p.patient_id = pr.patient_id;	
	

CREATE VIEW prescription_info AS
SELECT
	prescription_id as "Prescription ID",
	prescription_name as "Prescription Name",
	prescription_details as "Description",
	prescription_cost as "Cost"
FROM
	prescription;

CREATE VIEW daily_agenda AS
SELECT
	a.date AS "Date",
    	TO_CHAR(a.date, 'Day') AS "Day_of_Week",
	s.staff_last_name ||', ' || s.staff_first_name AS "Doctor",	
	a.patient_id as "Patient",
	p.patient_first_name || ' ' || p.patient_last_name AS "Patient Name",
	sc.block_id as "Time Block",
	sc.start_time,
	sc.end_time,
	a.appointment_type as Type,
	a.appointment_status as Status,
	a.appointment_id AS appointment_id
FROM
	appointment a
	JOIN clinician c on c.npi_number = a.npi_number
	JOIN staff s on s.staff_id = c.staff_id
	JOIN patient p on p.patient_id = a.patient_id
	JOIN scheduling_blocks sc on sc.start_time = a.start_time AND sc.end_time = a.end_time
WHERE a.date = current_date;

CREATE OR REPLACE FUNCTION get_daily_agenda_for_date(p_date DATE)
RETURNS TABLE (
    "Date" DATE,
    "Day_of_Week" TEXT,
    "Doctor" TEXT,
    "Patient" bigint,
    "Patient Name" TEXT,
    "Time Block" bigint,
    start_time TIME,
    end_time TIME,
    Type varchar(50),
    Status varchar(50),
    appointment_id bigint
) AS $$
BEGIN
    RETURN QUERY
    SELECT
        a.date AS "Date",
        TO_CHAR(a.date, 'Day') AS "Day_of_Week",
        s.staff_last_name ||', ' || s.staff_first_name AS "Doctor",    
        a.patient_id::bigint AS "Patient",
        p.patient_first_name || ' ' || p.patient_last_name AS "Patient Name",
        sc.block_id::bigint AS "Time Block",
        sc.start_time,
        sc.end_time,
        a.appointment_type::varchar(50) AS Type,
        a.appointment_status::varchar(50) AS Status,
        a.appointment_id::bigint AS appointment_id
    FROM
        appointment a
    JOIN clinician c ON c.npi_number = a.npi_number
    JOIN staff s ON s.staff_id = c.staff_id
    JOIN patient p ON p.patient_id = a.patient_id
    JOIN scheduling_blocks sc ON sc.start_time = a.start_time AND sc.end_time = a.end_time
    WHERE a.date = p_date;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE VIEW weekly_agenda AS
SELECT
	date_trunc('week', a.date) AS "Week_Start_Date",
    	TO_CHAR(date_trunc('week', a.date), 'Week of YYYY-MM-DD') AS "Week_Label",
	a.date AS "Date",
	TO_CHAR(a.date, 'Day') AS "Day_of_Week",
    	s.staff_last_name || ', ' || s.staff_first_name AS "Doctor",
    	a.patient_id AS "Patient",
    	p.patient_first_name || ' ' || p.patient_last_name AS "Patient Name",
    	sc.block_id AS "Time Block",
    	sc.start_time,
    	sc.end_time,
    	a.appointment_type AS Type,
    	a.appointment_status AS Status,
    	a.appointment_id AS appointment_id
FROM
    appointment a
	JOIN clinician c ON c.npi_number = a.npi_number
	JOIN staff s ON s.staff_id = c.staff_id
	JOIN patient p ON p.patient_id = a.patient_id
	JOIN scheduling_blocks sc ON sc.start_time = a.start_time AND sc.end_time = a.end_time
GROUP BY
    	date_trunc('week', a.date),
	a.date,
    	s.staff_last_name, 
    	s.staff_first_name, 
    	a.patient_id, 
    	p.patient_first_name, 
    	p.patient_last_name, 
    	sc.block_id, 
    	sc.start_time, 
    	sc.end_time, 
    	a.appointment_type, 
    	a.appointment_status,
    	a.appointment_id
ORDER BY
    	"Week_Start_Date", 
    	"Doctor", 
    	"Patient";

CREATE OR REPLACE VIEW monthly_agenda AS
SELECT
    	date_trunc('month', a.date) AS "Month_Start_Date",
    	TO_CHAR(date_trunc('month', a.date), 'Month YYYY') AS "Month_Label",
	a.date AS "Date",
	TO_CHAR(a.date, 'Day') AS "Day_of_Week",
    	s.staff_last_name || ', ' || s.staff_first_name AS "Doctor",
    	a.patient_id AS "Patient",
    	p.patient_first_name || ' ' || p.patient_last_name AS "Patient Name",
    	sc.block_id AS "Time Block",
    	sc.start_time,
    	sc.end_time,
    	a.appointment_type AS Type,
    	a.appointment_status AS Status,
    	a.appointment_id AS appointment_id
FROM
    appointment a
	JOIN clinician c ON c.npi_number = a.npi_number
	JOIN staff s ON s.staff_id = c.staff_id
	JOIN patient p ON p.patient_id = a.patient_id
	JOIN scheduling_blocks sc ON sc.start_time = a.start_time AND sc.end_time = a.end_time
GROUP BY
    	date_trunc('month', a.date),
    	a.date,
	    s.staff_last_name,
    	s.staff_first_name, 
    	a.patient_id, 
    	p.patient_first_name, 
    	p.patient_last_name, 
    	sc.block_id, 
    	sc.start_time, 
    	sc.end_time, 
    	a.appointment_type, 
    	a.appointment_status,
    	a.appointment_id
ORDER BY
    	"Month_Start_Date", 
    	"Doctor", 
    	"Patient";
	
--------------------------------------------------------------------------------
COMMIT TRANSACTION;
