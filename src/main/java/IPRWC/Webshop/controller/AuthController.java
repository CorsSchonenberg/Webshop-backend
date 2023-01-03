package IPRWC.Webshop.controller;

import IPRWC.Webshop.dao.UserDao;
import IPRWC.Webshop.model.ApiResponse;
import IPRWC.Webshop.model.LoginCredentials;
import IPRWC.Webshop.model.User;
import IPRWC.Webshop.security.JWTUtil;
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

    public AuthController(UserDao userDao, JWTUtil jwtUtil, AuthenticationManager authManager, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public Object registerHandler(@RequestBody User user) {
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        userDao.saveToDatabase(user);
        return new ApiResponse(HttpStatus.ACCEPTED, jwtUtil.generateToken(user.getEmail()));
    }

    @PostMapping("/login")
    public Object loginHandler(@RequestBody LoginCredentials body) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());
        authManager.authenticate(authInputToken);
        return new ApiResponse(HttpStatus.ACCEPTED, jwtUtil.generateToken(body.getEmail()));
    }


    @GetMapping("/info")
    public ApiResponse getUserDetails() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ApiResponse(HttpStatus.ACCEPTED, userDao.findByEmail(email).get());
    }
}
