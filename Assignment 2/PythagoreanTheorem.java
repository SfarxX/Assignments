/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		println("Enter values to compute Pythagorean theorem");
		int a = readInt("a: ");
		int b = readInt("b: ");
		double sumOfNumbers = a*a+b*b;
		double c = Math.sqrt(sumOfNumbers);
		println("c = "+c);
	}
}
