package com.madadgaar.foundation.service;

import com.madadgaar.foundation.configuration.MemberDetailsImpl;
import com.madadgaar.foundation.exceptions.UnivestException;
import com.madadgaar.foundation.repository.MemberRegisterRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SimpleUserDetailsService implements UserDetailsService {

    @NonNull
    private final MemberRegisterRepository memberRegisterRepository;

    @Override()
    @Cacheable(cacheNames = "loadUserByUsername")
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return memberRegisterRepository.findById(Long.valueOf(userId))
                             .map(MemberDetailsImpl::of)
                             .orElseThrow(() -> new UnivestException(
                                     String.format("A user with provided ID %s does not exists.", userId)));
    }
}
