package com.example.CRM.controller;

import com.example.CRM.model.Customer;
import com.example.CRM.service.CustomerService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class    HomeController {

   private final CustomerService customerService;

    public HomeController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Customer> customerList = customerService.getAllCustomer();
        model.addAttribute("customerList", customerList);
        return "home";
    }




    @GetMapping("/create")
    public String create(Model model){
        Customer customer = new Customer();

        model.addAttribute("customer",customer);

        return "create";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("customer") Customer customer,
                       @NotNull BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       Model model) {

        if (bindingResult.hasErrors()) {
            return "create";
        }

        customerService.save(customer); // This is for creating
        redirectAttributes.addFlashAttribute("message", "Customer Added Successfully");

        return "redirect:/";
    }


    @GetMapping("customer/{id}")
    public String show(@PathVariable long id, Model model){

        customerService.findById(id)
                .ifPresent(customer -> model.addAttribute("customer", customer));

        return "show";
    }


    //update customer
    @GetMapping("/customer/{id}/edit")
    public String edit(@PathVariable long id, Model model) {
        Customer customer = customerService.findById(id).orElse(null);
        model.addAttribute("customer", customer);
        return "create";
    }


    //UPDATE CUSTOMER
    @PostMapping("/customer/{id}/update")
    public String update(@PathVariable long id,
                         @Valid @ModelAttribute("customer") Customer customer,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         Model model) {

        if (bindingResult.hasErrors()) {
            return "create";
        }

        customer.setId(id);
        customerService.save(customer); // Reuse the same save method
        redirectAttributes.addFlashAttribute("message", "Customer Updated Successfully");

        return "redirect:/";
    }



    //DELETE CUSTOMER

    @GetMapping("customer/{id}/delete")

    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes){

        customerService.deleteById(id);

        redirectAttributes.addFlashAttribute(
                "message",
                "Customer Deleted Successfully");

        return "redirect:/";
    }

}
