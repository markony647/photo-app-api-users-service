package com.appsdeveloperblog.photoapp.api.users.users.service;

import com.appsdeveloperblog.photoapp.api.users.users.data.UserEntity;
import com.appsdeveloperblog.photoapp.api.users.users.data.UsersRepository;
import com.appsdeveloperblog.photoapp.api.users.users.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

@Service
public class UsersServiceImpl implements UsersService {

    UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(STRICT);
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setEncryptedPassword("test");
        usersRepository.save(userEntity);
        return userDto;
    }
}
