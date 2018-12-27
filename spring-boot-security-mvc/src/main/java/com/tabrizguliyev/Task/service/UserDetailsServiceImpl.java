/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabrizguliyev.Task.service;

import com.tabrizguliyev.Task.dao.UserRepository;
import com.tabrizguliyev.Task.entities.Authorities;
import com.tabrizguliyev.Task.entities.User;
import com.tabrizguliyev.Task.util.LoggedInUser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userDetailsDao;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDetailsDao.findUserByUsername(username);//hashlenmish 12345
        if (user != null) {
            Collection<GrantedAuthority> auths = getAuthorityArr(user.getAuthorities());

            LoggedInUser lgU = new LoggedInUser(user, username, user.getPassword(), !user.getBlocked(), auths);

            return lgU;
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }

    public static Collection<GrantedAuthority> getAuthorityArr(Collection<Authorities> authorities) {

        List<GrantedAuthority> authoritiesArr = new ArrayList<>();
        Iterator<Authorities> iter = authorities.iterator();
        int i = 0;
        while (iter.hasNext()) {
            Authorities a = iter.next();
            authoritiesArr.add(new SimpleGrantedAuthority(a.getAuthority()));
            i++;
        }

        return authoritiesArr;
    }
}
