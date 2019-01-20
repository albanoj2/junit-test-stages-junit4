package com.dzone.albanoj2.example.junitstages.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.dzone.albanoj2.example.junitstages.domain.Foo;

public class FooRepositoryTest {

	private FooRepository repository;
	
	@Before
	public void setUp() {
		repository = new FooRepository();
	}
	
	@Test
	public void noExistingFoosResultsInFooNotFoundById() {
		assertTrue(repository.findAll().isEmpty());
		assertFalse(repository.findById(1).isPresent());
	}
	
	@Test
	public void existingFooNotMatchingIdResultsInFooNotFoundById() {
		
		long desiredId = 1;
		long differentId = 2;
		
		repository.save(new Foo(differentId));
		
		assertFalse(repository.findById(desiredId).isPresent());
	}
	
	@Test
	public void existingFooMatchingIdResultsInFooFoundById() {
		
		Foo foo = new Foo(1);
		
		repository.save(foo);
		
		Optional<Foo> result = repository.findById(foo.getId());
		
		assertTrue(result.isPresent());
		assertEquals(foo, result.get());
	}
}
