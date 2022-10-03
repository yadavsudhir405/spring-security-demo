package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Spliterator;

import static org.junit.jupiter.api.Assertions.*;

class FlightServiceTest {

    @Test
    void findAll() {
        Iterable<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Spliterator<Integer> spliterator = numbers.spliterator();
        Spliterator<Integer> integerSpliterator = spliterator.trySplit();
        spliterator.forEachRemaining(System.out::println);
        System.out.println("((((((((((((((((");
        integerSpliterator.forEachRemaining(System.out::println);
    }
}
