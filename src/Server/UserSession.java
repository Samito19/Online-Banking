package Server;

import java.util.List;

public class UserSession {

    private String name;
    private int accountNumber;
    private int CBalance;
    private int SBalance;

    public UserSession(List user_information){

        this.name = (String) user_information.get(0);
        this.accountNumber = Integer.parseInt((String) user_information.get(1));
        this.CBalance = Integer.parseInt((String) user_information.get(2));
        this.SBalance = Integer.parseInt((String) user_information.get(3));

    }

    public String getName(){
        return name;
    }
    public int getAccountNumber(){
        return accountNumber;
    }
    public int getCBalance(){
        return CBalance;
    }
    public int getSBalance(){
        return SBalance;
    }
}
