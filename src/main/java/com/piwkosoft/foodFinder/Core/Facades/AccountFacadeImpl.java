package com.piwkosoft.foodFinder.Core.Facades;

import com.piwkosoft.foodFinder.Converters.Converter;
import com.piwkosoft.foodFinder.Dto.AccountPlanDTO;
import com.piwkosoft.foodFinder.Core.Facades.Interfaces.AccountFacade;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.AccountPlanEntity;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.AccountPlanEntity.AccountPlan;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.UserEntity;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.AccountService;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.UserService;
import org.springframework.stereotype.Component;

@Component
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
