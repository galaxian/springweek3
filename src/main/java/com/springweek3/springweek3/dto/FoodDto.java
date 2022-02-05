package com.springweek3.springweek3.dto;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FoodDto {

    private Long id;
    private String name;
    private int price;

}
