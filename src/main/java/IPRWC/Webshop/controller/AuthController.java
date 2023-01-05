package IPRWC.Webshop.controller;

import IPRWC.Webshop.dao.UserDao;
import IPRWC.Webshop.model.ApiResponse;
import IPRWC.Webshop.model.LoginCredentials;
import IPRWC.Webshop.model.User;
import IPRWC.Webshop.security.JWTUtil;
import IPRWC.Webshop.service.InvalidMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final InvalidMailService invalidMailService;

    public AuthController(UserDao userDao, JWTUtil jwtUtil, AuthenticationManager authManager, PasswordEncoder passwordEncoder, InvalidMailService invalidMailService) {
        this.userDao = userDao;
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
        this.passwordEncoder = passwordEncoder;
        this.invalidMailService = invalidMailService;
    }

    @PostMapping("/register")
    public Object registerHandler(@RequestBody User user) {
        try {
            if (invalidMailService.patternMatches(user.getEmail())) {
                String encodedPass = passwordEncoder.encode(user.getPassword());
                user.setPassword(encodedPass);
                userDao.saveToDatabase(user);
                return new ApiResponse(HttpStatus.ACCEPTED, jwtUtil.generateToken(user.getEmail()));
            } else {
                return new ApiResponse(HttpStatus.BAD_REQUEST, "Invalid email");
            }
        } catch (Exception e) {
            return new ApiResponse(HttpStatus.BAD_REQUEST, "Email already in use");
        }
    }

    @PostMapping("/login")
    public Object loginHandler(@RequestBody LoginCredentials body) {
        try {
            if (invalidMailService.patternMatches(body.getEmail())) {
                UsernamePasswordAuthenticationToken authInputToken =
                        new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());
                authManager.authenticate(authInputToken);
                return new ApiResponse(HttpStatus.ACCEPTED, jwtUtil.generateToken(body.getEmail()));
            } else {
                return new ApiResponse(HttpStatus.BAD_REQUEST, "Invalid email");
            }
        } catch (AuthenticationException authExc) {
            return new ApiResponse(HttpStatus.UNAUTHORIZED, "Invalid email/password");
        }
    }


    @GetMapping("/info")
    public ApiResponse getUserDetails() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ApiResponse(HttpStatus.ACCEPTED, userDao.findByEmail(email).get());
    }
}
