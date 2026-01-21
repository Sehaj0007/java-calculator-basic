package com.example;

public class Calculator {
    
    // Basic arithmetic operations
    public int add(int a, int b) { 
        return a + b; 
    }
    
    public int subtract(int a, int b) { 
        return a - b; 
    }
    
    public int multiply(int a, int b) { 
        return a * b; 
    }
    
    public int divide(int a, int b) { 
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return a / b; 
    }
    
    // Advanced arithmetic operations
    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }
    
    public double squareRoot(double number) {
        if (number < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of negative number");
        }
        return Math.sqrt(number);
    }
    
    public double logarithm(double number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Logarithm is undefined for non-positive numbers");
        }
        return Math.log(number);
    }
    
    public double logarithm(double number, double base) {
        if (number <= 0 || base <= 0 || base == 1) {
            throw new IllegalArgumentException("Invalid arguments for logarithm");
        }
        return Math.log(number) / Math.log(base);
    }
    
    // Trigonometric functions (in radians)
    public double sine(double angle) {
        return Math.sin(angle);
    }
    
    public double cosine(double angle) {
        return Math.cos(angle);
    }
    
    public double tangent(double angle) {
        return Math.tan(angle);
    }
    
    // Conversion between degrees and radians
    public double toRadians(double degrees) {
        return Math.toRadians(degrees);
    }
    
    public double toDegrees(double radians) {
        return Math.toDegrees(radians);
    }
    
    // Absolute value
    public int absolute(int number) {
        return Math.abs(number);
    }
    
    public double absolute(double number) {
        return Math.abs(number);
    }
    
    // Rounding operations
    public long round(double number) {
        return Math.round(number);
    }
    
    public double ceil(double number) {
        return Math.ceil(number);
    }
    
    public double floor(double number) {
        return Math.floor(number);
    }
    
    // Factorial
    public long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        if (n > 20) {
            throw new IllegalArgumentException("Result would exceed Long.MAX_VALUE for n > 20");
        }
        
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    
    // Modulo operation
    public int modulo(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Modulo by zero is not allowed");
        }
        return a % b;
    }
    
    // Percentage calculation
    public double percentage(double value, double percentage) {
        return (value * percentage) / 100.0;
    }
    
    // Memory functions
    private double memory = 0;
    
    public void memoryStore(double value) {
        memory = value;
    }
    
    public double memoryRecall() {
        return memory;
    }
    
    public void memoryClear() {
        memory = 0;
    }
    
    public void memoryAdd(double value) {
        memory += value;
    }
    
    public void memorySubtract(double value) {
        memory -= value;
    }
    
    // Statistics functions
    public double average(double... numbers) {
        if (numbers.length == 0) {
            return 0;
        }
        
        double sum = 0;
        for (double num : numbers) {
            sum += num;
        }
        return sum / numbers.length;
    }
    
    public double max(double... numbers) {
        if (numbers.length == 0) {
            throw new IllegalArgumentException("No numbers provided");
        }
        
        double max = numbers[0];
        for (double num : numbers) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
    
    public double min(double... numbers) {
        if (numbers.length == 0) {
            throw new IllegalArgumentException("No numbers provided");
        }
        
        double min = numbers[0];
        for (double num : numbers) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }
    
    // Constants
    public static final double PI = Math.PI;
    public static final double E = Math.E;
    
    // Utility methods
    public boolean isEven(int number) {
        return number % 2 == 0;
    }
    
    public boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        if (number <= 3) {
            return true;
        }
        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }
        
        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
