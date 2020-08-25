package com.appsdeveloperblog.photoapp.api.users.users.data;

import com.appsdeveloperblog.photoapp.api.users.users.shared.UserDto;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);
    UserDto getUserDetailsByEmail(String email);
}
