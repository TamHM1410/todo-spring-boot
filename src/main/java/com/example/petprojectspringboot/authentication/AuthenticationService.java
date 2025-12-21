package com.example.petprojectspringboot.authentication;

import com.example.petprojectspringboot.authentication.data.AuthenticationRequest;
import com.example.petprojectspringboot.cache.RedisKeyTypes;
import com.example.petprojectspringboot.cache.RedisStoreRepository;
import com.example.petprojectspringboot.cache.manager.RedisKeyManager;
import com.example.petprojectspringboot.config.security.JwtTokenUtils;
import com.example.petprojectspringboot.user.UserEntity;
import com.example.petprojectspringboot.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    private final JwtTokenUtils jwtTokenUtils;
    private final RedisStoreRepository redisStoreRepository;

    public Map<String,Object> signIn(AuthenticationRequest authenticationRequest) {

        if(authenticationRequest.getUsername()==null || authenticationRequest.getPassword()==null){
            throw  new RuntimeException("Invalid username or password");
        }

        UserEntity userEntity=userRepository.findByUsername(authenticationRequest.getUsername());

        if(userEntity==null){
            throw  new RuntimeException("Invalid username or password");
        }

        if(!bCryptPasswordEncoder.matches(authenticationRequest.getPassword(),userEntity.getPassword())){
            throw  new RuntimeException("Invalid password");
        }

        String token= jwtTokenUtils.generateToken(userEntity);

        Map<String,Object> returnedData = new HashMap<>();
        returnedData.put("username",userEntity.getUsername());
        returnedData.put("token",token);

        //#region store to redis
        String key=RedisKeyManager.detailKey(RedisKeyTypes.USER_DETAIL.toString(),userEntity.getId());
        redisStoreRepository.saveToRedisStore(key,returnedData);
        //#endregion

        return returnedData;

    }

    public UserEntity signUp(AuthenticationRequest authenticationRequest) {
    try{
        if(authenticationRequest.getUsername()==null || authenticationRequest.getPassword()==null){
            throw  new RuntimeException("Invalid username or password");
        }

        UserEntity userEntity=userRepository.findByUsername(authenticationRequest.getUsername());
        if(userEntity!=null){
            throw  new RuntimeException("User already exists");
        }

        userEntity=new UserEntity();
        userEntity.setUsername(authenticationRequest.getUsername());
        userEntity.setPassword(bCryptPasswordEncoder.encode(authenticationRequest.getPassword()));
        userEntity=userRepository.saveAndFlush(userEntity);

        return userEntity;
    }catch (Exception e){
        System.out.println(e.getMessage());
    }
    return  null;
    }

    public void signOut(String token) {
        if(token==null){
            throw  new RuntimeException("Invalid token");

        }

        Claims claims= jwtTokenUtils.parseToken(token);

        if(claims==null){
            throw  new RuntimeException("Invalid token");
        }

        String userDetailKey=RedisKeyManager.detailKey(RedisKeyTypes.USER_DETAIL.toString(),Long.parseLong(claims.getId()));

        redisStoreRepository.deleteFromRedisStore(userDetailKey);


    }

}
