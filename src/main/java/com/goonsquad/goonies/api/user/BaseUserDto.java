package com.goonsquad.goonies.api.user;

import com.goonsquad.goonies.api.user.role.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class BaseUserDto {

    private final String username;
    private final String profileImageReference;
    private List<String> roles = new ArrayList<>();
    private final UserStatus status;

    public BaseUserDto(User user) {
        this.username = user.getUsername();
        this.profileImageReference = user.getProfileImageReference();
        this.roles = getRoles(user.getRoles());
        this.status = user.getStatus();
    }

    private List<String> getRoles(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }
}
