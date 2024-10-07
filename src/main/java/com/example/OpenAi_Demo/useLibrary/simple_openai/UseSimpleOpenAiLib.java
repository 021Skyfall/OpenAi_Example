package com.example.OpenAi_Demo.useLibrary.simple_openai;

import io.github.sashirestela.openai.SimpleOpenAI;
import io.github.sashirestela.openai.domain.chat.ChatMessage;
import io.github.sashirestela.openai.domain.chat.ChatRequest;

// 심플 라이브러리 기본 사용
public class UseSimpleOpenAiLib {
    public static void main(String[] args) {
        var openAI = SimpleOpenAI.builder()
                .apiKey("API-KEY")
                .build();

        var chatRequest = ChatRequest.builder()
                .model("gpt-4o-mini")
                .message(ChatMessage.SystemMessage.of("You are an expert in AI."))
                .message(ChatMessage.UserMessage.of("안녕, 넌 누구니?"))
                .temperature(0.0)
                .maxTokens(3000)
                .build();

        var futureChat = openAI.chatCompletions().create(chatRequest);
        var chatResponse = futureChat.join();
        System.out.println(chatResponse.firstContent());
    }
}

/* 어시스턴트 활용 (작동 안함)
import io.github.sashirestela.openai.SimpleOpenAI;
import io.github.sashirestela.openai.domain.assistant.*;
import java.util.Arrays;

public class OpenAIChatDemo {
    private SimpleOpenAI openAI;
    private String assistantId;
    private String threadId;

    public OpenAIChatDemo() {
        openAI = SimpleOpenAI.builder().apiKey("YOUR_API_KEY").build(); // API 키 설정
    }

    public void createAssistant(String vectorStoreId) {
        var assistant = openAI.assistants()
                .create(AssistantRequest.builder()
                        .name("asst_T4PnWVpKJoPXocMPWtDbrboy")
                        .instructions("") // 필요한 지침 추가
                        .tools(Arrays.asList(AssistantTool.fileSearch())) // 파일 검색 도구 설정
                        .toolResources(ToolResourceFull.builder()
                                .fileSearch(ToolResourceFull.FileSearch.builder()
                                        .vectorStoreIds(Arrays.asList(vectorStoreId)) // 벡터 스토어 ID 설정
                                        .build())
                                .build())
                        .model("gpt-3.5-turbo") // 모델 설정
                        .build())
                .join();
        assistantId = assistant.getId();
        System.out.println("Assistant was created with id: " + assistantId);
    }

    public void runConversation() {
        // 스레드 생성
        var thread = openAI.threads().create(ThreadRequest.builder().build()).join();
        threadId = thread.getId();
        System.out.println("Thread was created with id: " + threadId);

        // 사용자 메시지 입력
        var userMessage = System.console().readLine("Ask a question: ");
        openAI.threadMessages()
                .create(threadId, ThreadMessageRequest.builder()
                        .role(ThreadMessageRole.USER)
                        .content(userMessage)
                        .build())
                .join();

        // 실행 요청
        var run = openAI.threadRuns()
                .create(threadId, ThreadRunRequest.builder()
                        .assistantId(assistantId)
                        .build())
                .join();

        // 완료된 경우 메시지 목록 가져오기
        if (run.getStatus().equals(ThreadRun.RunStatus.COMPLETED)) {
            var messagesResponse = openAI.threadMessages().list(threadId).join();
            messagesResponse.forEach(msg -> System.out.println("Response: " + msg.getContent()));
        } else {
            System.out.println("Run status: " + run.getStatus());
        }
    }

    public static void main(String[] args) {
        var demo = new OpenAIChatDemo();

        // 벡터 스토어 ID를 여기서 설정
        String vectorStoreId = "vs_GEukEMvlvpbvZEYMGgNaEIZc"; // 예시로 벡터 스토어 ID 설정
        demo.createAssistant(vectorStoreId);
        demo.runConversation();
    }
}
 */
