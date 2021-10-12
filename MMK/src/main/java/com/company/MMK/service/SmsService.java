package com.company.MMK.service;

import com.company.MMK.entity.Sms;
import com.company.MMK.error.ParameterInvalidException;
import com.company.MMK.error.ParameterMissingException;
import com.company.MMK.error.ParameterNotFoundException;

public interface SmsService {
    void validateInbound(Sms sms) throws ParameterMissingException, ParameterInvalidException, ParameterNotFoundException, InterruptedException;
    void validateOutbound(Sms sms) throws ParameterMissingException, ParameterInvalidException, ParameterNotFoundException;
}
