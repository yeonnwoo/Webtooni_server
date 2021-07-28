package com.webtooni.webtooniverse.domain.join;

import com.webtooni.webtooniverse.domain.review.domain.Reviews;
import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    User user;

    @ManyToOne
    @JoinColumn(name = "REVIEW_ID")
    Reviews review;

}
