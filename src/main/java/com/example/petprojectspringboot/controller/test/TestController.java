    package com.example.petprojectspringboot.controller.test;

    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class TestController {
        @GetMapping("/public/helloworld")
        public ResponseEntity<?> publicMethod() {
            return ResponseEntity.ok("Hello World");
        }

        @GetMapping("/private")
        public String privateMethod() {
            return "private";
        }
    }
