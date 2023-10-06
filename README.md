# quarkus-schema-example

This project contains the service, the wsdl and xsd in order to test out the schema validation.
You can find a test case, where in the body the included xml content is invalid, because the apduAckCode has the invalid value.
The schema validation is turned on (there is the annotation on the web service class), however the test still passes. 