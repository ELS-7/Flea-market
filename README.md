# Flea-market
판매자와 구매자가 사용가능한 거래용 flea market웹 사이트

작동방법
0. D 드라이브 밑에서 DevJava 폴더 압축을 해제하고 그곳에서 이클립스로(Xenus) 실행해주세요.
(eclipse 폴더 내 eclipse.ini에 
-vm
C:\Program Files (x86)\Java\jdk1.8.0_73\bin\javaw.exe 형태로 jdk 연결이 설정되어있습니다.)

1- ms sql을 사용하여 db 파일을 연결하세요.

2 - Eclipse 이클립스의 JRE system Library [jdk1.8.0_73]을 사용하세요.

3. Eclipse - 이클립스에 Xenus를 임포트하세요.(제 경우에는 Mars.2를 사용했습니다)

4. 이클립스 Servers를 통하여 Tomcat (제 경우에는 v7.0 을 사용했습니다)을 작동시킵니다. 
서버를 로컬호스트 8081등으로 연결합니다. (이클립스 서버 환경은 server.xml 등을 사용하세요)

5. 톰켓 서버(http://localhost:8081/)로 연결하여 웹 페이지를 확인하세요.

6. updown directory(사진):  프로그램에서 상품 이미지는 D:/DevJava/updn/ 에 저장하고 가져옵니다. 저장할 때 파일이 중복되지 않도록 UUID(랜덤 아이디)로 관리합니다. 


