package cn.itcast.zt.producer.config;

import org.springframework.stereotype.Component;

/**
 * 生产者管道配置项
 * Created by zhangtian on 2017/6/1.
 */
@Component
public class ProducerConfig {
    // broker地址
    private String brokerUrl ;
    // 用户名
    private String userName ;
    // 密码
    private String password ;

    public String getBrokerUrl() {
        return brokerUrl;
    }

    public void setBrokerUrl(String brokerUrl) {
        this.brokerUrl = brokerUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
