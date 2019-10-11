package com.herokuapp.schoolmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.herokuapp.schoolmvc.dao.UserDAO;
import com.herokuapp.schoolmvc.dao.RoleDAO;
import com.herokuapp.schoolmvc.model.Role;
import com.herokuapp.schoolmvc.model.User;
import com.herokuapp.schoolmvc.model.UserType;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = this.userDAO.findUserAccount(userName);

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getType().toString());
        grantList.add(authority);

        if(user.getType().equals(UserType.EMPLOYEE)){
            List<Role> roles = this.roleDAO.getAssignedRoles(user.getUserId());
            if (roles != null) {
                for (Role role : roles) {
                    grantList.add(new SimpleGrantedAuthority(role.getRoleName()));
                }
            }
        }

        if(user.getType().equals(UserType.ADMIN)){
            List<Role> roles = this.roleDAO.getAllRoles();
            if (roles != null) {
                for (Role role : roles) {
                    grantList.add(new SimpleGrantedAuthority(role.getRoleName()));
                }
            }
        }
        
        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantList);
        
        return userDetails;
    }

}