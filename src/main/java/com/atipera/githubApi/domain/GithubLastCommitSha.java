package com.atipera.githubApi.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GithubLastCommitSha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "SHA must not be null")
    private String sha;
    @NotNull(message = "URL must not be null")
    private String url;
}
