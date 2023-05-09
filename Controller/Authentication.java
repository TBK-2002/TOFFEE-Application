package Controller;
import java.util.HashMap;

import ModelsClasses.Account;

public class Authentication {
    private HashMap<Integer, Account> accounts;
    Authentication(){
        this.accounts = new HashMap<Integer, Account>();
    }
    public String sendOTP(int id) {
        Account account = this.accounts.get(id);
        String otp = "1234";
        if(account != null) {
            System.out.println("OTP sent to " + account.getEmail());
            return otp;
        }
        return null;
    }
  public Boolean verifyOTP(int id, String otp) {
        Account account = this.accounts.get(id);
        if(account != null) {
            if(otp.equals(sendOTP(id))){
                   return true;
            }
        }
        return false;
    }
    public Boolean checkAccount(int id) {
        Account account = this.accounts.get(id);
        if(account != null) {
            return true;
        }
        return false;
    }
    public Boolean login(int id, String password, String email) {
        Account account = this.accounts.get(id);
        if(account != null) {
            if(account.getPassword().equals(password) && account.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
    public Boolean signUp(int id, String password, String email, String address) {
        Account account = this.accounts.get(id);
        if(account == null) {
            Account newAccount = new Account(id, email, password, address);
            this.accounts.put(id, newAccount);
            return true;
        }
        else{
            System.out.println("Account already exists");
            return false;
        }
    }
    public void loadAccounts() {
        // load accounts from database
        // mock data
        Account a1 = new Account(1, "nour@gmail.com", "1234", "Cairo");
        Account a2 = new Account(2, "ahmed@gmail.com", "1234", "Cairo");
        Account a3 = new Account(3, "mohanad@gmail.com", "1234", "Cairo");
        this.accounts.put(1, a1);
        this.accounts.put(2, a2);
        this.accounts.put(3, a3);
    }
}
