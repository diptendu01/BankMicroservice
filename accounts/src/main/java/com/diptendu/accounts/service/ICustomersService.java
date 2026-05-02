package com.diptendu.accounts.service;

import com.diptendu.accounts.dto.CustomerDetailsDto;

public interface ICustomersService {

    CustomerDetailsDto fetchCustomerDetails (String mobileNumber, String correlationId);
}
