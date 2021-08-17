package com.webtooni.webtooniverse.domain.myList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyListRepository extends JpaRepository<MyList, Long>, MyListRepositoryCustom {

}
