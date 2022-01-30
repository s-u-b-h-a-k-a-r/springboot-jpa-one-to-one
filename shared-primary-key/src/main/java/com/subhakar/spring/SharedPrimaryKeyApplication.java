package com.subhakar.spring;

import com.github.javafaker.Faker;
import com.subhakar.spring.entity.Post;
import com.subhakar.spring.entity.PostDetails;
import com.subhakar.spring.repository.PostDetailsRepository;
import com.subhakar.spring.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.Optional;

@Slf4j
@SpringBootApplication
public class SharedPrimaryKeyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SharedPrimaryKeyApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(PostRepository postRepository, PostDetailsRepository postDetailsRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Faker faker = new Faker();
                PostDetails postDetails = PostDetails.builder()
                        .createdBy(faker.name().name())
                        .post(Post.builder()
                                .title(faker.harryPotter().book())
                                .build())
                        .createdOn(new Date())
                        .build();
                postDetailsRepository.save(postDetails);

                faker = new Faker();
                postDetails = PostDetails.builder()
                        .createdBy(faker.name().name())
                        .post(Post.builder()
                                .title(faker.harryPotter().book())
                                .build())
                        .createdOn(new Date())
                        .build();
                postDetailsRepository.save(postDetails);

                Optional<Post> optionalPost = postRepository.findById(1L);
                Optional<PostDetails> optionalPostDetails = postDetailsRepository.findById(optionalPost.get().getId());

                log.info(optionalPost.get() + "");
                log.info(optionalPostDetails.get() + "");
            }
        };
    }
}
