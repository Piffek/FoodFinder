package com.example.foodFinder.Services;

import com.example.foodFinder.Services.Interfaces.EmailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendEmail(List<String> toList, String message) {

    }
}
