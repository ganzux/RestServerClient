package com.ganzux.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * @author Alvaro Alcedo Moreno - aalcedo
 * @version 1.0
 * @since 2020-06-10
 */
@Component
public class ClientConfiguration {

    private String serverHost;
    private int serverPort;
    private String serverEndPointEmployeesGet;

    private static final String PROTOCOL = "http";

    @Autowired
    public ClientConfiguration(@Value("${destination.server.endPointEmployeesGet}") String serverEndPointEmployeesGet,
                      @Value("${destination.server.host}") String serverHost,
                      @Value("${destination.server.port}") int serverPort) {
        this.serverEndPointEmployeesGet = serverEndPointEmployeesGet;
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    public String getServerEndPointEmployeesGetDirection() {
        try {
            URI uri = new URI(PROTOCOL, null, serverHost, serverPort,
                    serverEndPointEmployeesGet, null, null);

            return uri.toString();
        } catch (Exception e) {
            // TODO
            return null;
        }
    }

    public String getServerHostWithPort() {
        try {
            URI uri = new URI(PROTOCOL, null, serverHost, serverPort,
                    null, null, null);

            return uri.toString();
        } catch (Exception e) {
            // TODO
            return null;
        }
    }



    public String getServerHost() {
        return serverHost;
    }

    public int getServerPort() {
        return serverPort;
    }

    public String getServerEndPointEmployeesGet() {
        return serverEndPointEmployeesGet;
    }

}
