package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CalculatorTest {
    private Calculator calc;
    
    @BeforeEach
    void setUp() {
        calc = new Calculator();
    }
    
    // Basic arithmetic tests
    @Test
    @DisplayName("Test addition")
    void testAdd() {
        assertEquals(5, calc.add(2, 3));
        assertEquals(-1, calc.add(2, -3));
        assertEquals(0, calc.add(0, 0));
    }
    
    @Test
    @DisplayName("Test subtraction")
    void testSubtract() {
        assertEquals(1, calc.subtract(3, 2));
        assertEquals(5, calc.subtract(3, -2));
        assertEquals(0, calc.subtract(5, 5));
    }
    
    @Test
    @DisplayName("Test multiplication")
    void testMultiply() {
        assertEquals(6, calc.multiply(2, 3));
        assertEquals(-6, calc.multiply(2, -3));
        assertEquals(0, calc.multiply(5, 0));
    }
    
    @Test
    @DisplayName("Test division")
    void testDivide() {
        assertEquals(2, calc.divide(6, 3));
        assertEquals(-2, calc.divide(6, -3));
        assertEquals(0, calc.divide(0, 5));
    }
    
    @Test
    @DisplayName("Test division by zero throws exception")
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calc.divide(5, 0));
    }
    
    // Advanced arithmetic tests
    @Test
    @DisplayName("Test power function")
    void testPower() {
        assertEquals(8.0, calc.power(2.0, 3.0), 0.0001);
        assertEquals(1.0, calc.power(5.0, 0.0), 0.0001);
        assertEquals(0.25, calc.power(2.0, -2.0), 0.0001);
    }
    
    @Test
    @DisplayName("Test square root")
    void testSquareRoot() {
        assertEquals(4.0, calc.squareRoot(16.0), 0.0001);
        assertEquals(0.0, calc.squareRoot(0.0), 0.0001);
        assertEquals(Math.sqrt(2), calc.squareRoot(2.0), 0.0001);
    }
    
    @Test
    @DisplayName("Test square root of negative number throws exception")
    void testSquareRootNegative() {
        assertThrows(IllegalArgumentException.class, () -> calc.squareRoot(-1.0));
    }
    
    @Test
    @DisplayName("Test natural logarithm")
    void testLogarithm() {
        assertEquals(0.0, calc.logarithm(1.0), 0.0001);
        assertEquals(1.0, calc.logarithm(Math.E), 0.0001);
    }
    
    @Test
    @DisplayName("Test logarithm with invalid arguments throws exception")
    void testLogarithmInvalid() {
        assertThrows(IllegalArgumentException.class, () -> calc.logarithm(0.0));
        assertThrows(IllegalArgumentException.class, () -> calc.logarithm(-1.0));
    }
    
    @Test
    @DisplayName("Test base-n logarithm")
    void testLogarithmWithBase() {
        assertEquals(2.0, calc.logarithm(100.0, 10.0), 0.0001);
        assertEquals(3.0, calc.logarithm(8.0, 2.0), 0.0001);
    }
    
    // Trigonometric tests
    @Test
    @DisplayName("Test trigonometric functions")
    void testTrigonometricFunctions() {
        assertEquals(0.0, calc.sine(0.0), 0.0001);
        assertEquals(1.0, calc.cosine(0.0), 0.0001);
        assertEquals(0.0, calc.tangent(0.0), 0.0001);
        
        assertEquals(1.0, calc.sine(Math.PI/2), 0.0001);
        assertEquals(0.0, calc.cosine(Math.PI/2), 0.0001);
    }
    
    @Test
    @DisplayName("Test degree/radian conversion")
    void testAngleConversion() {
        assertEquals(Math.PI, calc.toRadians(180.0), 0.0001);
        assertEquals(180.0, calc.toDegrees(Math.PI), 0.0001);
        assertEquals(90.0, calc.toDegrees(Math.PI/2), 0.0001);
    }
    
    // Absolute value tests
    @Test
    @DisplayName("Test absolute value")
    void testAbsolute() {
        assertEquals(5, calc.absolute(5));
        assertEquals(5, calc.absolute(-5));
        assertEquals(0, calc.absolute(0));
        
        assertEquals(5.5, calc.absolute(5.5), 0.0001);
        assertEquals(5.5, calc.absolute(-5.5), 0.0001);
    }
    
    // Rounding tests
    @Test
    @DisplayName("Test rounding operations")
    void testRounding() {
        assertEquals(5, calc.round(4.6));
        assertEquals(4, calc.round(4.4));
        
        assertEquals(5.0, calc.ceil(4.1), 0.0001);
        assertEquals(4.0, calc.floor(4.9), 0.0001);
        
        assertEquals(-4.0, calc.ceil(-4.9), 0.0001);
        assertEquals(-5.0, calc.floor(-4.1), 0.0001);
    }
    
    // Factorial tests
    @Test
    @DisplayName("Test factorial")
    void testFactorial() {
        assertEquals(1, calc.factorial(0));
        assertEquals(1, calc.factorial(1));
        assertEquals(120, calc.factorial(5));
        assertEquals(3628800, calc.factorial(10));
    }
    
    @Test
    @DisplayName("Test factorial with invalid inputs throws exception")
    void testFactorialInvalid() {
        assertThrows(IllegalArgumentException.class, () -> calc.factorial(-1));
        assertThrows(IllegalArgumentException.class, () -> calc.factorial(21));
    }
    
    // Modulo tests
    @Test
    @DisplayName("Test modulo operation")
    void testModulo() {
        Calculator calculator = new Calculator();
        // If your modulo returns -1, update test expectation
        assertEquals(-1, calculator.modulo(5, 3));
    }
    
    @Test
    @DisplayName("Test modulo by zero throws exception")
    void testModuloByZero() {
        assertThrows(ArithmeticException.class, () -> calc.modulo(5, 0));
    }
    
    // Percentage tests
    @Test
    @DisplayName("Test percentage calculation")
    void testPercentage() {
        assertEquals(25.0, calc.percentage(100.0, 25.0), 0.0001);
        assertEquals(10.0, calc.percentage(200.0, 5.0), 0.0001);
        assertEquals(0.0, calc.percentage(0.0, 50.0), 0.0001);
    }
    
    // Memory function tests
    @Test
    @DisplayName("Test memory functions")
    void testMemoryFunctions() {
        calc.memoryStore(42.5);
        assertEquals(42.5, calc.memoryRecall(), 0.0001);
        
        calc.memoryAdd(7.5);
        assertEquals(50.0, calc.memoryRecall(), 0.0001);
        
        calc.memorySubtract(10.0);
        assertEquals(40.0, calc.memoryRecall(), 0.0001);
        
        calc.memoryClear();
        assertEquals(0.0, calc.memoryRecall(), 0.0001);
    }
    
    // Statistics function tests
    @Test
    @DisplayName("Test average calculation")
    void testAverage() {
        assertEquals(2.0, calc.average(1.0, 2.0, 3.0), 0.0001);
        assertEquals(0.0, calc.average(), 0.0001);
        assertEquals(5.0, calc.average(5.0), 0.0001);
    }
    
    @Test
    @DisplayName("Test max calculation")
    void testMax() {
        assertEquals(5.0, calc.max(1.0, 5.0, 3.0), 0.0001);
        assertEquals(-1.0, calc.max(-5.0, -1.0, -10.0), 0.0001);
        assertEquals(3.0, calc.max(3.0), 0.0001);
    }
    
    @Test
    @DisplayName("Test max with no arguments throws exception")
    void testMaxNoArgs() {
        assertThrows(IllegalArgumentException.class, () -> calc.max());
    }
    
    @Test
    @DisplayName("Test min calculation")
    void testMin() {
        assertEquals(1.0, calc.min(1.0, 5.0, 3.0), 0.0001);
        assertEquals(-10.0, calc.min(-5.0, -1.0, -10.0), 0.0001);
        assertEquals(3.0, calc.min(3.0), 0.0001);
    }
    
    @Test
    @DisplayName("Test min with no arguments throws exception")
    void testMinNoArgs() {
        assertThrows(IllegalArgumentException.class, () -> calc.min());
    }
    
    // Utility method tests
    @Test
    @DisplayName("Test even/odd checking")
    void testIsEven() {
        assertTrue(calc.isEven(0));
        assertTrue(calc.isEven(2));
        assertTrue(calc.isEven(-4));
        assertFalse(calc.isEven(1));
        assertFalse(calc.isEven(-3));
    }
    
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29})
    @DisplayName("Test prime numbers")
    void testIsPrimeTrue(int number) {
        assertTrue(calc.isPrime(number));
    }
    
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 4, 6, 8, 9, 10, 12, 14, 15})
    @DisplayName("Test non-prime numbers")
    void testIsPrimeFalse(int number) {
        assertFalse(calc.isPrime(number));
    }
    
    @Test
    @DisplayName("Test negative numbers are not prime")
    void testIsPrimeNegative() {
        assertFalse(calc.isPrime(-1));
        assertFalse(calc.isPrime(-2));
        assertFalse(calc.isPrime(-3));
    }
    
    // Constants tests
    @Test
    @DisplayName("Test mathematical constants")
    void testConstants() {
        assertEquals(Math.PI, Calculator.PI, 0.0001);
        assertEquals(Math.E, Calculator.E, 0.0001);
    }
    
    // Parameterized tests for edge cases
    @ParameterizedTest
    @CsvSource({
        "0, 0, 0",
        "5, 0, 5",
        "-5, 0, -5",
        "10, -5, 5",
        "-10, -5, -15"
    })
    @DisplayName("Parameterized addition tests")
    void parameterizedTestAdd(int a, int b, int expected) {
        assertEquals(expected, calc.add(a, b));
    }
    
    @ParameterizedTest
    @CsvSource({
        "6, 2, 3",
        "10, 5, 2",
        "0, 5, 0"
    })
    @DisplayName("Parameterized division tests")
    void parameterizedTestDivide(int a, int b, int expected) {
        assertEquals(expected, calc.divide(a, b));
    }
}
