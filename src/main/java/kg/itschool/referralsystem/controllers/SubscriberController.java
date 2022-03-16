package kg.itschool.referralsystem.controllers;

import kg.itschool.referralsystem.services.SubscriberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/subscriber")
public class SubscriberController {

    private SubscriberService subscriberService;

    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @GetMapping("/permission")
    public ResponseEntity<?> changePermission(@RequestParam Long subsId, @RequestParam boolean value){
        return subscriberService.changePermission(subsId,value);
    }

}
