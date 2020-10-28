//package com.factory.end.config;
//
//import com.factory.end.service.MyUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
//
///**
// * @Author jchonker
// * @Date 2020/8/24 17:24
// * @Version 1.0
// * 配置授权服务器
// */
//@Configuration
//@EnableAuthorizationServer
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//    @Autowired
//    AuthenticationManager authenticationManager;
//
//    @Autowired
//    RedisConnectionFactory redisConnectionFactory;
//
////    @Autowired
////    UserDetailsService userDetailsService;
//
//    @Autowired
//    MyUserDetailsService myUserDetailsService;
//
//
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("password")
//                .authorizedGrantTypes("password","refresh_token")
//                //token过期时间
//                .accessTokenValiditySeconds(1800)
//                //refresh_token过期时间
//                .refreshTokenValiditySeconds(18000)
//                .resourceIds("rid")
//                //作用域,范围
//                .scopes("all")
//                //123456的经过PasswordEncoder加密后的密文
//                .secret("$2a$10$5BTK7jQWU0cEX0gNk3G2buAiVHANXIr7ItIp8WHJ2zfD7Ws3B.W4y");
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.tokenStore(new RedisTokenStore(redisConnectionFactory))
//                .authenticationManager(authenticationManager)
//                //使用自定义的认证方式
//                .userDetailsService(myUserDetailsService);
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        //允许客户端表单验证
//        security.allowFormAuthenticationForClients();
//    }
//}
