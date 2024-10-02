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

/* 오픈 ai 어시스턴트에게 벡터 스토어에 담긴 파일을 읽게할 수 있는 파이썬 코드
from openai import OpenAI
client = OpenAI(api_key="")

assistant = client.beta.assistants.create(
    name="asst_T4PnWVpKJoPXocMPWtDbrboy",
    instructions="",
    tools=[{"type": "file_search"}],
    tool_resources={
       "file_search": {
          "vector_store_ids": ["vs_GEukEMvlvpbvZEYMGgNaEIZc"] # vector_store_ids 설정 / 나의 정보를 기입한 txt 파일
       }
    },
    model="gpt-3.5-turbo"
)

thread = client.beta.threads.create()

message = client.beta.threads.messages.create(
  thread_id=thread.id,
  role="user",
  content="선유준의 나이는 몇 살일까"
)

run = client.beta.threads.runs.create_and_poll(
  thread_id=thread.id,
  assistant_id=assistant.id,
)

if run.status == 'completed':
  messages = client.beta.threads.messages.list(
    thread_id=thread.id
  )
  print(messages)
  # value 추출
else:
  print(run.status)
 */