package com.webtooni.webtooniverse.global.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkPostRepository;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkPostRepositoryImpl;
import java.io.IOException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TalkPostRepository postRepository;

    @RabbitListener(queues = "CREATE_TALKPOST_QUEUE")
    public void handler(String message) throws IOException {
        TalkPost post =objectMapper.readValue(message,TalkPost.class);
        postRepository.save(post);
    }
}
