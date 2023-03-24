package scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonTest.class);

        SingletonTest singletonBean1 = ac.getBean(SingletonTest.class);
        SingletonTest singletonBean2 = ac.getBean(SingletonTest.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);
        assertThat(singletonBean1).isSameAs(singletonBean2);

        ac.close();
    }

    @Scope("singleton")
    static  class SingtonBean{
        @PostConstruct
        public void init() {
            System.out.println("SingtonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingtonBean.destroy");
        }

    }

}
