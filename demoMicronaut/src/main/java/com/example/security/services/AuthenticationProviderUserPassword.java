package com.example.security.services;

import com.example.models.User;
import com.example.repository.UserRepository;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

//AuthenticationProvider mô phỏng xác thực người dùng
//authenticationRequest - Yêu cầu xác thực
//Publisher<AuthenticationResponse>	authenticate(AuthenticationRequest authenticationRequest)Xác thực người dùng với yêu cầu nhất định.
@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider {
    @Inject
    UserRepository userRepository;
    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest,
                                                          AuthenticationRequest<?, ?> authenticationRequest) {
        return Flux.create(emitter -> {
            User user = userRepository.findByUsername()
            if (authenticationRequest.getIdentity().equals("sherlock") &&
                    authenticationRequest.getSecret().equals("password")) {
                emitter.next(AuthenticationResponse.success((String) authenticationRequest.getIdentity()));
                emitter.complete();
            } else {
                emitter.error(AuthenticationResponse.exception());
            }
        }, FluxSink.OverflowStrategy.ERROR);
    }
}
