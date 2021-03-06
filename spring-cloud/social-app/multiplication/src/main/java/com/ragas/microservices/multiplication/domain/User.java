/**
 * 
 */
package com.ragas.microservices.multiplication.domain;

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
public final class User {
	private final String alias;

    // Empty constructor for JSON (de)serialization
    protected User() {
        alias = null;
    }
}
