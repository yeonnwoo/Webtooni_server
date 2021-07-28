package com.webtooni.webtooniverse.domain.join;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public enum Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

}
