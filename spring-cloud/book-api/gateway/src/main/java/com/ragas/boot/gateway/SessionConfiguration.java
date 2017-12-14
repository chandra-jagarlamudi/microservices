/**
 * 
 */
package com.ragas.boot.gateway;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * @author Chandra Jagarlamud
 *
 */

@Configuration
@EnableRedisHttpSession(redisFlushMode = RedisFlushMode.IMMEDIATE)
public class SessionConfiguration extends AbstractHttpSessionApplicationInitializer {

}
