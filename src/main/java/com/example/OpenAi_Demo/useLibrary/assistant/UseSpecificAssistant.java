package com.example.OpenAi_Demo.useLibrary.assistant;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatFunction;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UseSpecificAssistant {
    public static void main(String[] args) {
        // OpenAI API 키 설정
        OpenAiService service = new OpenAiService("");

        // 어시스턴트 ID 설정
        String assistantId = "asst_T4PnWVpKJoPXocMPWtDbrboy";

        // 대화 메시지 리스트 생성
        List<ChatMessage> messages = new ArrayList<>();
        messages.add(new ChatMessage("user", "선유준이 누구야?")); // 사용자 메시지 추가

        // ChatFunction 생성
        ChatFunction function = ChatFunction.builder()
                .name(assistantId)
                .description("어시스턴트 기능을 호출합니다.") // 설명 추가
                .build();

        // ChatCompletionRequest 생성
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("gpt-4o") // 사용할 모델 설정
                .messages(messages) // 사용자 메시지 추가
                .functions(Collections.singletonList(function)) // 함수 추가
                .maxTokens(150) // 최대 토큰 수 설정
                .build();

        // ChatCompletion 요청 및 응답 받기
        var response = service.createChatCompletion(chatCompletionRequest);
        response.getChoices().forEach(choice -> {
            System.out.println("Response: " + choice.getMessage().getContent()); // 응답 출력
        });
    }
}

/*
asst_T4PnWVpKJoPXocMPWtDbrboy
from openai import OpenAI
client = OpenAI()

assistant = client.beta.assistants.create(
  name="Math Tutor",
  instructions="You are a personal math tutor. Write and run code to answer math questions.",
  tools=[{"type": "code_interpreter"}],
  model="gpt-4o",
)
 */