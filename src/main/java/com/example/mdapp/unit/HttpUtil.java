package com.example.mdapp.unit;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class HttpUtil {

    // private static Logger logger =
    // Logger.getLogger(String.valueOf(HttpUtil.class));throws IOException, InterruptedException
    // protected Logger logger = LoggerFactory.getLogger(HttpUtils.class);



    /**
     * get请求
     *
     * @return
     */
    public static String doGet(String url)  {
        String result= "";
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
    
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    
            System.out.println(response.body());
            result = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

      return result;
    }

    /**
     * post (json)
     *
     * @param url
     * @param json
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public String doPostJson(String url, String json) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        // add json header
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create(url))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
        System.out.println(response.statusCode());

        // print response body
        System.out.println(response.body());

        return response.body();
    }

    /**
     * post (form)
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public String doPostForm(String url, Map<Object, Object> params) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newBuilder()
                .version(Version.HTTP_2) //Http协议的版本(HTTP 1.1或者HTTP 2.0)，默认是2.0
                .followRedirects(Redirect.NORMAL) //是否遵从服务器发出的重定向
                .connectTimeout(Duration.ofSeconds(20)) //连接超时时间
                //.proxy(ProxySelector.of(new InetSocketAddress("proxy.example.com", 80))) //代理
                //.authenticator(Authenticator.getDefault())//身份认证
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .POST(ofFormData(params))
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(20))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/x-www-form-urlencoded").build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
        System.out.println(response.statusCode());

        // print response body
        System.out.println(response.body());
        return response.body();
    }

    public static InputStream GetFile(String url) throws IOException, InterruptedException, ExecutionException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        var result = client.sendAsync(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(HttpResponse::body);

        return result.get();
    }

    /**
     * @return
     * @description: 从服务器获得一个输入流
     */
    public static InputStream getInputStream(String urlPath) {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(urlPath);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            // 设置网络连接超时时间
            httpURLConnection.setConnectTimeout(3000);
            // 设置应用程序要从网络连接读取数据
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            System.out.println("responseCode is:" + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 从服务器返回一个输入流
                inputStream = httpURLConnection.getInputStream();
            } else {
                inputStream = httpURLConnection.getErrorStream();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    // Sample: 'password=123&custom=secret&username=abc&ts=1570704369823'
    public static HttpRequest.BodyPublisher ofFormData(Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }

}
