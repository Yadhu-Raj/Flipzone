package com.project.flipzone.controller;

import com.project.flipzone.flipzoneDTO.UserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerFilpzone {

    @PostMapping("/v1/adduser")
    public String addUser (@RequestBody UserDTO user){
        return "";
    }
}
