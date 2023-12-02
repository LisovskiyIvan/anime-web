package com.example.anime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.mockito.Mockito.when;

@SpringBootTest
class AnimeApplicationTests {
	@Mock
	Pageable pageable;

	@Test
	void contextLoads() {
	}
	@Test
	public void orderByTest() {
		when(pageable.getSort()).thenReturn(Sort.by("score").descending());
		StringBuilder orderBy = new StringBuilder("ORDER BY ");
		for (Sort.Order order : pageable.getSort())
		{
			orderBy.append(order.getProperty()).append(" ").append(order.getDirection()).append(", ");
		}
		orderBy.deleteCharAt(orderBy.length() - 1);
		orderBy.deleteCharAt(orderBy.length() - 1);
		Assertions.assertEquals("ORDER BY score DESC", orderBy.toString());
	}


}
