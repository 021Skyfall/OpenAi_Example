package com.example.OpenAi_Demo.useLibrary;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import java.util.*;

public class OpenAiChatExample {
    public static void main(String[] args) {
        // OpenAI API 키 설정
        OpenAiService service = new OpenAiService("API_KEY");

        // 대화 메시지 리스트 생성
        List<ChatMessage> messages = new ArrayList<>();
        messages.add(new ChatMessage("user", "통신이 잘 되는지 확인을 위한 메시지야. 확인할 수 있게 아무 말이나 해볼래?")); // 사용자 메시지 추가

        // ChatCompletionRequest 생성
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo") // 사용할 모델 설정
                .messages(messages) // 사용자 메시지 추가
                .maxTokens(150) // 최대 토큰 수 설정
                .build();

        // ChatCompletion 요청 및 응답 받기
        var response = service.createChatCompletion(chatCompletionRequest);
        response.getChoices().forEach(choice -> {
            System.out.println("Response: " + choice.getMessage().getContent()); // 응답 출력
        });
    }
}
