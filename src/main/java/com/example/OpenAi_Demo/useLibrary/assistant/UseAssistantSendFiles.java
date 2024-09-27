package com.example.OpenAi_Demo.useLibrary.assistant;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UseAssistantSendFiles {
    public static void main(String[] args) {
        // OpenAI API 키 설정
        OpenAiService service = new OpenAiService("API_KEY");

        // 웹 어시스턴트
        String assistantId = "asst_T4PnWVpKJoPXocMPWtDbrboy";

        // 텍스트 파일 경로 설정
        String txtFilePath1 = "C:/Users/keddy/Desktop/계획.txt";
        String txtFilePath2 = "C:/Users/keddy/Desktop/career/내_장&단점_메모.txt";

//         텍스트 파일 읽기
        String fileContent1 = readTextFile(txtFilePath1);
        String fileContent2 = readTextFile(txtFilePath2);

        // 대화 메시지 리스트 생성
        List<ChatMessage> messages = new ArrayList<>();
        messages.add(new ChatMessage("system", "You are a " + assistantId + " assistant."));
        messages.add(new ChatMessage("user", "7월 30일에 뭐해야하니, 그리고 내 장점이 뭐야?"));
        messages.add(new ChatMessage("user", fileContent1));
        messages.add(new ChatMessage("user", fileContent2));

        // ChatCompletionRequest 생성
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("gpt-4o-mini") // 사용할 모델 설정
                .messages(messages) // 사용자 메시지 추가
                .maxTokens(150) // 최대 토큰 수 설정
                .build();

        // ChatCompletion 요청 및 응답 받기
        var response = service.createChatCompletion(chatCompletionRequest);
        response.getChoices().forEach(choice -> {
            System.out.println("Response: " + choice.getMessage().getContent()); // 응답 출력
        });
    }

    // 텍스트 파일 읽기 메서드
    private static String readTextFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath))); // 파일 내용을 String으로 변환
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading file.";
        }
    }
}
