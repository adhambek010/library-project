//package com.example.library.weblibrary.config.disable.auditing;
//
//import com.example.library.weblibrary.user.database.entities.UserEntity;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import java.util.Optional;
//
//public class ApplicationAuditAware implements AuditorAware<String> {
//
//    @Override
//    public Optional<String> getCurrentAuditor() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
//            return Optional.empty();
//        }
//
//        UserEntity userPrincipal = (UserEntity) authentication.getPrincipal();
//        return Optional.of(userPrincipal.getIdentifier());
//    }
//}
