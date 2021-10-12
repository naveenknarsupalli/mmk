package com.company.MMK.controller;

import com.company.MMK.entity.ErrorMessage;
import com.company.MMK.entity.Sms;
import com.company.MMK.error.ParameterInvalidException;
import com.company.MMK.error.ParameterMissingException;
import com.company.MMK.error.ParameterNotFoundException;
import com.company.MMK.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController {

    @Autowired
    SmsService smsService;

    @PostMapping("/inbound/sms")
    public ResponseEntity<ErrorMessage> inboundSms(@RequestBody Sms sms) throws
            ParameterMissingException,
            ParameterInvalidException,
            ParameterNotFoundException, InterruptedException {
        smsService.validateInbound(sms);
        return ResponseEntity.ok(new ErrorMessage("inbound sms ok", ""));
    }

    @PostMapping("/outbound/sms")
    public ResponseEntity<ErrorMessage> outboundSms(@RequestBody Sms sms) throws
            ParameterMissingException,
            ParameterInvalidException,
            ParameterNotFoundException {
        smsService.validateOutbound(sms);
        return ResponseEntity.ok(new ErrorMessage("outbound sms ok", ""));
    }
}