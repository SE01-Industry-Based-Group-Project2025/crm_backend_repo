package com.example.CRM.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @GetMapping("/{id}/engagement")
    public Map<String, Object> getEngagementStats(@PathVariable Long id) {
        return Map.of(
                "teacherId", id,
                "activeStudents", 25,
                "participationRate", "85%",
                "averageQuizScore", "78%"
        );
    }

    @GetMapping("/{id}/upcoming-lessons")
    public Map<String, Object> getUpcomingLessons(@PathVariable Long id) {
        return Map.of(
                "teacherId", id,
                "lessons", new String[]{"Math - 10:00 AM", "Science - 2:00 PM"}
        );
    }

    @GetMapping("/{id}/chat-activity")
    public Map<String, Object> getChatActivity(@PathVariable Long id) {
        return Map.of(
                "teacherId", id,
                "recentMessages", 10,
                "unreadMessages", 5,
                "chatbotUsage", "50%"
        );
    }

    @GetMapping("/{id}/feedback-summary")
    public Map<String, Object> getFeedbackSummary(@PathVariable Long id) {
        return Map.of(
                "teacherId", id,
                "averageFeedbackScore", "4.5",
                "recentComments", new String[]{"Great session!", "Needs improvement."},
                "suggestions", "Focus on practical examples."
        );
    }
}