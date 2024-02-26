package hackaton.Viajes.controller;

import hackaton.Viajes.controller.request.CreateUserDb;
import hackaton.Viajes.model.*;
import hackaton.Viajes.repository.UserRepository;
//import hackaton.Viajes.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    HotelService hotelService;

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        //metodo de testeo sin seguridad
        return "Hello world not secured";
    }

    @GetMapping("/helloSecured")
    @ResponseBody
    public String helloSecured(){
        //metodo para testear la seguridad
        return "hello world from secured point";
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDb createUserDb){

        if(userRepository.existsByUsername(createUserDb.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: the name of the User its already in use!");
        }

        if(userRepository.existsByEmail(createUserDb.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: the Email its already in use!");
        }

        Set<RoleEntity> roles = createUserDb.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .username(createUserDb.getUsername())
                .password(passwordEncoder.encode(createUserDb.getPassword()))
                .email(createUserDb.getEmail())
                .roles(roles)
                .build();

        userRepository.save(userEntity);
     return ResponseEntity.ok(userEntity);
    }

//    @PostMapping("/createHotel")
//    public String createHotel(@ModelAttribute("formHotel") Hotel hotel){
//        hotelService.save(hotel);
//        System.out.println("el hotel guardado es: " + hotel);
//        return "redirect:/";
//    }


    @PostMapping("/submit") // Ejemplo de manejo de una solicitud POST
    public String handleSubmit() {
        // Aquí puedes manejar la lógica de la solicitud POST
        // por ejemplo, procesar datos enviados desde un formulario
        return "redirect:/"; // Redirige a la página principal después de procesar el formulario
    }
}
