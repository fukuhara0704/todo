package com.example.todo.service;

import com.example.todo.mapper.UserMapper;
import com.example.todo.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) throw new UsernameNotFoundException("");

        User user = userMapper.selectByUser(username);

        // ユーザが存在しない場合
        if (user == null) throw new UsernameNotFoundException("");
        // アカウントの有効期限切れ、アカウントのロック、パスワードの有効期限切れ、ユーザの無効を判定
        if (!user.isAccountNonExpired() || !user.isAccountNonLocked() ||
                !user.isCredentialsNonExpired() || !user.isEnabled())
            throw new UsernameNotFoundException("");
        return user;
    }

}