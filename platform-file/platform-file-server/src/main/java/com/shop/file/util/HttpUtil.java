package com.shop.file.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.shop.common.constant.Constant;
import com.shop.file.model.FileInfo;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * 封装http请求工具
 */
public class HttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 直接返回内容
     * @param url
     * @param params
     * @return
     */
    public static String postJson(String url, String json) {
        String result = null;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
//        List<NameValuePair> nameValuePairs = new ArrayList<>();
//        for (Map.Entry<String, String> e : params.entrySet()) {
//            nameValuePairs.add(new BasicNameValuePair(e.getKey(), e.getValue()));
//        }
//        postJson.setEntity(new UrlEncodedFormEntity(nameValuePairs, Constant.UTF8));
        post.setHeader(new BasicHeader("Content-Type", "application/json"));
        post.setEntity(new StringEntity(json, Constant.UTF8));

        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
            } else {
                result = "服务器错误:" + statusCode;
            }
        } catch (IOException e) {
            logger.error("请求错误{},{}", url, json ,e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws JsonProcessingException {
        HashMap<String, String> map = Maps.newHashMap();
        map.put("id", UUID.randomUUID().toString());
        map.put("name", "文件名");
        map.put("path", "/dhsakjdhskad/");
        map.put("uploadTime", "2018-03-12");

        FileInfo fileInfo = new FileInfo();
        fileInfo.setId(UUID.randomUUID().toString());
        fileInfo.setName("文件名");
        fileInfo.setPath("/hhhhh/2313123");
        fileInfo.setUploadTime(new Date());

        String s = new ObjectMapper().writeValueAsString(fileInfo);
        System.out.println(postJson("http://192.168.199.215:10086/user/callback", s));
    }
}
