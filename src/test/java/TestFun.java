import com.how2java.tmall.service.CategoryService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Objects;

public class TestFun {
    @Test
    public void getTotal() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CategoryService categoryService = context.getBean("categoryService", CategoryService.class);
        int total = categoryService.total();
        System.out.println(total);
    }

    @Test
    public void springBean() {
        String str =null;
        if(Objects.isNull(str)){
            if(true) {
                str = "123";
            }
        }
        System.out.println(str);
    }
}
