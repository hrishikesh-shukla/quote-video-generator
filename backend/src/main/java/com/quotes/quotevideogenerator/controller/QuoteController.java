package com.quotes.quotevideogenerator.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {

	private final String[] quotes = { "The only way to do great work is to love what you do.",
			"Success is not final, failure is not fatal: It is the courage to continue that counts.",
			"Believe you can and you're halfway there.", "The best way to predict the future is to create it.",
			"Dream big and dare to fail." };

	@GetMapping("/api/quote")
	public String getRandomQuote() {
		Random random = new Random();
		int index = random.nextInt(quotes.length);
		return quotes[index];
	}
}