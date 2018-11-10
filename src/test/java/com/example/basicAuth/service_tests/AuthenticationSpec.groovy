package com.example.basicAuth.service_tests

import com.example.basicAuth.DemoApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.MultiValueMap
import spock.lang.Ignore
import spock.lang.Shared

import java.nio.charset.Charset

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import spock.lang.Specification


@SpringBootTest(classes = [DemoApplication.class], webEnvironment = RANDOM_PORT)
class AuthenticationSpec extends  Specification{

    @Autowired
    TestRestTemplate template


    def 'unauthorized users are not allowed to call the secured endpoints'(){
        given:
        assert template != null

        when:
        ResponseEntity<String> result = template.getForEntity(endpoints, String.class)

        then:
        result.statusCode == HttpStatus.UNAUTHORIZED

        where
        endpoints << ["/toni", "/api/players"]
    }

    @Ignore
    def 'users with bad credentials are blocked'(){
        given:
        assert template != null

        when:
        ResponseEntity<String> result = template.exchange(endpoints, HttpMethod.GET, new HttpEntity<>(createBasicAuthheaders("richard", "test1234")))

        then:
        result.statusCode == HttpStatus.OK

        where:
        endpoints << ["/toni", "/api/players"]
    }

    HttpHeaders createBasicAuthheaders(String username, String password){
        String auth = username + ":" + password;
        byte[] encryptedAuth = Base64.encoder.encode(auth.getBytes());
        String authheader = "Basic " + new String(encryptedAuth)
        HttpHeaders headers = new HttpHeaders()
        headers.add("Authorization", authheader)
        return headers
    }

}