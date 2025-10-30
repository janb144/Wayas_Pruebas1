package com.wayas.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String mostrarDashboard() {
        // Muestra dashboard.html
        return "dashboard";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/login"; 
    }
    
    @GetMapping("/gestion_compra")
    public String mostrarGestionCompra() {
        return "gestion_compra"; 
    }

    @GetMapping("/gestion_inventario")
    public String mostrarGestionInventario() {
        return "gestion_inventario"; 
    }

     @GetMapping("/gestion_recetas")
     public String mostrarGestionRecetas() {
         return "gestion_recetas"; 
     }

     @GetMapping("/gestion_calificacion")
     public String mostrarGestionCalificacion() {
         return "gestion_calificacion"; 
     }
}

