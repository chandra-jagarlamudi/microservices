/**
 * 
 */
package com.ragas.microservices.multiplication.domain;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author Chandra Jagarlamudi
 *
 */

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public final class Multiplication {
	
	// Both factors
    private final int factorA;
    private final int factorB;
    private final String today;
	
	// Empty constructor for JSON (de)serialization
    Multiplication() {
        this(0, 0, new Date().toString());
    }	
}
