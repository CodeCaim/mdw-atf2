package com.mdw.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mdw.code.model.Contacto;
import com.mdw.code.repository.ContactoRepository;

@Controller
public class ContactoController {
        @Autowired
    private ContactoRepository contactoRepository;

    @GetMapping("/contacto")
    public String mostrarFormularioContacto(Model model) {
        model.addAttribute("contacto", new Contacto());
        return "contacto";  // El nombre de la plantilla HTML
    }

    @PostMapping("/contacto")
    public String procesarFormularioContacto(@ModelAttribute("contacto") Contacto contacto) {
        contactoRepository.save(contacto);
        return "redirect:/contacto?exito";  // Redirigir después de enviar con éxito
    }

}
