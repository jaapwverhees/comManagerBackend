package competitie.manager.dto;

import competitie.manager.models.user.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {
    private String email;
    private String token;

    public static UserResponse of(User user, String token){
        return UserResponse.builder()
                .email(user.getEmail())
                .token(token)
                .build();
    }
}
