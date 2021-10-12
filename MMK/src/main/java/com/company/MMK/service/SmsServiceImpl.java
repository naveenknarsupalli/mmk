package com.company.MMK.service;

import com.company.MMK.entity.Sms;
import com.company.MMK.error.ParameterInvalidException;
import com.company.MMK.error.ParameterMissingException;
import com.company.MMK.error.ParameterNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    PhoneNumberService phoneNumberService;

    @Autowired
    AccountService accountService;

    //    @Cacheable(key = "#sms.from + #sms.to",
//            value = "inboundCache",
//            condition = "#sms.text == 'STOP' or " +
//                    "#sms.text == 'STOP\\n' or " +
//                    "#sms.text == 'STOP\\r' or " +
//                    "#sms.text == 'STOP\\r\\n'")
    @Override
    public void validateInbound(Sms sms) throws ParameterMissingException, ParameterInvalidException, ParameterNotFoundException, InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        validate(sms);
        if (!phoneNumberService.contains(sms.getTo(), getCurrentAccountId()))
            throw new ParameterNotFoundException("", "to parameter not found");
    }

    @Override
    public void validateOutbound(Sms sms) throws ParameterMissingException, ParameterInvalidException, ParameterNotFoundException {
        validate(sms);
        if (!phoneNumberService.contains(sms.getFrom(), getCurrentAccountId()))
            throw new ParameterNotFoundException("", "from parameter not found");
    }

    private void validate(Sms sms) throws ParameterMissingException, ParameterInvalidException {
        String[] params = {"from", "to", "text"};
        String[] inputParams = {sms.getFrom(), sms.getTo(), sms.getText()};

        List<String> errors = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < params.length; i++) {
            if (inputParams[i] == null) {
                count++;
                errors.add(params[i] + " is missing");
            }
        }
        if (count > 0) throw new ParameterMissingException("", errors);

        errors.clear();
        count = 0;
        for (int i = 0; i < params.length; i++) {
            if (((inputParams[i].length() < 6 || inputParams[i].length() > 16) && i != 2) ||
                    ((inputParams[i].length() < 1 || inputParams[i].length() > 120) && i == 2)) {
                count++;
                errors.add(params[i] + " is invalid");
            }
        }
        if (count > 0) throw new ParameterInvalidException("", errors);
    }

    private Long getCurrentAccountId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        return accountService.getAccountIdByUserName(username);
    }
}
