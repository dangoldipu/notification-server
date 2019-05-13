package com.javasampleapproach.fcm.pushnotif.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import com.javasampleapproach.fcm.pushnotif.service.AndroidPushNotificationsService;

@CrossOrigin
@RestController
public class WebController {
    //token generated by browser.
    private String TOKEN;
    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;

    @RequestMapping(value = "/send", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> send() throws JSONException {
            System.out.println("inResource"+TOKEN);
            JSONObject body = new JSONObject();
            body.put("to", "c8ykE2vZCF0:APA91bGQQDODV_TFi3YRLigcttSdGQoeB1hM2vfD_O7Ktnn6GeNrFR6aJD7GxLSvBKvdMWrgJ-qdlFD5IcXAVTEm976ji5hwQJKjgmzkBcyeUhwZ7V0U18l9xEOMTrBhaC8pb7NOSC-G");
            body.put("priority", "high");

            JSONObject notification = new JSONObject();
            notification.put("title", "Custom notification");
            notification.put("body", "notif"+System.currentTimeMillis());

            notification.put("click_action","https://maps.google.com");

            body.put("notification", notification);


            HttpEntity<String> request = new HttpEntity<>(body.toString());
        System.out.println(request);

            CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
            CompletableFuture.allOf(pushNotification).join();

            try {
                String firebaseResponse = pushNotification.get();

                return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public String register (@RequestBody String token){
       this.TOKEN = token;
        return token;
    }
}