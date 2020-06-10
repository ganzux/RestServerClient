package com.ganzux.client.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ganzux.client.ClientConfiguration;
import com.ganzux.client.dto.Employee;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Alvaro Alcedo Moreno - aalcedo
 * @version 1.0
 * @since 2020-06-10
 */
@Service
public class ServiceEmployeeWebClientGet implements IServiceEmployeeGet {

    private static final Logger log = LoggerFactory.getLogger(ServiceEmployeeWebClientGet.class);

    @Autowired
    ClientConfiguration clientConfiguration;

    WebClient webClient;

    private WebClient getWebClient() {
        if (webClient == null) {
            TcpClient tcpClient = TcpClient
                    .create()
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                    .doOnConnected(connection -> {
                        connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
                        connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
                    });

            webClient = WebClient
                    .builder()
                    .baseUrl(clientConfiguration.getServerHostWithPort())
                    .defaultCookie("clientCookie", "cookieValue")
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultHeader(HttpHeaders.USER_AGENT, "Ganzux Client")
                    .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                    .build();
        }
        return webClient;
    }

    @Override
    public List<Employee> getEmployees(){
        try {
            WebClient.RequestBodySpec request1 = getWebClient()
                    .method(HttpMethod.GET)
                    .uri(clientConfiguration.getServerEndPointEmployeesGet())
                    .accept(MediaType.APPLICATION_JSON);

            Mono<String> response = request1
                    .retrieve()
                    .bodyToMono(String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
            Employee[] employess = objectMapper.readValue(response.block(), Employee[].class);
            return Arrays.asList(employess);
        } catch (Exception e) {
            // TODO
        }

        return null;
    }
}
