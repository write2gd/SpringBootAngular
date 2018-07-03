package com.gd.learn.angular.rest.model;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class Car {
    @Id @GeneratedValue
    private Long id;
    private @NonNull String name;
}
