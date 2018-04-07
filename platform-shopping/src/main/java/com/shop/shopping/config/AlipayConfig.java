package com.shop.shopping.config;

public interface AlipayConfig {
    /**
     * URL	支付宝网关（固定）	https://openapi.alipay.com/gateway.do
     APPID	APPID 即创建应用后生成	获取见上面创建应用
     APP_PRIVATE_KEY	开发者私钥，由开发者自己生成	获取详见上面配置密钥
     FORMAT	参数返回格式，只支持json	json（固定）
     CHARSET	编码集，支持GBK/UTF-8	开发者根据实际工程编码配置
     ALIPAY_PUBLIC_KEY	支付宝公钥，由支付宝生成	获取详见上面配置密钥
     SIGN_TYPE	商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2	RSA2
     */
    String URL = "https://openapi.alipaydev.com/gateway.do";
    String APP_ID = "";
    String APP_PRIVATE_KEY = "";
    String FORMAT = "json";
    String CHARSET = "UTF-8";
    String ALIPAY_PUBLIC_KEY = "";
    String SIGN_TYPE = "RSA2";
    
    /**
     * 相对项目根路径
     */
    String NOTIFY_URL = "/alipay/alipayNotify";
    String RETURN_URL = "/public/home.html";
}
