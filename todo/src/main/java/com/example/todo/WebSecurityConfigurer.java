package com.example.todo;

import org.springframework.context.MessageSourceAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
          // アクセス権限の設定
          // staticディレクトリにある、'/css/','fonts','/js/'は制限なし
          .antMatchers("/css/**", "/fonts/**", "/js/**").permitAll()
          // '/admin/'で始まるURLには、'ADMIN'ロールのみアクセス可
          .antMatchers("/admin/**").hasRole("ADMIN")
          .antMatchers("/login").permitAll() // 認証なしでアクセス可能なパス
          // 他は制限なし
          .anyRequest().authenticated()
        .and()
          // ログイン処理の設定
          .formLogin()
            // ログイン処理のURL
            .loginPage("/login")
            .loginProcessingUrl("/login") // ログインフォームのアクションに指定したURL[action="@{/login}"]を設定
            // usernameのパラメタ名
            .usernameParameter("username")
            // passwordのパラメタ名
            .passwordParameter("password")
            .successForwardUrl("/") // ログイン成功時に遷移するURL
            .failureUrl("/login?error") // ログイン失敗時に遷移するURL
            .permitAll()
        .and()
          // ログアウト処理の設定
          .logout()
            // ログアウト処理のURL
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            // ログアウト成功時の遷移先URL
            .logoutSuccessUrl("/login?logout")
            // ログアウト時に削除するクッキー名
            .deleteCookies("JSESSIONID")
            // ログアウト時のセッション破棄を有効化
            .invalidateHttpSession(true)
            .permitAll()
            // cfrの無効化
            .and().csrf().disable().cors();
        // ▼今回追加
        // メッセージをカスタマイズするために、メッセージソースを設定する
        AuthenticationManager a = this.authenticationManager();
        if (a instanceof ProviderManager) {
            ProviderManager a2 = (ProviderManager)a;
            a2.getProviders().forEach(p -> {
                if (p instanceof MessageSourceAware) {
                    // ((MessageSourceAware)p).setMessageSource(s);
                }
            });
        }
        // ▲今回追加
    }
}