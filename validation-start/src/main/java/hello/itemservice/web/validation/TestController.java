package hello.itemservice.web.validation;

import hello.itemservice.web.validation.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class TestController {

    @PostMapping("/user")
    public ResponseEntity<String> savePost(@Valid @RequestBody UserDto userDto) {
        log.info(userDto.toString());
        return ResponseEntity.ok().body("request 객체 검증 성공");
    }
}
