package hello.core.Singleton;

public class SingletonService {
    //static -> 하나만 만들어져서 올라감
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }


}
