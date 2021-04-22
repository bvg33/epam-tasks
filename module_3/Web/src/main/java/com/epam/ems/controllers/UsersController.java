package com.epam.ems.controllers;

import com.epam.ems.dto.lists.CertificateList;
import com.epam.ems.dto.Tag;
import com.epam.ems.dto.lists.UserList;
import com.epam.ems.dto.lists.UserOrderInfoList;
import com.epam.ems.hateoas.UsersControllerHateoas;
import com.epam.ems.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Validated
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService service;

    @Autowired
    private UsersControllerHateoas hateoas;

    @GetMapping
    public ResponseEntity<UserList> getAllUsers(@Min(1) @RequestParam int page,
                                                @Min(1) @RequestParam int elements) {
        UserList users = new UserList(service.getAllUsers(page, elements));
        hateoas.createHateoas(users);
        return ResponseEntity.status(OK).body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificateList> getUserCertificates(@Min(1) @PathVariable int id,
                                                               @Min(1) @RequestParam int page,
                                                               @Min(1) @RequestParam int elements) {
        CertificateList list = new CertificateList(service.getUserCertificates(id, page, elements));
        hateoas.createHateoas(list);
        return ResponseEntity.status(OK).body(list);
    }

    @PatchMapping("/{userId}/{certificateId}")
    public ResponseEntity<RepresentationModel> addCertificatesToUser(@Min(1) @PathVariable int userId,
                                                                     @Min(1) @PathVariable int certificateId) {
        RepresentationModel model = new RepresentationModel();
        hateoas.createHateoas(model);
        service.addCertificateToUser(userId, certificateId);
        return ResponseEntity.status(CREATED).body(model);
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<UserOrderInfoList> getUserOrderInfo(@Min(1) @PathVariable int id,
                                                              @Min(1) @RequestParam int page,
                                                              @Min(1) @RequestParam int elements) {
        UserOrderInfoList orderInfo = new UserOrderInfoList(service.getUserOrdersInfo(id, page, elements));
        hateoas.createHateoas(orderInfo);
        return ResponseEntity.status(OK).body(orderInfo);
    }

    @GetMapping("/widelyUsedTag")
    public ResponseEntity<Tag> getTheMostWidelyUsedTag() {
        Tag tag = service.theMostWidelyUsedTag();
        hateoas.createHateoas(tag);
        return ResponseEntity.status(OK).body(tag);
    }

}
