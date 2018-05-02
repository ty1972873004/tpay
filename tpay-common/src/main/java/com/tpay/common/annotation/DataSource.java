package com.tpay.common.annotation;

import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * @desc 
 * @author tuyong
 * @since 2017-11-16
 * @version 1.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
@Order(0)
public @interface DataSource {
    String value();
}
