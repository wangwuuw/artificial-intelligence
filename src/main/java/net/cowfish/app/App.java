package net.cowfish.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @classDesc： 功能描述：（）
 * @author：王武
 * @createTime 2018/2/5
 * @verson: v1.0
 * @copyright: 上海江豚教育科技有限公司
 * @qq:834667820
 */
@ComponentScan(basePackages = {"net.cowfish"})
@MapperScan("net.cowfish.dao")
@EnableAutoConfiguration
@EntityScan("net.cowfish.entity")
public class App {

    public static void main(String[] args) {
        // 主函数运行springboot项目
        SpringApplication.run(App.class, args);
    }

}
