package com.example.demo_security.security.services;

import com.example.demo_security.models.User;
import com.example.demo_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//dùng để tải tên người dùng bằng tên người dùng và
// trả về đối tượng userdetails mà spring có thể xác thực và xác nhận

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional //khi có Exception xảy ra thì Transaction sẽ rollback lại các thao tác trước đó.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);//xây dựng một UserDetails đối tượng

    }

}
