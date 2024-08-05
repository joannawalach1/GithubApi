package com.atipera.githubApi.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GithubRepository {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Name of Repo is mandatory")
    private String name;
    @NotNull(message = "User of Repo is mandatory")
    private GithubUser user;
    private List<GithubBranch> branches = new ArrayList<>();
    private boolean fork;
}
