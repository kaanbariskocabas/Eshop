package com.kaan.eshop.authservice.app.resource;

import com.kaan.eshop.authservice.app.config.jwt.JwtTokenUtil;
import com.kaan.eshop.authservice.backend.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class UserResource {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public UserDTO getAuthenticatedUser(HttpServletRequest request) {
        String token = jwtTokenUtil.getRefactoredToken(request.getHeader(tokenHeader));
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserDTO user = (UserDTO) userDetailsService.loadUserByUsername(username);
        return user;
    }
}
