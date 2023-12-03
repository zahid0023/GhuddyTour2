package com.ghuddy.backendapp.config;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/open")
public class YourController {

    @RequestMapping(value = "/refresh_token", method = RequestMethod.OPTIONS)
    public void handleOptionsRequest() {
        // Add any necessary headers here
    }

    // Other controller methods...

}
