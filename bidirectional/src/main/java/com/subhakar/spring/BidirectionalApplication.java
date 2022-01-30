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
public class BidirectionalApplication {
    public static void main(String[] args) {
        SpringApplication.run(BidirectionalApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(PostRepository postRepository, PostDetailsRepository postDetailsRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Faker faker = new Faker();

                Post post = Post.builder()
                        .title(faker.harryPotter().book())
                        .build();
                postRepository.save(post);

                PostDetails postDetails = PostDetails.builder()
                        .createdBy(faker.name().name())
                        .post(postRepository.findById(1L).get())
                        .createdOn(new Date())
                        .build();
                postDetailsRepository.save(postDetails);

                Optional<Post> optionalPost = postRepository.findById(1L);
                Optional<PostDetails> optionalPostDetails = postDetailsRepository.findById(2L);

                log.info(optionalPost.get() + ":" + optionalPost.get().getPostDetails());
                log.info(optionalPostDetails.get() + ":" + optionalPostDetails.get().getPost());

            }
        };
    }
}
