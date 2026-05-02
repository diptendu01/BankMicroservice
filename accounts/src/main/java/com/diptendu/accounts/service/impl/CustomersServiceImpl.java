package com.diptendu.accounts.service.impl;

import com.diptendu.accounts.dto.AccountsDto;
import com.diptendu.accounts.dto.CardsDto;
import com.diptendu.accounts.dto.CustomerDetailsDto;
import com.diptendu.accounts.dto.LoansDto;
import com.diptendu.accounts.entity.Accounts;
import com.diptendu.accounts.entity.Customer;
import com.diptendu.accounts.exception.ResourceNotFoundException;
import com.diptendu.accounts.mapper.AccountsMapper;
import com.diptendu.accounts.mapper.CustomerMapper;
import com.diptendu.accounts.repository.AccountsRepository;
import com.diptendu.accounts.repository.CustomerRepository;
import com.diptendu.accounts.service.ICustomersService;
import com.diptendu.accounts.service.client.CardsFeignClient;
import com.diptendu.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private LoansFeignClient loansFeignClient;
    private CardsFeignClient cardsFeignClient;


    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer= customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );
        Accounts accounts= accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()->new ResourceNotFoundException("Accounts","customerId",String.valueOf(customer.getCustomerId()))
        );
        CustomerDetailsDto customerDetailsDto= CustomerMapper.mapToCustomerDetailsDto(customer,new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity =loansFeignClient.fetchLoanDetails(correlationId,mobileNumber);
        if (null != loansDtoResponseEntity){
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        }
        ResponseEntity<CardsDto> cardsDtoResponseEntity =cardsFeignClient.fetchCardDetails(correlationId,mobileNumber);
        if(null != cardsDtoResponseEntity){
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }
        return customerDetailsDto;
    }
}
