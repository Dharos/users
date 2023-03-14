package com.bci.users.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter @Setter @NoArgsConstructor
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private long number;

    @Column(name = "city_code",nullable = false)
    private Integer cityCode;

    @Column(name = "contry_code",nullable = false)
    private String contryCode;

}
