package com.springweek3.springweek3.model;

import com.springweek3.springweek3.dto.FoodOrderDto;
import com.springweek3.springweek3.dto.FoodOrderRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class FoodOrder {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int price;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Food food;

    @ManyToOne
    @JoinColumn(nullable = false)
    private OrderSheet orderSheet;

}
