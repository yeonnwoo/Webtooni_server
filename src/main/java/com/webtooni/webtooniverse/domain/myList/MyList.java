package com.webtooni.webtooniverse.domain.myList;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class MyList {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_list_id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "toon_id")
    private Webtoon webtoon;

    public MyList(User user, Webtoon webtoon) {
        this.user = user;
        this.webtoon = webtoon;
    }

    public static MyList of(User user, Webtoon webtoon)
    {
        return new MyList(user,webtoon);
    }

    
}
