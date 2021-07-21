package com.alevel.application.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.CharBuffer;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminController {

    PasswordEncoder passwordEncoder;

    @GetMapping("/password/{password}")
    @ResponseBody
    public String encodePass(@PathVariable(name = "password") String password) {
        return passwordEncoder.encode(CharBuffer.wrap(password.toCharArray()));
    }

}
