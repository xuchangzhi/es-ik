package com.gl.es;

import com.gl.es.model.Product;
import com.gl.es.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsApplicationTests {
    @Autowired
    private ProductService productService;
    @Test
    public void contextLoads() {

        Product product = new Product();
        product.setYear(2001);
        product.setLength(10000l);
        product.setName("java指南");
        product.setContent("8月29日上午，记者来到市公安局出入境接待大厅看到，市民对于办理流程不用再向工作人员一一询问，而是通过添加“乐山出入境”微信公众号或手机扫描二维码进入导办系统，对于自己想办理的业务自行了解。\n" +
                "\n" +
                "　　记者登录系统后看到，该系统主要分有“出入境窗口办证服务”、“自助办证服务”、“证件直邮服务”等六大板块，申请人可点击相关板块，获得相关办证服务的精确指引。系统从实景图片、语音说明、文字描述三个方面，以“三位一体”的立体化展现模式给予指引，申请人只需按照系统提醒步骤完成每一步操作即可顺利办理完结业务。");
        productService.save(product);

        System.out.println(product.getId());
    }

}
