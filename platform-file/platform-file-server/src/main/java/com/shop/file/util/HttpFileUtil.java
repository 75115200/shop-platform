package com.shop.file.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shop.common.constant.Constant;
import com.shop.file.model.FileInfo;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.shop.common.constant.Constant.FILE_KEY;

/**
 * 封装http请求工具
 */
public class HttpFileUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpFileUtil.class);

    /**
     * 文件上传回调
     * @param url 回调url
     * @param params 原表单数据
     * @param files 上传后的文件数据
     * @return String
     */
    public static String callback(String url, Map<String, String[]> params, List<FileInfo> files) {
        String result = null;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        List<NameValuePair> nameValuePairs = new ArrayList<>();

        // 处理回调表单数据
        for (Map.Entry<String, String[]> e : params.entrySet()) {
            for (String s : e.getValue()) {
                nameValuePairs.add(new BasicNameValuePair(e.getKey(), s));
            }
        }

        // 处理文件数据
        for (FileInfo f : files) {
            nameValuePairs.add(new BasicNameValuePair(FILE_KEY, f.getId()));
        }

        post.setEntity(new UrlEncodedFormEntity(nameValuePairs, Constant.UTF8));

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
            logger.error("请求错误{},{}", url, params ,e);
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
//        HashMap<String, String> map = Maps.newHashMap();
//        map.put("id", UUID.randomUUID().toString());
//        map.put("name", "文件名");
//        map.put("path", "/dhsakjdhskad/");
//        map.put("uploadTime", "2018-03-12");
//
//        FileInfo fileInfo = new FileInfo();
//        fileInfo.setId(UUID.randomUUID().toString());
//        fileInfo.setName("文件名");
//        fileInfo.setPath("/hhhhh/2313123");
//        fileInfo.setUploadTime(new Date());
//
//        String s = new ObjectMapper().writeValueAsString(fileInfo);
//        System.out.println(callback("http://192.168.199.215:10086/user/callback", s));

        FileInfo info = new FileInfo();
    }
}
