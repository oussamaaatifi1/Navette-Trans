    package com.platformtrasnport.platformtransport.service;

    import com.platformtrasnport.platformtransport.auth.AuthenticationRequest;
    import com.platformtrasnport.platformtransport.auth.AuthenticationResponse;
    import com.platformtrasnport.platformtransport.auth.RegisterRequest;
    import com.platformtrasnport.platformtransport.model.*;
    import com.platformtrasnport.platformtransport.model.enul.Role;
    import com.platformtrasnport.platformtransport.repository.UtilisateurRepository;
    import lombok.RequiredArgsConstructor;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;

    @Service
    @RequiredArgsConstructor
    public class AuthenticationService {

        private final UtilisateurRepository userdao;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthenticationResponse registerParticulier(RegisterRequest request) {
            Utilisateur user = new Particulier();
            user.setNom(request.getNom());
            user.setPrenom(request.getPrenom());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword())); // Ensure correct field name
            user.setRole(Role.PARTICULIER);
            userdao.save(user);

            var jwtToken = jwtService.generateToken(user, user.getId());
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }

        public AuthenticationResponse registerAdmin(RegisterRequest request) {
            Utilisateur admin = new Administrateur();
            admin.setNom(request.getNom());
            admin.setPrenom(request.getPrenom());
            admin.setEmail(request.getEmail());
            admin.setPassword(passwordEncoder.encode(request.getPassword()));
            admin.setRole(Role.ADMIN);
            userdao.save(admin);

            var jwtToken = jwtService.generateToken(admin, admin.getId());
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }

        public AuthenticationResponse registerEmploye(RegisterRequest request) {
            Utilisateur employe = new Employe();
            employe.setNom(request.getNom());
            employe.setPrenom(request.getPrenom());
            employe.setEmail(request.getEmail());
            employe.setPassword(passwordEncoder.encode(request.getPassword()));
            employe.setRole(Role.EMPLOYE);
            userdao.save(employe);

            var jwtToken = jwtService.generateToken(employe, employe.getId());
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }

        public AuthenticationResponse registerEmployeur(RegisterRequest request) {
            Utilisateur employeur = new Employeur();
            employeur.setNom(request.getNom());
            employeur.setPrenom(request.getPrenom());
            employeur.setEmail(request.getEmail());
            employeur.setPassword(passwordEncoder.encode(request.getPassword())); // Ensure correct field name
            employeur.setRole(Role.EMPLOYEUR);
            userdao.save(employeur);

            var jwtToken = jwtService.generateToken(employeur, employeur.getId());
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            var user = userdao.findByEmail(request.getEmail());
            var jwtToken = jwtService.generateToken(user, user.getId());

            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .userId(user.getId())
                    .build();
        }
    }
