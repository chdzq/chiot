package org.chdzq.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bootstrap.BootstrapApplicationListener;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description 服务器
 * @Author chdzq
 * @Date 2024/11/14
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AuthenticationServer {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationServer.class, args);
    }
}
