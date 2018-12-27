/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabrizguliyev.Task.util;

import com.tabrizguliyev.Task.entities.User;
import java.util.Collection;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;


/**
 *
 * @author sarkhanrasullu
 */
@Data
public class LoggedInUser extends org.springframework.security.core.userdetails.User{
    private User user;
    
    public LoggedInUser(User user, String username, String password, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, enabled, enabled, enabled, authorities);
         this.user = user;
    }
    
    
}
