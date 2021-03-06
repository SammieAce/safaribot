package com.safaribot;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.configuration.BotConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /*share bot instance with all views*/
    @Bean
    public Bot alice() {
        return new Bot(BotConfiguration.builder()
                        .name("alice")
                        .path("src/main/resources")
                        .build()
        );
    }
    @Bean
    public ScheduledExecutorService executorService() {
        return Executors.newScheduledThreadPool(2);
    }

    private final Chat chatSession;

/* creating a bot and chat object. */
    public Application() {
        BotConfiguration botConfiguration = BotConfiguration.builder()
                .name("alice")
                .path("src/main/resources")
                .build();
        Bot bot = new Bot(botConfiguration);
        chatSession = new Chat(bot);

    }
    /*getting an answer*/
    public String answer(String message) {

        return chatSession.multisentenceRespond(message);
    }

}
