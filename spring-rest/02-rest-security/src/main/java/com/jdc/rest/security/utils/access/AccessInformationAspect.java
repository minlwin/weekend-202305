package com.jdc.rest.security.utils.access;

import java.time.LocalDateTime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.jdc.rest.security.model.dto.MemberDto;
import com.jdc.rest.security.model.dto.SignInForm;
import com.jdc.rest.security.model.dto.SignInResult;
import com.jdc.rest.security.model.entity.AccessHistory;
import com.jdc.rest.security.model.entity.AccessHistory.AccessStatus;
import com.jdc.rest.security.model.entity.AccessHistoryPk;
import com.jdc.rest.security.model.repo.AccessHistoryRepo;
import com.jdc.rest.security.model.repo.MemberRepo;

@Aspect
@Configuration
public class AccessInformationAspect {
	
	@Autowired
	private AccessHistoryRepo accessHistoryRepo;
	@Autowired
	private MemberRepo memberRepo;

	@Pointcut("@annotation(com.jdc.rest.security.utils.access.AccessLog)")
	public void accessMethod() {}
	
	@Around(value = "accessMethod()")
	public Object onSuccess(ProceedingJoinPoint joinPoint) throws Throwable{
		Object result = null;
		try {
			result = joinPoint.proceed();
			// Login Success Operation
			var form = getArgument(joinPoint);
			if(null != form) {
				saveHistory(true, form);
				var member = memberRepo.findById(form.username()).orElseThrow();
				member.getAccess().setLastLoginAt(LocalDateTime.now());
				member.getAccess().setLastLoginStatus(AccessStatus.Success);

				if(null == member.getAccess().getFirstLoginAt()) {
					member.getAccess().setFirstLoginAt(LocalDateTime.now());
				}
				
				if(result instanceof SignInResult signInResult) {
					return signInResult.withUser(form.username(), MemberDto.withMember(member));
				}
			}
		} catch (Throwable e) {
			// Login Failure Operation
			var form = getArgument(joinPoint);
			if(null != form) {
				saveHistory(false, form);
				
				memberRepo.findById(form.username()).ifPresent(member -> {
					member.getAccess().setLastLoginAt(LocalDateTime.now());
					member.getAccess().setLastLoginStatus(AccessStatus.Error);
					
					if(null == member.getAccess().getFirstLoginAt()) {
						member.getAccess().setFirstLoginAt(LocalDateTime.now());
					}
				});
			}
			throw e;
		}
		
		return result;
	}
	
	private void saveHistory(boolean success, SignInForm form) {
		var pk = new AccessHistoryPk();
		pk.setMemberId(form.username());
		pk.setAccessAt(LocalDateTime.now());
		
		var history = new AccessHistory();
		history.setId(pk);
		history.setStatus(success ? AccessStatus.Success : AccessStatus.Error);
		accessHistoryRepo.save(history);
	}
	
	private SignInForm getArgument(ProceedingJoinPoint joinPoint) {
		var args = joinPoint.getArgs();
		
		if(args.length > 0 && args[0] instanceof SignInForm form) {
			return form;
		}
		
		return null;
	}
}
