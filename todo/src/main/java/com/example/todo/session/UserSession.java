package com.example.todo.session;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSession implements Serializable {
    
    private final long serialVersionUID = 6334063099671792256L;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
