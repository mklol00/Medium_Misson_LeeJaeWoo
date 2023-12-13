package com.ll.medium.global.rq.Rq;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.util.URLEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.nio.charset.StandardCharsets;

@Component
@RequestScope
@RequiredArgsConstructor
public class Rq {
    private final HttpServletRequest request;
    private final HttpServletRequest response;

    public String redirect(String url, String msg) {
        msg = URLEncoder.DEFAULT.encode(msg, StandardCharsets.UTF_8);

        StringBuilder sb = new StringBuilder();

        sb.append("redirect:");
        sb.append(url);

        if(msg != null){
            sb.append("?msg=");
            sb.append(msg);
        }

        return sb.toString();


    }
}
