/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvidder.config;

import com.dvidder.domain.Account;
import com.dvidder.domain.ProfilePicture;
import com.dvidder.repository.AccountRepository;
import com.dvidder.repository.ProfilePictureRepository;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author akseli
 */
@Component
@Profile("default")
public class DefaultProfile {
    
    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    ProfilePictureRepository profilePictureRepository;
    
    @PostConstruct
    public void init() {
        Account user1 = new Account();
        user1.setUsername("akseli");
        user1.setPassword(passwordEncoder.encode("1234"));
        
        ProfilePicture profpic = new ProfilePicture();
        profpic.setDefaultPic(true);
        user1.setProfilePicture(profpic);
        
        profilePictureRepository.save(profpic);
        accountRepository.save(user1);
    }
}
