package hello.core.lifecycle;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient{
    private String url;
    public NetworkClient() {
        System.out.println("생성자를 호출, url = "+url);
    }
    public void setUrl(String url) {
        this.url = url;
    }
    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: "+ url);
    }

    //의존관계 주입 끝나면 호출
    @PostConstruct
    public void init() throws Exception {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.close");
        disconnect();
    }

    /*
    초기화, 소멸 인터페이스의 단점
        스프링 전용 인터페이스 이기 때문에 해당 코드가 스프링 전용 인터페이스에 의존한다.
        초기화, 소멸 메서드의 이름을 변경할 수 없다.
        내가 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없다.

        참고) 인터페이스를 사용하는 초기화, 종료 방법은 스프링 초창기에 나온 방법들이고, 지금은 더 나은 방법들이 있어서 거의 사용하지 않는다.
     */

    /*
    설정 정보 사용 특징
        메서드 이름을 자유롭게 줄 수 있다.
        스프링 빈이 스프링 코드에 의존하지 않는다.
        코드가 아니라 설정 정보를 사용하기 때문에 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드를 적용할 수 있다.
     */

    /*
    종료 메서드 추론
        @Bean의 'destroyMethod'는 기본값이 '(inferred)' (추론)으로 등록되어 있다.
        이 추론 기능은 'clost', 'shutdown' 이라는 이름의 메서드를 자동으로 호출하기 때문에
        직접 스프링 빈으로 등록하면 종료 메서드는 따로 적어주지 않아도 잘 동작한다

        참고) 추론 기능을 사용하기 싫으면 destroyMethod= "" 처럼 빈 공백을 지정하면 된다.
     */

    /*
    @PostConstruce, @PreDestroy 애노테이션 특징
        최신 스프링에서 가장 권장하는 방법이다.
        애노테이션 하나만 붙이면 되므로 매우 편리하다.
        스프링에 종속된 기술이 아니라, JSR-250 이라는 자바 표준이다. 따라서 스프링이 아닌 다른 컨테이너에서도 동작한다.
        컴포넌트 스캔과 잘 어울린다.
        유일한 단점! 외부 라이브러리에는 적응하지 못한다. -> 외부 라이브러리를 초기화, 종료 해야 하면 @Bean의 기능을 사용하자.

        *** 정리 ***

        @PostConstruct, @PreDestroy 애노테이션을 사용하자
        코드를 수정할 수 없는 외부 라이브러리를 초기화, 종료해야 하면 @Bean의 initMethod, destroyMethod 를 사용하자
     */
}
