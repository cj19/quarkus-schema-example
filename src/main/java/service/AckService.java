package service;

import org.apache.cxf.annotations.SchemaValidation;

import com.tsystems.etccm.iso12855.ack.AckServicePT;
import com.tsystems.etccm.iso12855.ack.InfoExchangeAck;

import io.quarkus.logging.Log;
import jakarta.jws.WebService;

@WebService(serviceName = "AckService", targetNamespace = "http://tsystems.com/etccm/iso12855/ack")
@SchemaValidation
public class AckService implements AckServicePT{

	@Override
	public String sendAck(InfoExchangeAck request) {
		Log.info("Test method");
		return null;
	}
}
