package com.webtoon.demo.domain.myList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyListRepository extends JpaRepository<MyList,Long> {
}
