package com.mysite.sbbfinal.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mysite.sbbfinal.favorite.dto.Product;

@Service
public class NaverShoppingService {

//	private String clientId = "YOUR_CLIENT_ID"
    @Value("${naver.client.id}")
    private String clientId;

    @Value("${naver.client.secret}")
    private String clientSecret;

    public List<Product> searchProducts(String query) {
        String url = "https://openapi.naver.com/v1/search/shop.json";
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);
        HttpEntity<String> entity = new HttpEntity<>(headers);

//      RestTemplate : API 요청용 DTO라고 생각하자.
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.exchange(
                url + "?query=" + query, HttpMethod.GET, entity, Map.class
        );

        return (List<Product>) response.getBody().get("items");
    }
}