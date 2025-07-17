package com.example.CRM.controller;

import com.example.CRM.entity.Plan;
import com.example.CRM.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/plans")
public class PlanController {

    @Autowired
    private PlanRepository planRepository;

    // Get all plans
    @GetMapping
    public List<Plan> getAllPlans() {
        return planRepository.findAll();
    }

    // Add a new plan
    @PostMapping
    public String addPlan(@RequestBody Plan plan) {
        planRepository.save(plan);
        return "Plan added successfully";
    }

    // Update an existing plan
    @PutMapping("/{id}")
    public String updatePlan(@PathVariable Long id, @RequestBody Plan updatedPlan) {
        return planRepository.findById(id).map(plan -> {
            plan.setPlanName(updatedPlan.getPlanName());
            plan.setPlanPrice(updatedPlan.getPlanPrice());
            plan.setMessageQuota(updatedPlan.getMessageQuota());
            planRepository.save(plan);
            return "Plan updated successfully";
        }).orElse("Plan not found");
    }

    // Delete a plan
    @DeleteMapping("/{id}")
    public String deletePlan(@PathVariable Long id) {
        if (planRepository.existsById(id)) {
            planRepository.deleteById(id);
            return "Plan deleted successfully";
        }
        return "Plan not found";
    }
}