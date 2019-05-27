package me.daniel.kotlinspringbootmapstruct.user;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import me.daniel.kotlinspringbootmapstruct.user.UserDTO.CreateDTO;
import me.daniel.kotlinspringbootmapstruct.user.UserDTO.ResponseDTO;
import me.daniel.kotlinspringbootmapstruct.user.UserDTO.UserMapper;
import me.daniel.kotlinspringbootmapstruct.user.UserRole.Role;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-26T20:22:57+0900",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_65 (Oracle Corporation)"
)
@Component
public class UserDTO$UserMapperImpl implements UserMapper {

    @Override
    public User toUser(CreateDTO req) {
        if ( req == null ) {
            return null;
        }

        User user = new User();

        user.set_roles( toUserRole( req.getRoles() ) );
        user.setName( req.getName() );
        user.setEmail( req.getEmail() );
        user.setGender( req.getGender() );
        user.setForeigner( req.getForeigner() );
        if ( user.getRoles() != null ) {
            List<String> list1 = roleListToStringList( req.getRoles() );
            if ( list1 != null ) {
                user.getRoles().addAll( list1 );
            }
        }

        return user;
    }

    @Override
    public ResponseDTO fromUser(User user) {
        if ( user == null ) {
            return null;
        }

        ResponseDTO responseDTO = new ResponseDTO();

        responseDTO.setId( user.getId() );
        responseDTO.setName( user.getName() );
        responseDTO.setEmail( user.getEmail() );
        responseDTO.setGender( user.getGender() );
        List<String> list = user.getRoles();
        if ( list != null ) {
            responseDTO.setRoles( new ArrayList<String>( list ) );
        }
        responseDTO.setForeigner( user.getForeigner() );
        responseDTO.setCreatedAt( user.getCreatedAt() );
        responseDTO.setUpdatedAt( user.getUpdatedAt() );

        return responseDTO;
    }

    @Override
    public List<UserRole> toUserRole(List<? extends Role> roles) {
        if ( roles == null ) {
            return null;
        }

        List<UserRole> list = new ArrayList<UserRole>( roles.size() );
        for ( Role role : roles ) {
            list.add( roleToUserRole( role ) );
        }

        return list;
    }

    protected List<String> roleListToStringList(List<Role> list) {
        if ( list == null ) {
            return null;
        }

        List<String> list1 = new ArrayList<String>( list.size() );
        for ( Role role : list ) {
            list1.add( role.name() );
        }

        return list1;
    }

    protected UserRole roleToUserRole(Role role) {
        if ( role == null ) {
            return null;
        }

        UserRole userRole = new UserRole();

        userRole.setRole( role );

        return userRole;
    }
}
