package com.example.CRM.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DashboardController {

    @GetMapping("/dashboard")
    public Map<String, Object> getDashboard(Authentication authentication) {
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        if ("ROLE_TEACHER".equals(role)) {
            return Map.of(
                    "studentEngagementStats", "Sample stats for student engagement",
                    "upcomingLessons", "Sample upcoming lessons",
                    "messagingActivity", "Sample messaging activity",
                    "feedbackSummary", "Sample feedback summary"
            );
        } else if ("ROLE_BUSINESSMAN".equals(role)) {
            return Map.of(
                    "leadsSummary", "Sample leads summary",
                    "engagementGraphs", "Sample engagement graphs",
                    "planUsage", "Sample plan usage",
                    "teamActivity", "Sample team activity",
                    "marketingInsights", "Sample marketing insights"
            );
        }

        return Map.of("error", "Role not recognized");
    }
}