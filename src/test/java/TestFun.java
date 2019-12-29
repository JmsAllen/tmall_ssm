import com.how2java.tmall.mapper.ProductMapper;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.ProductExample;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.service.ProductService;
import com.how2java.tmall.service.impl.ProductServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Objects;

public class TestFun {

    @Test
    public void springBean() {
        String str = null;
        if (Objects.isNull(str)) {
            if (true) {
                str = "123";
            }
        }
        System.out.println(str);
    }

    public static int myCompare(String str1, String str2) {

        String[] split1 = str1.split("\\.");
        String[] split2 = str2.split("\\.");
        int i1, i2;
        for (int i = 0; i < 3; i++) {
            i1 = Integer.parseInt(split1[i]);
            i2 = Integer.parseInt(split2[i]);
            if (i1 != i2) {
                if (i1 > i2) return 1;
                return -1;
            }
        }
        return 0;
    }

    public void deal() {
        String result;
        int i = myCompare("0.2.1", "0.2.0");
        if (i > 0) result = "第一个参数大";
        else if (i < 0) result = "第二个参数大";
        else result = "两者一样大";
        System.out.println(result);
    }

}
