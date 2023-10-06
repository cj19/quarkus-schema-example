package service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

@QuarkusTest
class AckServiceTest {

	@Test
	void testAckService() {
		var response = RestAssured.given().header("Content-Type", "text/xml;charset=UTF-8")
        .and()
        .body("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ack=\"http://tsystems.com/etccm/iso12855/ack\"><soapenv:Header/><soapenv:Body><ack:InfoExchangeAck><ack:messageOriginator><ack:countryCode>1001010000</ack:countryCode><ack:providerIdentifier>40</ack:providerIdentifier></ack:messageOriginator><ack:ackADU><ack:apduAckCode>11</ack:apduAckCode></ack:ackADU></ack:InfoExchangeAck></soapenv:Body></soapenv:Envelope>")
        .when()
        .post("/test/service/ackService")
        .then()
        .extract()
        .response();
		assertEquals(HttpStatus.SC_OK, response.statusCode());
	}
}
