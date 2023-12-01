package com.pokemongame.service;

import com.pokemongame.model.UserModel;
import com.pokemongame.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepo usersRepo;

    public UserService(UsersRepo usersRepo){
        this.usersRepo = usersRepo;
    }
    public UserModel registerUser(String login, String password){
        if(login != null && password != null) {
            return null;
        }
        else {
            if(usersRepo.findFirstByLogin(login).isPresent()){
                System.out.println("Duplicate Login");
                return null;
            }
            UserModel usersModel = new UserModel();
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            return usersRepo.save(usersModel);
        }
    }

    public UserModel authenticate(String login, String password){
        return usersRepo.findByLoginAndPassword(login, password).orElse( null);
    }
}
