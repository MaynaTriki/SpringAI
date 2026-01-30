package com.springAI.openAI.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatClient chatClient;

    /* Thanks to spring open AI dependency, during the startup of the app,
    * the ChatModel will be called with a single implementation of OpenAiChatModel and its bean will be created
    * this will be the model that has LLM provider as OpenAI
    * the bean of ChatClient.Builder will be created by DefaultChatClientBuilder
     */
    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    /* The user will invoke chat rest API by providing the prompt message
    * the prompt message will be sent to OpenAI API using Spring AI ChatClient
    * and the response from OpenAI API will be returned to the user
    * */
    @RequestMapping("/chat")
    public String chat(@RequestParam("message") String message){
        // Call method initiate the chat with the LLM model
        // Content provide the response from the LLM model
        return chatClient.prompt(message).call().content();
    }
}
