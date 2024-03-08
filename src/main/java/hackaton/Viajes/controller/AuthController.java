package hackaton.Viajes.controller;

import hackaton.Viajes.model.JwtResponse;
import hackaton.Viajes.model.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hackaton.Viajes.security.jwt.JwtUtils;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;
    @GetMapping("/login")
    public String getLoginDetails() {
        // Agrega lógica aquí para obtener detalles específicos sobre el proceso de inicio de sesión
        // Por ejemplo, puedes devolver estadísticas sobre la cantidad de veces que se ha iniciado sesión, etc.
        return "Detalles del proceso de inicio de sesión";
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        // Autenticar al usuario
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generar el token JWT
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwtToken = jwtUtils.generateToken(userDetails);

        // Devolver el token JWT como respuesta
        return ResponseEntity.ok(new JwtResponse(jwtToken));
    }
}
