package com.shop.shopping.config;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

public class OrdersDialect extends AbstractDialect implements IExpressionObjectDialect {
    private static final OrdersExpressionObjectFactory ORDERS_EXPRESSION_OBJECT_FACTORY = new OrdersExpressionObjectFactory();

    public OrdersDialect() {
        super("orders");
    }
    
    protected OrdersDialect(String name) {
        super(name);
    }
    
    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        return this.ORDERS_EXPRESSION_OBJECT_FACTORY;
    }
}
