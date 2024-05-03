package org.swagger.lab2_swagger.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.swagger.lab2_swagger.model.Account;
import org.swagger.lab2_swagger.model.User;
import org.swagger.lab2_swagger.repository.AccountRepository;
import org.swagger.lab2_swagger.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    public void registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) == null) {
            Account account = new Account();
            account.setBalance(0.0);
            account.setUser(user);
            userRepository.save(user);
            user.setAccount(account);
            accountRepository.save(account);
        }
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return user;
    }
}
