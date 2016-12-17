/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvidder.service;

import com.dvidder.domain.Account;
import com.dvidder.domain.Post;
import com.dvidder.repository.AccountRepository;
import com.dvidder.repository.PostRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/**
 *
 * @author akseli
 */
@Service
public class PostService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PostRepository postRepository;

    public void createPost(String content) {

        // Find current account
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account currentAccount = accountRepository.findByUsername(currentUser.getUsername());

        // Create the post
        Post post = new Post();
        post.setContent(content);

        currentAccount.getPosts().add(post);

        postRepository.save(post);
        accountRepository.save(currentAccount);
    }

    public List<Post> getPostsByUser(String username) {
        Account account = accountRepository.findByUsername(username);
        return account.getPosts();
    }
}