package com.shop.common.constant;

import java.nio.charset.Charset;

/**
 * 常量配置
 */
public interface Constant {
    /**
     * 用于文件服务器验证token
     */
    String SYS_FILE_TOKEN = "Coffer";

    /**
     * 文件服务器token对应的key值
     */
    String SYS_FILE_AUTH_KEY = "token";

    /**
     * 用户会话
     */
    String USER_SESSION = "user";

    /**
     * 默认编码
     */
    Charset UTF8 = Charset.forName("utf-8");
}
