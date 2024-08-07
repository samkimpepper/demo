package com.example.demo.common.security;

import com.example.demo.common.error.EntityNotFoundException;
import com.example.demo.common.error.ErrorCode;
import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws EntityNotFoundException {
        User user = userRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND, id));
        return new UserDetailsImpl(user);
    }
}