package com.sunsw.mercury.utils;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用于处理http请求
 *
 * @author sunsw 2016/4/1
 */
public class HttpUtils {

    private static Logger logger = Logger.getLogger(HttpUtils.class);

    /**
     * 用于发送get或post请求并返回请求结果
     *
     * @param url        请求地址 不能为null
     * @param map        请求参数 可为null
     * @param info       post其他参数
     * @param auName     请求用户名 可为null
     * @param auPassword 请求密码 可为null
     * @param method     请求方法 get或post，默认get
     * @return 请求结果
     */
    public static String send(String url, Map<String, String> map, String info, String auName, String auPassword, String method) throws IOException {
        if (StringUtils.isEmpty(url)) {
            logger.error("url不能为空！");
            return null;
        }
        if (StringUtils.isEmpty(method)) {
            method = "get";
        }

        String result = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpClientContext context = HttpClientContext.create();

        URI uri = URI.create(url);
        HttpHost host = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());

        //用户名和密码都不为null时需要请求验证
        if (auName != null && auPassword != null) {
            //认证提供者
            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(auName, auPassword);
            credsProvider.setCredentials(new AuthScope(host.getHostName(), AuthScope.ANY_PORT), credentials);
            //缓存
            AuthCache authCache = new BasicAuthCache();
            authCache.put(host, new BasicScheme());
            //提前填充认证信息缓存到上下文中，以这个上下文执行的方法，就会使用抢先认证。
            context.setAuthCache(authCache);
            context.setCredentialsProvider(credsProvider);
        }

        //参数
        List<NameValuePair> params = new ArrayList<>();
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        try {
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(15000).build();//设置请求和传输超时时间
            if (method.equalsIgnoreCase("get")) {
                String queryString = URLEncodedUtils.format(params, Consts.UTF_8);
                HttpGet get = new HttpGet(uri.getPath() + "?" + (uri.getQuery() == null ? "" : uri.getQuery()) + "&" + queryString);
                get.setConfig(requestConfig);
                response = httpclient.execute(host, get, context);
            } else if (method.equalsIgnoreCase("post")) {
                HttpPost post = new HttpPost(uri.getPath() + "?" + (uri.getQuery() == null ? "" : uri.getQuery()));
                post.setConfig(requestConfig);
                if (!params.isEmpty()) {
                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
                    post.setEntity(entity);
                } else if (!StringUtils.isEmpty(info)) {
                    StringEntity entity = new StringEntity(info, Consts.UTF_8);
                    entity.setContentType("application/json");
                    post.setEntity(entity);
                }
                response = httpclient.execute(host, post, context);
            } else {
                logger.error("method必须为get或post！");
                return null;
            }
            //if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, Consts.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.error(e.getLocalizedMessage());
            throw e;
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                LogUtils.error(e.getLocalizedMessage());
                throw e;
            }
        }
        return result;
    }

    public static String doGet(String url, Map<String, String> params, String auName, String auPassword) throws IOException {
        return HttpUtils.send(url, params, null, auName, auPassword, "get");
    }

    public static String doGet(String url, String auName, String auPassword) throws IOException {
        return HttpUtils.send(url, null, null, auName, auPassword, "get");
    }

    public static String doGet(String url, Map<String, String> params) throws IOException {
        return HttpUtils.send(url, params, null, null, null, "get");
    }

    public static String doGet(String url) throws IOException {
        return HttpUtils.send(url, null, null, null, null, "get");
    }

    public static String doPost(String url, Map<String, String> params, String auName, String auPassword) throws IOException {
        return HttpUtils.send(url, params, null, auName, auPassword, "post");
    }

    public static String doPost(String url, String auName, String auPassword) throws IOException {
        return HttpUtils.send(url, null, null, auName, auPassword, "post");
    }

    public static String doPost(String url, Map<String, String> params) throws IOException {
        return HttpUtils.send(url, params, null, null, null, "post");
    }

    public static String doPost(String url) throws IOException {
        return HttpUtils.send(url, null, null, null, null, "post");
    }

    public static String doPost(String url, String info, String auName, String auPassword) throws IOException {
        return HttpUtils.send(url, null, info, auName, auPassword, "post");
    }

    public static String doPost(String url, String info) throws IOException {
        return HttpUtils.send(url, null, info, null, null, "post");
    }
}
