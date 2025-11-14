package com;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

/**
 * Unit tests for the App class
 */
public class AppTest {
    
    private static final double DELTA = 0.001; // For float comparisons
    
    @Before
    public void setUp() {
        System.out.println("Setting up test...");
    }
    
    @After
    public void tearDown() {
        System.out.println("Test completed.");
    }
    
    /**
     * Test the add method with positive integers
     */
    @Test
    public void testAddPositiveIntegers() {
        int result = App.add(2, 3);
        assertEquals("2 + 3 should equal 5", 5, result);
    }
    
    /**
     * Test the add method with negative integers
     */
    @Test
    public void testAddNegativeIntegers() {
        int result = App.add(-5, -3);
        assertEquals("-5 + -3 should equal -8", -8, result);
    }
    
    /**
     * Test the add method with mixed positive and negative integers
     */
    @Test
    public void testAddMixedIntegers() {
        int result = App.add(10, -5);
        assertEquals("10 + -5 should equal 5", 5, result);
    }
    
    /**
     * Test the add method with zero
     */
    @Test
    public void testAddWithZero() {
        int result = App.add(5, 0);
        assertEquals("5 + 0 should equal 5", 5, result);
    }
    
    /**
     * Test the add method with large integers
     */
    @Test
    public void testAddLargeIntegers() {
        int result = App.add(1000000, 2000000);
        assertEquals("1000000 + 2000000 should equal 3000000", 3000000, result);
    }
    
    /**
     * Test the add method with positive floats
     */
    @Test
    public void testAddPositiveFloats() {
        float result = App.add(7.5f, 3.2f);
        assertEquals("7.5 + 3.2 should equal 10.7", 10.7f, result, DELTA);
    }
    
    /**
     * Test the add method with negative floats
     */
    @Test
    public void testAddNegativeFloats() {
        float result = App.add(-2.5f, -1.5f);
        assertEquals("-2.5 + -1.5 should equal -4.0", -4.0f, result, DELTA);
    }
    
    /**
     * Test the add method with mixed positive and negative floats
     */
    @Test
    public void testAddMixedFloats() {
        float result = App.add(5.5f, -2.5f);
        assertEquals("5.5 + -2.5 should equal 3.0", 3.0f, result, DELTA);
    }
    
    /**
     * Test the add method with zero float
     */
    @Test
    public void testAddFloatWithZero() {
        float result = App.add(3.14f, 0.0f);
        assertEquals("3.14 + 0.0 should equal 3.14", 3.14f, result, DELTA);
    }
    
    /**
     * Test the subtract method with positive integers
     */
    @Test
    public void testSubtractPositiveIntegers() {
        int result = App.subtract(10, 3);
        assertEquals("10 - 3 should equal 7", 7, result);
    }
    
    /**
     * Test the subtract method with negative integers
     */
    @Test
    public void testSubtractNegativeIntegers() {
        int result = App.subtract(-5, -3);
        assertEquals("-5 - -3 should equal -2", -2, result);
    }
    
    /**
     * Test the subtract method with mixed integers
     */
    @Test
    public void testSubtractMixedIntegers() {
        int result = App.subtract(5, -3);
        assertEquals("5 - -3 should equal 8", 8, result);
    }
    
    /**
     * Test the subtract method with zero
     */
    @Test
    public void testSubtractWithZero() {
        int result = App.subtract(10, 0);
        assertEquals("10 - 0 should equal 10", 10, result);
    }
    
    /**
     * Test the subtract method resulting in zero
     */
    @Test
    public void testSubtractResultingInZero() {
        int result = App.subtract(5, 5);
        assertEquals("5 - 5 should equal 0", 0, result);
    }
    
    /**
     * Test the subtract method with large integers
     */
    @Test
    public void testSubtractLargeIntegers() {
        int result = App.subtract(2000000, 1000000);
        assertEquals("2000000 - 1000000 should equal 1000000", 1000000, result);
    }
}
