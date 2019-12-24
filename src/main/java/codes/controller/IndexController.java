package codes.controller;

import codes.config.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController 
{
    // Injecting ConfigurationProperties in your Beans
    @Autowired
    private AppProperties appProperties;

    @GetMapping("/")
    public Map<String, String> getAppDetails() 
    {
        Map<String, String> appDetails = new HashMap<>();
        appDetails.put("name", appProperties.getName());
        appDetails.put("description", appProperties.getDescription());
        appDetails.put("userName", appProperties.getSecurity().getUsername());
        appDetails.put("password", appProperties.getSecurity().getPassword());
        
        List<String> roles = appProperties.getSecurity().getRoles();
        Map<String, String> permissions = appProperties.getSecurity().getPermissions();

        return appDetails;
    }
}