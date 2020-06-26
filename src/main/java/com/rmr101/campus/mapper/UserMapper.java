package com.rmr101.campus.mapper;

import com.rmr101.campus.dto.user.UserDto;
import com.rmr101.campus.dto.user.UserPostRequest;
import com.rmr101.campus.dto.user.UserPostResponse;
import com.rmr101.campus.dto.user.UserPutRequest;
import com.rmr101.campus.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserPostResponse userToUserPostResponse(User user);
    User userPostRquestToUser(UserPostRequest userPostRequest);
}
