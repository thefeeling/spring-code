package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by Daniel on 2017. 7. 8..
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    /**
     * See Reference PageableDefault {@link <a href="http://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/web/PageableDefault.html"></a>}
     * See Example Code {@link <a href="http://www.programcreek.com/java-api-examples/index.php?api=org.springframework.data.web.PageableDefault"></a>}
     *
     * @param pageRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Page<User> list(
        @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageRequest
    ) {
        return userRepository.findAll(pageRequest);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> detail(
        @PathVariable Optional<Long> id
    ) {
        User user = Optional.ofNullable(userRepository.findOne(id.get()))
            .orElseThrow(() -> new NotFoundException("ID값에 대응하는 유저가 없습니다"));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> create(
        @Valid @RequestBody User user,
        BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            throw new ValidationException("유효성 검사 실패");
        }
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(
        @PathVariable long id,
        @Valid @RequestBody User user,
        BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            log.error(bindingResult.getAllErrors().toString());
            throw new ValidationException("유효성 검사 실패");
        }

        User existUser = userRepository.findOne(id);
        existUser.setName(user.getName());
        existUser.setAge(user.getAge());
        existUser.setAddress(user.getAddress());
        existUser.setGender(user.getGender());
        existUser.setCountSex(user.getCountSex());
        existUser.setMoSol(user.isMoSol());
        userRepository.save(existUser);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Optional<Long> id) {
        if (userRepository.exists(id.get()) == false) {
            Optional.empty().orElseThrow(() -> new NotFoundException("ID값에 대응하는 유저가 없습니다"));
        }
        userRepository.delete(id.get());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
