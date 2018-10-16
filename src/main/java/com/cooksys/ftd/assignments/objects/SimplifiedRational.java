package com.cooksys.ftd.assignments.objects;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SimplifiedRational implements IRational {
	private int numerator;
	private int denominator;
	
    /**
     * Determines the greatest common denominator for the given values
     *
     * @param a the first value to consider
     * @param b the second value to consider
     * @return the greatest common denominator, or shared factor, of `a` and `b`
     * @throws IllegalArgumentException if a <= 0 or b < 0
     */
    public static int gcd(int a, int b) throws IllegalArgumentException {
        if(a <= 0 || b < 0) {
        	throw new IllegalArgumentException();
        }
        //greatest common denominator
        int gcd;
        int num;
        int remain;
        
        if(a > b) {
        	num = a;
        	gcd = b;
        } else {
        	num = b;
        	gcd = a;
        }
        remain = num % gcd;

        while(remain != 0) {
        	num = gcd;
        	gcd = remain;
            remain = num % gcd;
        }
        
        return gcd;
    }
    /**
     * Simplifies the numerator and denominator of a rational value.
     * <p>
     * For example:
     * `simplify(10, 100) = [1, 10]`
     * or:
     * `simplify(0, 10) = [0, 1]`
     *
     * @param numerator   the numerator of the rational value to simplify
     * @param denominator the denominator of the rational value to simplify
     * @return a two element array representation of the simplified numerator and denominator
     * @throws IllegalArgumentException if the given denominator is 0
     */
    public static int[] simplify(int numerator, int denominator) throws IllegalArgumentException {
        if(denominator == 0) {
        	throw new IllegalArgumentException();
        }
        int[] simp = new int[2];
        int d = gcd(Math.abs(numerator),Math.abs(denominator));
        
        if(numerator == 0) {
        	simp[0] = 0;
        	simp[1] = 1;
        } else {
        	simp[0] = numerator/d;
        	simp[1] = denominator/d;
    	}
        
        return simp;
    }

    /**
     * Constructor for rational values of the type:
     * <p>
     * `numerator / denominator`
     * <p>
     * Simplification of numerator/denominator pair should occur in this method.
     * If the numerator is zero, no further simplification can be performed
     *
     * @param numerator   the numerator of the rational value
     * @param denominator the denominator of the rational value
     * @throws IllegalArgumentException if the given denominator is 0
     */
    public SimplifiedRational(int numerator, int denominator) throws IllegalArgumentException {
        if(denominator == 0) {
        	throw new IllegalArgumentException();
        }
        int[] simp;
        if(numerator != 0) {
        	simp = simplify(numerator,denominator);
        	this.numerator = simp[0];
        	this.denominator = simp[1];
        	} else {
        	this.numerator = numerator;
        	this.denominator = denominator;
        }
    }

    /**
     * @return the numerator of this rational number
     */
    @Override
    public int getNumerator() {
        return this.numerator;
    }

    /**
     * @return the denominator of this rational number
     */
    @Override
    public int getDenominator() {
        return this.denominator;
    }

    /**
     * Specializable constructor to take advantage of shared code between Rational and SimplifiedRational
     * <p>
     * Essentially, this method allows us to implement most of IRational methods directly in the interface while
     * preserving the underlying type
     *
     * @param numerator   the numerator of the rational value to construct
     * @param denominator the denominator of the rational value to construct
     * @return the constructed rational value (specifically, a SimplifiedRational value)
     * @throws IllegalArgumentException if the given denominator is 0
     */
    @Override
    public SimplifiedRational construct(int numerator, int denominator) throws IllegalArgumentException {
        if(denominator == 0) {
        	throw new IllegalArgumentException();
        }
        return new SimplifiedRational(numerator, denominator);
    }

    /**
     * @param obj the object to check this against for equality
     * @return true if the given obj is a rational value and its
     * numerator and denominator are equal to this rational value's numerator and denominator,
     * false otherwise
     */
    @Override
    public boolean equals(Object obj) {
    	if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimplifiedRational other = (SimplifiedRational) obj;
//		System.out.println("Equals:" + ((other.getNumerator() == this.numerator) && (other.getDenominator() == this.denominator)));
//		System.out.println("    other: " + other.getNumerator() + "/" + other.getDenominator());
//		System.out.println("    this: " + this.numerator + "/" + this.denominator);
        return ((other.getNumerator() == this.numerator) && (other.getDenominator() == this.denominator));
    }

    /**
     * If this is positive, the string should be of the form `numerator/denominator`
     * <p>
     * If this is negative, the string should be of the form `-numerator/denominator`
     *
     * @return a string representation of this rational value
     */
    @Override
    public String toString() {
    	String s = "";
    	if ((numerator < 0 && denominator > 0) || (denominator < 0 && numerator > 0)) {
    		s = "-";
    	}
        return (s + Math.abs(numerator) + "/" + Math.abs(denominator));
    }
}
