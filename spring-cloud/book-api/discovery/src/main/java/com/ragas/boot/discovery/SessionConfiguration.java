/**
 * 
 */
package com.ragas.boot.discovery;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author Chandra Jagarlamudi
 *
 */
@EnableRedisHttpSession
public class SessionConfiguration extends AbstractSecurityWebApplicationInitializer {
}
