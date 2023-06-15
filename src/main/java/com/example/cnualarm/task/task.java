package com.example.cnualarm.task;

import org.springframework.scheduling.annotation.Scheduled;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class task {
    @Scheduled(cron = "0 0 13,18 * * ?")
    public void runTask() {
        try {
            // API 요청을 보낼 URL 생성
            URL url = new URL("https://yujinkimvv.pythonanywhere.com/task");

            // HttpURLConnection 객체 생성
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET"); // HTTP 요청 방식 설정

            // API 응답 코드 확인
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("TASK 완료");
            } else {
                System.out.println("API 요청이 실패하였습니다. 응답 코드: " + responseCode);
            }

            // 연결 종료
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
