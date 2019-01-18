package com.example.demoActuator;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class test {

    public static void main(String[] args) {
        System.out.println(BCrypt.hashpw("taki", BCrypt.gensalt(4)));
        String requestBody = "{\\r\\n  \\\"accountBranchCode\\\": \\\"00109\\\",\\r\\n  \\\"accountKey\\\": \\\"11\\\",\\r\\n  \\\"accountNumber\\\": \\\"06111111111\\\",\\r\\n  \\\"creditDebit\\\": \\\"\\\",\\r\\n  \\\"customerNumber\\\": \\\"12345678\\\",\\r\\n  \\\"fromAmount\\\": \\\"\\\",\\r\\n  \\\"fromDate\\\": \\\"2018-02-12\\\",\\r\\n  \\\"numberOfTransactions\\\": \\\"\\\",\\r\\n  \\\"toAmount\\\": \\\"\\\",\\r\\n  \\\"toDate\\\": \\\"2018-12-12\\\",\\r\\n  \\\"transactionDescription\\\": \\\"\\\",\\r\\n  \\\"transactionId\\\": \\\"\\\"\\r\\n}";
        String body = "{\"accountBranchCode\": \"00109\",\"accountKey\": \"11\",\"accountNumber\": \"06111111111\",\"creditDebit\": \"\",\"customerNumber\": \"12345678\",\"fromAmount\": \"\",\"fromDate\": \"2018-02-12\",\"numberOfTransactions\": \"\",\"toAmount\": \"\",\"toDate\": \"2018-12-12\",\"transactionDescription\": \"\",\"transactionId\": \"\"}";
        String bodyNotif = "{ \"clients_list\" :\"[12345678,11111111]\",\"message_category\": \"salim\",\"message_title\":\"Notif from pc salim\",\"message_body\": \"Notif from pc salim\",\"priority\":\"1\"}";
        post("http://localhost:2020/private/transactionList", body);
        //    post("http://10.20.0.96:10080/uib/adapters/NotificationsAdapter/pushByClientList",bodyNotif);

    }

    public static void post(String completeUrl, String body) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(completeUrl);
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("Authorization", "Basic dGVzdDp0ZXN0");
        try {
            StringEntity stringEntity = new StringEntity(body);
            httpPost.getRequestLine();
            httpPost.setEntity(stringEntity);

            HttpResponse response = httpClient.execute(httpPost);
            System.err.println("response code  " + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                System.err.println("hello");
                result.append(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
