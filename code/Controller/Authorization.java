package Controller;

import Clients.ClientsService;

public class Authorization {

    public static int authenticate(String login,String password,int check){
        String log="admin";
        String pas="admin123";
        if(check==1){
            switch (ClientsService.logIn(login,password)){
                case 0:
                    return 0;
                case 1:
                    return 1;
                case 2:
                    return 2;
            }
        }
        else {
            if(login.equals(log)&&password.equals(pas)){
                return 4;
            }
        }
        return 0xFFFFFFFF;
    }
}
