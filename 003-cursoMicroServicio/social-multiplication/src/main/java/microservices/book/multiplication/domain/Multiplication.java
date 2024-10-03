package microservices.book.multiplication.domain;

import lombok.*;

/**
* This class represents a Multiplication in our application.
*/

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Multiplication {

	// Both factors
	private int factorA;
	private int factorB;

	// Empty constructor for JSON (de)serialization
	public Multiplication() {
		this(0, 0);
	}
	// Constructor with parameters
	public Multiplication(int factorA, int factorB) {
		this.factorA = factorA;
		this.factorB = factorB;
	}
}
