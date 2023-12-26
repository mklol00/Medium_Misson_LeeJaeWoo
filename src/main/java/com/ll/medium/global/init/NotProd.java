package com.ll.medium.global.init;

import com.ll.medium.domain.member.member.entity.Member;
import com.ll.medium.domain.member.member.service.MemberService;
import com.ll.medium.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import java.util.stream.IntStream;

@Configuration
@Profile("!prod")
@Slf4j
@RequiredArgsConstructor
public class NotProd {
    private final MemberService memberService;
    private final PostService postService;

    @Bean
    @Order(3)
    public ApplicationRunner initNotProd() {
        return args -> {
            if (memberService.findByUsername("user1").isPresent()) return;
            Member memberUser1 = memberService.join("user1", "1234").getData();
            Member memberUser2 = memberService.join("user2", "1234").getData();
            Member memberUser3 = memberService.join("user3", "1234").getData();
            Member memberUser4 = memberService.join("user4", "1234").getData();

            postService.write(memberUser1, "제목 1", "내용1", true);
            postService.write(memberUser1, "제목 2", "내용2", true);
            postService.write(memberUser1, "제목 3", "내용3", false);
            postService.write(memberUser1, "제목 4", "내용4", true);

            postService.write(memberUser2, "제목 5", "내용5", true);
            postService.write(memberUser2, "제목 6", "내용6", false);

            IntStream.rangeClosed(7, 50).forEach(i -> {
                postService.write(memberUser3, "제목 " + i, "내용 " + i, true);
            });

        };
    }
}