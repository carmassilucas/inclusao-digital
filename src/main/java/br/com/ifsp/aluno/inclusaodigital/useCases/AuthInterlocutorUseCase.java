package br.com.ifsp.aluno.inclusaodigital.useCases;

import br.com.ifsp.aluno.inclusaodigital.dtos.AuthInterlocutorRequestDto;
import br.com.ifsp.aluno.inclusaodigital.dtos.AuthInterlocutorResponseDto;
import br.com.ifsp.aluno.inclusaodigital.exceptions.InterlocutorNotFoundException;
import br.com.ifsp.aluno.inclusaodigital.repositories.InterlocutorRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;

@Service
public class AuthInterlocutorUseCase {
    private final InterlocutorRepository interlocutorRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("authentication.jwt.issuer")
    private String authenticateJwtIssuer;

    @Value("authentication.algorithm.secret")
    private String authenticateAlgorithmSecret;

    public AuthInterlocutorUseCase(
            InterlocutorRepository interlocutorRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.interlocutorRepository = interlocutorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthInterlocutorResponseDto execute(AuthInterlocutorRequestDto authInterlocutorRequestDto)
            throws AuthenticationException {
        var interlocutor = this.interlocutorRepository.findByEmail(authInterlocutorRequestDto.email())
                .orElseThrow(InterlocutorNotFoundException::new);

        var passwordsMatch = passwordEncoder.matches(
                authInterlocutorRequestDto.password(),
                interlocutor.getPassword()
        );

        if (!passwordsMatch)
            throw new AuthenticationException("Usuário ou senha incorreto");

        var algorithm = Algorithm.HMAC256(authenticateAlgorithmSecret);
        var expiresIn = Instant.now().plus(Duration.ofHours(3));

        var token = JWT.create()
                .withIssuer(authenticateJwtIssuer)
                .withSubject(interlocutor.getUuid().toString())
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        return AuthInterlocutorResponseDto.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();
    }
}
