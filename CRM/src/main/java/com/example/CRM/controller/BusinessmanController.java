package com.example.CRM.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/business")
public class BusinessmanController {

    @GetMapping("/{id}/leads-summary")
    public Map<String, Object> getLeadsSummary(@PathVariable Long id) {
        return Map.of(
                "businessmanId", id,
                "totalLeads", 50,
                "conversionRate", "20%",
                "customerList", new String[]{"Customer A", "Customer B"}
        );
    }

    @GetMapping("/{id}/engagement-graph")
    public Map<String, Object> getEngagementGraph(@PathVariable Long id) {
        return Map.of(
                "businessmanId", id,
                "timeSeriesData", new int[]{10, 20, 15, 30}
        );
    }

    @GetMapping("/{id}/plan-usage")
    public Map<String, Object> getPlanUsage(@PathVariable Long id) {
        return Map.of(
                "businessmanId", id,
                "currentPlan", "Premium",
                "usage", "75%",
                "quotaRemaining", "25%"
        );
    }

    @GetMapping("/{id}/team-activity")
    public Map<String, Object> getTeamActivity(@PathVariable Long id) {
        return Map.of(
                "businessmanId", id,
                "recentActions", new String[]{"Task A completed", "Logged in"},
                "loginTimes", new String[]{"9:00 AM", "10:30 AM"},
                "tasksCompleted", 5
        );
    }

    @GetMapping("/{id}/marketing-insights")
    public Map<String, Object> getMarketingInsights(@PathVariable Long id) {
        return Map.of(
                "businessmanId", id,
                "campaignStats", "Campaign A - 50% success",
                "chatbotConversionRate", "30%",
                "userInteractions", 100
        );
    }
}