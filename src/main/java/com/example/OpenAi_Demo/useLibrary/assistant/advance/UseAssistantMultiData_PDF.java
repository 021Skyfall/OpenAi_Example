package com.example.OpenAi_Demo.useLibrary.assistant.advance;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UseAssistantMultiData_PDF {
    public static void main(String[] args) {
        // OpenAI API 키 설정
        OpenAiService service = new OpenAiService("");

        // 텍스트 파일 경로 설정
        String txtFilePath1 = "C:/Users/keddy/Desktop/계획.txt";
        String txtFilePath2 = "C:/Users/keddy/Desktop/career/내_장&단점_메모.txt";

//         텍스트 파일 읽기
//        String fileContent1 = readTextFile(txtFilePath1);
//        String fileContent2 = readTextFile(txtFilePath2);

        // 대화 메시지 리스트 생성
        List<ChatMessage> messages = new ArrayList<>();
        messages.add(new ChatMessage("system", "You are a helpful assistant."));
        messages.add(new ChatMessage("user", "받은 텍스트 파일의 내용을 3줄로 요약해봐"));

        String pdfPath = "C:/Users/keddy/Downloads/아카이브/1.pdf";
        String pdfContent = readPdfFile(pdfPath);
        messages.add(new ChatMessage("user", pdfContent));

//        for (int i = 1; i <= 12; i++) {
//            String txtPath = "C:/Users/keddy/Downloads/아카이브/" + i + ".pdf";
//            String pdfContent = readPdfFile(txtPath);
//            if (!pdfContent.isEmpty()) {
//                messages.add(new ChatMessage("user", pdfContent));
//            }
//        }

        // ChatCompletionRequest 생성
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("gpt-4o-mini") // 사용할 모델 설정
                .messages(messages) // 사용자 메시지 추가
                .maxTokens(500) // 최대 토큰 수 설정
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

    // PDF 파일 읽기 메서드
    private static String readPdfFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();

            // 텍스트 추출 시 정렬 순서를 설정
            pdfStripper.setSortByPosition(true);

            // PDF에서 텍스트 추출
            content.append(pdfStripper.getText(document));
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading PDF file.";
        }

        // 유니코드 문제를 해결하기 위해 필요한 추가 처리
        return content.toString();
    }
}