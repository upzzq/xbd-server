package com.xbd.svc.router.config.properties;

import com.xbd.svc.auth.common.util.RsaUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.security.PublicKey;

/**
 * JWT设置
 */
@ConfigurationProperties(prefix = JwtProperties.JWT_PREFIX)
@Data
@Slf4j
public class JwtProperties {

    public static final String JWT_PREFIX = "xbd.jwt";

    /**
     * 生成公钥文件的存放位置
     */
    private String pubKeyPath = "C:\\tmp\\rsa\\rsa.pub";

    /**
     * 已生成的公钥
     */
    private PublicKey publicKey;

    /**
     * Jwt存放在cookie中的key
     */
    private String cookieName;

    /**
     * 生成公钥和私钥文件
     */
    @PostConstruct
    public void init() {
        try {
            //获取公钥
            this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        } catch (Exception e) {
            log.error("初始化公钥和私钥失败!", e);
            throw new RuntimeException();
        }
    }
}
