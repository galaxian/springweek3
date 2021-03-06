package com.springweek3.springweek3.model;

import com.springweek3.springweek3.dto.RestaurantResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int minOrderPrice;

    @Column(nullable = false)
    private int deliveryFee;

    @Column
    private int x;

    @Column
    private int y;

    public RestaurantResponseDto toRestaurantResponseDto(int plus) {
        return RestaurantResponseDto.builder()
                .id(this.id)
                .name(this.name)
                .minOrderPrice(this.minOrderPrice)
                .deliveryFee(this.deliveryFee + plus)
                .x(this.x)
                .y(this.y)
                .build();
    }

}
