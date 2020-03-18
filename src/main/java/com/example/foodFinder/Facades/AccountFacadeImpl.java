package com.example.foodFinder.Facades;

import com.example.foodFinder.Converters.Converter;
import com.example.foodFinder.Dto.AccountPlanDTO;
import com.example.foodFinder.Facades.Interfaces.AccountFacade;
import com.example.foodFinder.Persistance.Entities.AccountPlanEntity;
import com.example.foodFinder.Persistance.Entities.AccountPlanEntity.AccountPlan;
import com.example.foodFinder.Persistance.Entities.UserEntity;
import com.example.foodFinder.Services.Interfaces.AccountService;
import com.example.foodFinder.Services.Interfaces.UserService;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

@Service
public class AccountFacadeImpl implements AccountFacade {

    private final UserService userService;
    private final AccountService accountService;
    private final Converter<AccountPlanDTO, AccountPlanEntity> converter;

    public AccountFacadeImpl(
        final UserService userService,
        final AccountService accountService,
        final Converter<AccountPlanDTO, AccountPlanEntity> converter) {
        this.userService = userService;
        this.accountService = accountService;
        this.converter = converter;
    }

    @Override
    public AccountPlanDTO getUserAccountPlan(Long userId) {
        UserEntity userEntity = userService.findById(userId);
        return converter.convert(userEntity.getAccountPlan());
    }

    @Override
    public AccountPlanDTO findAccountPlanByName(AccountPlan accountPlan) {
        AccountPlanEntity accountPlanEntity = accountService.findAccountPlanByName(accountPlan);
        if(accountPlanEntity == null) {
            return null;
        }
        return converter.convert(accountPlanEntity);
    }
}
