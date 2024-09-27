# OpenAi_Example
OpenAi를 활용한 챗봇 제작 전 실사용 학습


## log
9.23

기존 Open Ai와 통신하는 코드를 Java로 변환함
Java로 통신해서 요청과 응답을 주고 받음

---
9.25

https://github.com/TheoKanning/openai-java
오픈 소스 사용 해보려했는데
정보가 부족하고 원하는대로 사용이 안됨
특히 EndPoint 변경이 필요해서 학습이 요구됨

하드 코딩 vs 라이브러리
고민 해봐야할 것 같음

금요일에는 일단 자바 기반으로 하드 코딩 위주로 진행
GPT한테 법령 읽게 시킨 다음 요청응답 주고 받기 <

---
9.26     

라이브러리 사용불가 문제 해결함
openai-java 라이브러리의 버전이 너무 낮아서 문제였음
가장 최신 버전인 0.18.0 버전으로 재설치 후 진행하니 문제 없이 잘됨

그래들이 필요해서 기존 자바 프로젝트를 그래들이 적용된 프로젝트로 변경함 -> 이전 코드 옮김

---
9.27

일반적인 txt 파일을 String으로 파싱해서 보내주고
해당 내용을 읽고 응답 가능 (다수 가능)

다만, OpenAi 웹 사이트 플랫폼에서 입력한 파일은 읽지 못함
ChatFunction 을 사용해서 특정 이름의 어시스턴트와 통신이 가능한 것 같은데
안되서 더 알아봐야 할 것 같음

다음주에는
수만가지의 판례 파일을 어떻게 핸들링할지 고민해보고 적용해볼 것
