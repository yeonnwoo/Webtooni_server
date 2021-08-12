package com.webtooni.webtooniverse.domain.myList;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MyListRepository extends JpaRepository<MyList,Long>,MyListRepositoryCustom {

}
