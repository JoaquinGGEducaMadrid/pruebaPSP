package Principal;

import fi.iki.elonen.NanoHTTPD;
import java.io.IOException;

public class HealthServer extends NanoHTTPD {
    public HealthServer(int port) throws IOException {
        super(port);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("HTTP server escuchando en puerto " + port);
    }

    @Override
    public Response serve(IHTTPSession session) {
        return newFixedLengthResponse("Servicio activo");
    }

    public static void main(String[] args) {
        try {
            new HealthServer(8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


