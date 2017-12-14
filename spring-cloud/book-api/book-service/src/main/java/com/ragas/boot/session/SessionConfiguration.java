/**
 * 
 */
package com.ragas.boot.session;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * @author Chandra Jagarlamudi
 *
 */

@Configuration
@EnableRedisHttpSession
public class SessionConfiguration extends AbstractHttpSessionApplicationInitializer {

}
