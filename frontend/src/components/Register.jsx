import React, { forwardRef, useState } from "react";
import { Container, Row, Col, Form, Button, Alert } from "react-bootstrap";
import { registerUser } from "../services/authService";

const Register = forwardRef(({ scrollToSection, loginRef }, ref) => {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
    confirmPassword: "",
    firstName: "",
    lastName: "",
    dateOfBirth: "",
    phoneNumber: "",
    address: "",
    city: "",
    stateCode: "",
    ZIP: "",
    primaryOffice: "",
    role: "ROLE_PATIENT",
    npiNumber: "",
    clinicianRatePerHour: "",
  });

  const [cities, setCities] = useState([]);
  const [message, setMessage] = useState("");
  const [error, setError] = useState(false);

  const stateCityMap = {
    "AL": ["Birmingham", "Montgomery", "Mobile", "Huntsville", "Tuscaloosa"],
    "AK": ["Anchorage", "Fairbanks", "Juneau", "Sitka", "Ketchikan"],
    "AZ": ["Phoenix", "Tucson", "Mesa", "Chandler",
      "Scottsdale"],
    "AR": ["Little Rock", "Fort Smith", "Fayetteville",
      "Springdale", "Jonesboro"],
    "CA": ["Los Angeles", "San Francisco", "San Diego",
      "Sacramento", "San Jose"],
    "CO": ["Denver", "Colorado Springs", "Aurora",
      "Fort Collins", "Lakewood"],
    "CT": ["Hartford", "New Haven", "Bridgeport",
      "Stamford", "Waterbury"],
    "DE": ["Wilmington", "Dover", "Newark", "Middletown",
      "Smyrna"],
    "FL": ["Miami", "Orlando", "Tampa", "Jacksonville",
      "St. Petersburg"],
    "GA": ["Atlanta", "Augusta", "Columbus", "Savannah",
      "Athens"],
    "HI": ["Honolulu", "Hilo", "Kailua", "Kaneohe",
      "Waipahu"],
    "ID": ["Boise", "Nampa", "Meridian", "Idaho Falls", "Pocatello"],
    "IL": ["Chicago", "Aurora", "Naperville", "Rockford",
      "Joliet"],
    "IN": ["Indianapolis", "Fort Wayne", "Evansville",
      "South Bend", "Carmel"],
    "IA": ["Des Moines", "Cedar Rapids", "Davenport",
      "Sioux City", "Iowa City"],
    "KS": ["Wichita", "Overland Park", "Kansas City",
      "Topeka", "Olathe"],
    "KY": ["Louisville", "Lexington", "Bowling Green",
      "Owensboro", "Covington"],
    "LA": ["New Orleans", "Baton Rouge", "Shreveport",
      "Lafayette", "Lake Charles"],
    "ME": ["Portland", "Lewiston", "Bangor", "Augusta",
      "South Portland"],
    "MD": ["Baltimore", "Frederick", "Rockville",
      "Gaithersburg", "Bowie"],
    "MA": ["Boston", "Worcester", "Springfield",
      "Cambridge", "Lowell"],
    "MI": ["Detroit", "Grand Rapids", "Warren",
      "Sterling Heights", "Lansing"],
    "MN": ["Minneapolis", "Saint Paul", "Rochester",
      "Duluth", "Bloomington"],
    "MS": ["Jackson", "Gulfport", "Southaven",
      "Hattiesburg", "Biloxi"],
    "MO": ["Kansas City", "Saint Louis", "Springfield",
      "Columbia", "Independence"],
    "MT": ["Billings", "Missoula", "Great Falls",
      "Bozeman", "Butte"],
    "NE": ["Omaha", "Lincoln", "Bellevue", "Grand Island",
      "Kearney"],
    "NV": ["Las Vegas", "Henderson", "Reno", "North Las Vegas",
      "Sparks"],
    "NH": ["Manchester", "Nashua", "Concord", "Dover",
      "Rochester"],
    "NJ": ["Newark", "Jersey City", "Paterson", "Elizabeth",
      "Edison"],
    "NM": ["Albuquerque", "Las Cruces", "Rio Rancho",
      "Santa Fe", "Roswell"],
    "NY": ["New York City", "Buffalo", "Rochester",
      "Yonkers", "Syracuse"],
    "NC": ["Charlotte", "Raleigh", "Greensboro",
      "Durham", "Winston-Salem"],
    "ND": ["Fargo", "Bismarck", "Grand Forks",
      "Minot", "West Fargo"],
    "OH": ["Columbus", "Cleveland", "Cincinnati",
      "Toledo", "Akron"],
    "OK": ["Oklahoma City", "Tulsa", "Norman",
      "Broken Arrow", "Lawton"],
    "OR": ["Portland", "Salem", "Eugene", "Gresham",
      "Hillsboro"],
    "PA": ["Philadelphia", "Pittsburgh", "Allentown",
      "Erie", "Reading"],
    "RI": ["Providence", "Warwick", "Cranston",
      "Pawtucket", "East Providence"],
    "SC": ["Columbia", "Charleston", "North Charleston",
      "Mount Pleasant", "Rock Hill"],
    "SD": ["Sioux Falls", "Rapid City", "Aberdeen",
      "Brookings", "Watertown"],
    "TN": ["Nashville", "Memphis", "Knoxville",
      "Chattanooga", "Clarksville"],
    "TX": ["Houston", "San Antonio", "Dallas",
      "Austin", "Fort Worth"],
    "UT": ["Salt Lake City", "West Valley City",
      "Provo", "West Jordan", "Orem"],
    "VT": ["Burlington", "Essex", "South Burlington",
      "Rutland", "Barre"],
    "VA": ["Virginia Beach", "Norfolk", "Chesapeake",
      "Richmond", "Newport News"],
    "WA": ["Seattle", "Spokane", "Tacoma",
      "Vancouver", "Bellevue"],
    "WV": ["Charleston", "Huntington", "Morgantown",
      "Parkersburg", "Wheeling"],
    "WI": ["Milwaukee", "Madison", "Green Bay",
      "Kenosha", "Racine"],
    "WY": ["Cheyenne", "Casper", "Laramie",
      "Gillette", "Rock Springs"],
  };  

  const passwordsMatch = formData.password === formData.confirmPassword;
  const phoneNumberValid = /^\d{10}$/.test(formData.phoneNumber);
  const zipValid = /^\d{5}(-\d{4})?$/.test(formData.ZIP);

  const requiredFields = [
    "username",
    "password",
    "confirmPassword",
    "firstName",
    "lastName",
    "dateOfBirth",
    "phoneNumber",
    "address",
    "city",
    "stateCode",
    "ZIP",
    "primaryOffice",
  ].every((field) => formData[field].trim() !== "");

  const clinicianFieldsValid = formData.role !== "ROLE_CLINICIAN" || (
    formData.npiNumber.trim() !== "" && formData.clinicianRatePerHour.trim() !== ""); 

  const isFormValid = requiredFields && passwordsMatch && phoneNumberValid && zipValid && clinicianFieldsValid;

  const handleChange = (e) => {
    const { name, value } = e.target;

    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));

  if (name === "stateCode") {
    setCities(stateCityMap[value] || []);
    setFormData((prevData) => ({ ...prevData, city: "" }));
  }
};

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(false);
    setMessage("");

    const payload = {
      ...formData,
      primaryOffice: parseInt(formData.primaryOffice),
      clinicianRatePerHour: formData.clinicianRatePerHour
        ? parseFloat(formData.clinicianRatePerHour)
        : undefined,
    };

    try {
      await registerUser(payload);
      setMessage("Registration successful! You can now log in.");
      setFormData({
        username: "",
        password: "",
        confirmPassword: "",
        firstName: "",
        lastName: "",
        dateOfBirth: "",
        phoneNumber: "",
        address: "",
        city: "",
        stateCode: "",
        ZIP: "",
        primaryOffice: "",
        role: "ROLE_PATIENT",
        npiNumber: "",
        clinicianRatePerHour: "",
      });
      setCities([]);

      setTimeout(() => {
        if (scrollToSection && loginRef) {
          scrollToSection(loginRef);
        }
        }, 1500); 

    } catch (err) {
      console.error("Registration error:", err);
      setError(true);
      setMessage("Registration failed. Please try again.");
    }
  };

  return (
    <section id="register" className="min-vh-100 bg-white" ref={ref}>
      <Container className="d-flex justify-content-center align-items-center h-100">
        <Row>
          <Col className="text-center">
            <h2 className="text-center mb-4">Create an Account</h2>

            {message && (
              <Alert variant={error ? "danger" : "success"}>
                {message}
              </Alert>
            )}

            <Form onSubmit={handleSubmit}>
              <Form.Group className="mb-3">
                <Form.Label>Select Role</Form.Label>
                <Form.Select
                  name="role"
                  value={formData.role}
                  onChange={handleChange}
                  required
                >
                  <option value="ROLE_PATIENT">Patient</option>
                  <option value="ROLE_CLINICIAN">Clinician</option>
                  <option value="ROLE_ADMIN">Admin</option>
                  <option value="ROLE_RECEPTIONIST">Receptionist</option>
                  <option value="ROLE_STAFF">Staff</option>
                </Form.Select>
              </Form.Group>

              <Row>
                <Col md={6}>
                <Form.Group className="mb-3">
                  <Form.Label>Username</Form.Label>
                  <Form.Control
                    type="text"
                    name="username"
                    value={formData.username}
                    onChange={handleChange}
                    required
                    isInvalid={!formData.username.trim()}
                  />
                  <Form.Control.Feedback type="invalid">
                    Username is required.
                  </Form.Control.Feedback>
                </Form.Group>
              </Col>

              <Col md={6}>
                <Form.Group className="mb-3">
                  <Form.Label>Phone Number</Form.Label>
                  <Form.Control
                    type="tel"
                    name="phoneNumber"
                    value={formData.phoneNumber}
                    onChange={handleChange}
                    required
                    isInvalid={formData.phoneNumber && !phoneNumberValid}
                  />
                  <Form.Control.Feedback type="invalid">
                    Phone number must be 10 digits.
                  </Form.Control.Feedback>
                </Form.Group>
              </Col>
            </Row>

            <Row>
              <Col md={6}>
                <Form.Group className="mb-3">
                  <Form.Label>Password</Form.Label>
                  <Form.Control
                    type="password"
                    name="password"
                    value={formData.password}
                    onChange={handleChange}
                    required
                    isInvalid={!formData.password.trim()}
                  />
                  <Form.Control.Feedback type="invalid">
                    Password is required.
                  </Form.Control.Feedback>
                </Form.Group>
              </Col>

              <Col md={6}>
                <Form.Group className="mb-3">
                  <Form.Label>Confirm Password</Form.Label>
                  <Form.Control
                    type="password"
                    name="confirmPassword"
                    value={formData.confirmPassword}
                    onChange={handleChange}
                    required
                    isInvalid={formData.confirmPassword && !passwordsMatch}
                  />
                  <Form.Control.Feedback type="invalid">
                    Passwords do not match.
                  </Form.Control.Feedback>
                </Form.Group>
              </Col>
            </Row>

            <Row>
              <Col md={6}>
                <Form.Group className="mb-3">
                  <Form.Label>First Name</Form.Label>
                  <Form.Control
                    type="text"
                    name="firstName"
                    value={formData.firstName}
                    onChange={handleChange}
                    required
                    isInvalid={!formData.firstName.trim()}
                  />
                  <Form.Control.Feedback type="invalid">
                    First name is required.
                  </Form.Control.Feedback>
                </Form.Group>
              </Col>

              <Col md={6}>
                <Form.Group className="mb-3">
                  <Form.Label>Last Name</Form.Label>
                  <Form.Control
                    type="text"
                    name="lastName"
                    value={formData.lastName}
                    onChange={handleChange}
                    required
                    isInvalid={!formData.lastName.trim()}
                  />
                  <Form.Control.Feedback type="invalid">
                    Last name is required.
                  </Form.Control.Feedback>
                </Form.Group>
              </Col> 
            </Row>

            <Form.Group className="mb-3">
              <Form.Label>Date of Birth</Form.Label>
              <Form.Control
                type="date"
                name="dateOfBirth"
                value={formData.dateOfBirth}
                onChange={handleChange}
                required
                isInvalid={!formData.dateOfBirth.trim()}
              />
              <Form.Control.Feedback type="invalid">
                Date of birth is required.
              </Form.Control.Feedback>
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Address</Form.Label>
              <Form.Control
                type="text"
                name="address"
                value={formData.address}
                onChange={handleChange}
                required
                isInvalid={!formData.address.trim()}
              />
              <Form.Control.Feedback type="invalid">
                Address is required.
              </Form.Control.Feedback>
            </Form.Group>

            <Row>
              <Col md={4}>
                <Form.Group className="mb-3">
                  <Form.Label>State</Form.Label>
                  <Form.Select
                    name="stateCode"
                    value={formData.stateCode}
                    onChange={handleChange}
                    required
                    isInvalid={!formData.stateCode.trim()}
                  >
                    <option value="">Select State</option>
                    {Object.keys(stateCityMap).map((state) => (
                      <option key={state} value={state}>
                        {state}
                      </option>
                    ))}
                  </Form.Select>
                  <Form.Control.Feedback type="invalid">
                    State is required.
                  </Form.Control.Feedback>
                </Form.Group>
              </Col>

              <Col md={4}>
                <Form.Group className="mb-3">
                  <Form.Label>City</Form.Label>
                  <Form.Select
                    name="city"
                    value={formData.city}
                    onChange={handleChange}
                    required
                    isInvalid={!formData.city.trim()}
                  >
                    <option value="">Select City</option>
                    {cities.map((city) => (
                      <option key={city} value={city}>
                        {city}
                      </option>
                    ))}
                  </Form.Select>
                  <Form.Control.Feedback type="invalid">
                    City is required.
                  </Form.Control.Feedback>
                </Form.Group>
              </Col>

              <Col md={4}>
                <Form.Group className="mb-3">
                  <Form.Label>ZIP</Form.Label>
                  <Form.Control
                    type="text"
                    name="ZIP"
                    value={formData.ZIP}
                    onChange={handleChange}
                    required
                    isInvalid={formData.ZIP && !zipValid}
                  />
                  <Form.Control.Feedback type="invalid">
                    ZIP code must be 5 digits.
                  </Form.Control.Feedback> 
                </Form.Group>
              </Col>
            </Row>

            <Form.Group className="mb-3">
              <Form.Label>Primary Office</Form.Label>
              <Form.Control
                type="number"
                name="primaryOffice"
                value={formData.primaryOffice}
                onChange={handleChange}
                required
                isInvalid={!formData.primaryOffice.trim()}
              />
              <Form.Control.Feedback type="invalid">
                Primary office is required.
              </Form.Control.Feedback>
            </Form.Group>

            {formData.role === "ROLE_CLINICIAN" && (
              <>
              <Form.Group className="mb-3">
                <Form.Label>NPI Number</Form.Label>
                <Form.Control
                  type="text"
                  name="npiNumber"
                  value={formData.npiNumber}
                  onChange={handleChange}
                  required
                  isInvalid={!formData.npiNumber.trim()}
                />
                <Form.Control.Feedback type="invalid">
                  NPI number is required.
                </Form.Control.Feedback>
              </Form.Group>

              <Form.Group className="mb-3">
                <Form.Label>Clinician Rate Per Hour</Form.Label>
                <Form.Control
                  type="number"
                  name="clinicianRatePerHour"
                  value={formData.clinicianRatePerHour}
                  onChange={handleChange}
                  required
                  isInvalid={!formData.clinicianRatePerHour.trim()}
                />
                <Form.Control.Feedback type="invalid">
                  Clinician rate per hour is required.
                </Form.Control.Feedback>
              </Form.Group>
              </>
            )}

            <Button
              type="submit"
              variant="primary"
              className="w-100"
              disabled={!isFormValid}
              >
              Register
            </Button>
          </Form>
        </Col>
      </Row>
    </Container>
  </section>
);
});

export default Register;
