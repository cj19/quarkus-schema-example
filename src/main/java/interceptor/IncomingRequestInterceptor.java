package interceptor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import io.quarkus.logging.Log;

public class IncomingRequestInterceptor extends AbstractPhaseInterceptor<Message> {

    public IncomingRequestInterceptor() {
        super(Phase.RECEIVE);
    }

    @Override
    public void handleMessage(final Message message) {
        Log.infof("Incoming SOAP Request: '%s'", getRawMessage(message));
    }

    protected String getRawMessage(final Message message) {
        var is = message.getContent(InputStream.class);
        var baos = new ByteArrayOutputStream();
        String raw;
        try {
            IOUtils.copy(is, baos);
            var bytes = baos.toByteArray();
            InputStream copy = new ByteArrayInputStream(bytes);
            message.setContent(InputStream.class, copy);
            raw = new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Copy incoming message failed", e);
        }
        return raw;
    }
}
