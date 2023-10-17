package com.file.compressor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Himal Rai on 10/15/2023
 * Sb Solutions Nepal pvt.ltd
 * Project compressor.
 */
@RequestMapping("/")
@Controller
public class Home {

    @GetMapping
    public String home() {
        return "home/index";
    }
}
