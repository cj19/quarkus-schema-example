package service;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.containsString;

@QuarkusTest
class AckServiceTest {

    @Test
    void testAckServiceInvalidApduReasonCode() {
        RestAssured.given()
                .header("Content-Type", "text/xml;charset=UTF-8")
                .body("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ack=\"http://tsystems.com/etccm/iso12855/ack\"><soapenv:Header/><soapenv:Body><ack:InfoExchangeAck><ack:messageOriginator><ack:countryCode>1001010000</ack:countryCode><ack:providerIdentifier>40</ack:providerIdentifier></ack:messageOriginator><ack:ackADU><ack:apduAckCode>11</ack:apduAckCode></ack:ackADU></ack:InfoExchangeAck></soapenv:Body></soapenv:Envelope>")
                .when()
                .post("/test/service/ackService")
                .then()
                .statusCode(500)
                .body(containsString("Unmarshalling Error: cvc-maxInclusive-valid: Value '11' is not facet-valid with respect to maxInclusive '10' for type 'ApduReasonCode'."));
    }

    @Test
    void testAckServiceValidXml() {
        RestAssured.given()
                .header("Content-Type", "text/xml;charset=UTF-8")
                .body("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ack=\"http://tsystems.com/etccm/iso12855/ack\"><soapenv:Header/><soapenv:Body><ack:InfoExchangeAck><ack:messageOriginator><ack:countryCode>1001010000</ack:countryCode><ack:providerIdentifier>40</ack:providerIdentifier></ack:messageOriginator><ack:ackADU><ack:apduAckCode>10</ack:apduAckCode></ack:ackADU></ack:InfoExchangeAck></soapenv:Body></soapenv:Envelope>")
                .when()
                .post("/test/service/ackService")
                .then()
                .statusCode(200);
    }
}
