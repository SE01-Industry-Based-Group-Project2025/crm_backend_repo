package com.example.CRM.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DashboardService {

    public Map<String, Object> getTeacherDashboard() {
        return Map.of(
                "studentEngagementStats", "Sample stats for student engagement",
                "upcomingLessons", "Sample upcoming lessons",
                "messagingActivity", "Sample messaging activity",
                "feedbackSummary", "Sample feedback summary"
        );
    }

    public Map<String, Object> getBusinessmanDashboard() {
        return Map.of(
                "leadsSummary", "Sample leads summary",
                "engagementGraphs", "Sample engagement graphs",
                "planUsage", "Sample plan usage",
                "teamActivity", "Sample team activity",
                "marketingInsights", "Sample marketing insights"
        );
    }
}