package com.shop.shopping.config;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.expression.IExpressionObjectFactory;

import com.shop.shopping.util.OrderStatusResolver;

public class OrdersExpressionObjectFactory implements IExpressionObjectFactory {
    public static final String ORDERS_RESOLVER_EXPRESSION_OBJECT_NAME = "orders";
    
    private static final OrderStatusResolver ORDER_STATUS_RESOLVER = new OrderStatusResolver();
    
    public static final Set<String> ALL_EXPRESSION_OBJECT_NAMES;
    
    static {
        final Set<String> allExpressionObjectNames = new LinkedHashSet<String>();
        allExpressionObjectNames.add(ORDERS_RESOLVER_EXPRESSION_OBJECT_NAME);
        ALL_EXPRESSION_OBJECT_NAMES = Collections.unmodifiableSet(allExpressionObjectNames);
    }
    
    @Override
    public Set<String> getAllExpressionObjectNames() {
        return ALL_EXPRESSION_OBJECT_NAMES;
    }
    
    @Override
    public Object buildObject(IExpressionContext context, String expressionObjectName) {
        return ORDERS_RESOLVER_EXPRESSION_OBJECT_NAME.equals(expressionObjectName) ? ORDER_STATUS_RESOLVER : null;
    }
    
    @Override
    public boolean isCacheable(String expressionObjectName) {
        return expressionObjectName != null && ORDERS_RESOLVER_EXPRESSION_OBJECT_NAME.equals(expressionObjectName);
    }
}
