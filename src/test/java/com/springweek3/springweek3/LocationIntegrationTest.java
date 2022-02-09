package com.springweek3.springweek3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springweek3.springweek3.dto.RestaurantResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LocationIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders headers;
    private ObjectMapper mapper = new ObjectMapper();

    private final List<RestaurantDto> registeredRestaurants = new ArrayList<>();

    @BeforeEach
    public void setup() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Nested
    @DisplayName("음식점 3개 등록 및 조회")
    class RegisterRestaurants {
        @Test
        @Order(1)
        @DisplayName("음식점1 등록")
        void test1() throws JsonProcessingException {
            // given
            RestaurantDto restaurantRequest = RestaurantDto.builder()
                    .id(null)
                    .name("쉐이크쉑 청담점")
                    .minOrderPrice(1_000)
                    .deliveryFee(0)
                    .x(1)
                    .y(2)
                    .build();

            String requestBody = mapper.writeValueAsString(restaurantRequest);
            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

            // when
            ResponseEntity<RestaurantDto> response = restTemplate.postForEntity(
                    "/restaurant/register",
                    request,
                    RestaurantDto.class);

            // then
            assertEquals(HttpStatus.OK, response.getStatusCode());

            RestaurantDto restaurantResponse = response.getBody();
            assertNotNull(restaurantResponse);
            assertTrue(restaurantResponse.id > 0);
            assertEquals(restaurantRequest.name, restaurantResponse.name);
            assertEquals(restaurantRequest.minOrderPrice, restaurantResponse.minOrderPrice);
            assertEquals(restaurantRequest.deliveryFee, restaurantResponse.deliveryFee);
            assertTrue(restaurantRequest.x > 0 && restaurantRequest.y > 0);
            assertTrue(restaurantRequest.x < 100 && restaurantRequest.y < 100);

            // 음식점 등록 성공 시, registeredRestaurants 에 추가
            registeredRestaurants.add(restaurantResponse);
        }

        @Test
        @Order(2)
        @DisplayName("x 값 99 초과")
        void test2() throws JsonProcessingException {
            // given
            RestaurantDto restaurantRequest = RestaurantDto.builder()
                    .id(null)
                    .name("자담치킨 강남점")
                    .minOrderPrice(100_000)
                    .deliveryFee(10_000)
                    .x(100)
                    .y(2)
                    .build();

            String requestBody = mapper.writeValueAsString(restaurantRequest);
            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

            // when
            ResponseEntity<RestaurantDto> response = restTemplate.postForEntity(
                    "/restaurant/register",
                    request,
                    RestaurantDto.class);

            // then
            assertTrue(
                    response.getStatusCode() == HttpStatus.BAD_REQUEST
                            || response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR
            );

        }

        @Test
        @Order(3)
        @DisplayName("x 값 0 미만")
        void test3() throws JsonProcessingException {
            // given
            RestaurantDto restaurantRequest = RestaurantDto.builder()
                    .id(null)
                    .name("마라하오 위례점")
                    .minOrderPrice(100000)
                    .deliveryFee(10000)
                    .x(-1)
                    .y(1)
                    .build();

            String requestBody = mapper.writeValueAsString(restaurantRequest);
            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

            // when
            ResponseEntity<RestaurantDto> response = restTemplate.postForEntity(
                    "/restaurant/register",
                    request,
                    RestaurantDto.class);

            // then
            assertTrue(
                    response.getStatusCode() == HttpStatus.BAD_REQUEST
                            || response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR
            );

        }

        @Test
        @Order(4)
        @DisplayName("음식점 2 등록")
        void test4() throws JsonProcessingException {
            // given
            RestaurantDto restaurantRequest = RestaurantDto.builder()
                    .id(null)
                    .name("맛있는 음식점")
                    .minOrderPrice(100_000)
                    .deliveryFee(10_000)
                    .x(5)
                    .y(4)
                    .build();

            String requestBody = mapper.writeValueAsString(restaurantRequest);
            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

            // when
            ResponseEntity<RestaurantDto> response = restTemplate.postForEntity(
                    "/restaurant/register",
                    request,
                    RestaurantDto.class);

            // then
            assertEquals(HttpStatus.OK, response.getStatusCode());

            RestaurantDto restaurantResponse = response.getBody();
            assertNotNull(restaurantResponse);
            assertTrue(restaurantResponse.id > 0);
            assertEquals(restaurantRequest.name, restaurantResponse.name);
            assertEquals(restaurantRequest.minOrderPrice, restaurantResponse.minOrderPrice);
            assertEquals(restaurantRequest.deliveryFee, restaurantResponse.deliveryFee);
            assertTrue(restaurantRequest.x > 0 && restaurantRequest.y > 0);
            assertTrue(restaurantRequest.x < 100 && restaurantRequest.y < 100);

            // 음식점 등록 성공 시, registeredRestaurants 에 추가
            registeredRestaurants.add(restaurantResponse);
        }

        @Test
        @Order(5)
        @DisplayName("음식점 3 등록")
        void test5() throws JsonProcessingException {
            // given
            RestaurantDto restaurantRequest = RestaurantDto.builder()
                    .id(null)
                    .name("맛있는 치킨집")
                    .minOrderPrice(5000)
                    .deliveryFee(1000)
                    .x(3)
                    .y(3)
                    .build();

            String requestBody = mapper.writeValueAsString(restaurantRequest);
            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

            // when
            ResponseEntity<RestaurantDto> response = restTemplate.postForEntity(
                    "/restaurant/register",
                    request,
                    RestaurantDto.class);

            // then
            assertEquals(HttpStatus.OK, response.getStatusCode());

            RestaurantDto restaurantResponse = response.getBody();
            assertNotNull(restaurantResponse);
            assertTrue(restaurantResponse.id > 0);
            assertEquals(restaurantRequest.name, restaurantResponse.name);
            assertEquals(restaurantRequest.minOrderPrice, restaurantResponse.minOrderPrice);
            assertEquals(restaurantRequest.deliveryFee, restaurantResponse.deliveryFee);
            assertTrue(restaurantRequest.x > 0 && restaurantRequest.y > 0);
            assertTrue(restaurantRequest.x < 100 && restaurantRequest.y < 100);

            // 음식점 등록 성공 시, registeredRestaurants 에 추가
            registeredRestaurants.add(restaurantResponse);
        }

        @Test
        @Order(6)
        @Transactional
        @DisplayName("이용자 좌표가 없는 지역 (-1,1001)에서 이용가능한 음식점 조회")
        void test6() throws RestClientException {
            Map<String, Integer> vars = new HashMap<>();
            vars.put("x", -1);
            vars.put("y", 1001);
            // when
            ResponseEntity<RestaurantDto[]> response = restTemplate.getForEntity(
                    "/restaurants?x={x}&y={y}",
                    RestaurantDto[].class,
                    vars
            );

            // then
            assertTrue(
                    response.getStatusCode() == HttpStatus.BAD_REQUEST
                            || response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        @Test
        @Order(7)
        @Transactional
        @DisplayName("이용자 (2,4)에서 이용가능한 음식점 조회")
        void test7() throws RestClientException {
            Map<String, Integer> vars = new HashMap<>();
            vars.put("x", 2);
            vars.put("y", 4);
            // when
            ResponseEntity<RestaurantDto[]> response = restTemplate.getForEntity(
                    "/restaurants?x={x}&y={y}",
                    RestaurantDto[].class,
                    vars
            );

            // then
            assertEquals(HttpStatus.OK, response.getStatusCode());
            RestaurantDto[] responseRestaurants = response.getBody();
            assertNotNull(responseRestaurants);
            assertEquals(registeredRestaurants.size(), responseRestaurants.length);
            for (RestaurantDto responseRestaurant : responseRestaurants) {
                RestaurantDto registerRestaurant = registeredRestaurants.stream()
                        .filter(restaurant -> responseRestaurant.getId().equals(restaurant.getId()))
                        .findAny()
                        .orElse(null);

                assertNotNull(registerRestaurant);
                assertEquals(registerRestaurant.getName(), responseRestaurant.getName());
                assertEquals(registerRestaurant.getDeliveryFee() + 500 * Math.abs(registerRestaurant.getX() + registerRestaurant.getY() - (vars.get("x") + vars.get("y"))), responseRestaurant.getDeliveryFee());
                assertEquals(registerRestaurant.getMinOrderPrice(), responseRestaurant.getMinOrderPrice());
            }
        }
    }


    @Getter
    @Setter
    @Builder
    static class RestaurantDto {
        private Long id;
        private String name;
        private int minOrderPrice;
        private int deliveryFee;
        private int x;
        private int y;
    }

    @Getter
    @Setter
    @Builder
    static class RestaurantResponseDto {
        private Long id;
        private String name;
        private int minOrderPrice;
        private int deliveryFee;
        private int x;
        private int y;
    }
}
