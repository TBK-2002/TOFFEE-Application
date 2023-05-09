package Controller;

import java.util.HashMap;
import ModelsClasses.Account;
import java.io.FileReader;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Iterator;
import ViewClasses.AuthenticationView;

public class Authentication {
    private HashMap<String, Account> accounts;

    private AuthenticationView authenticationView;

    private TOFFEE toffee;

    public Authentication(TOFFEE toffee) {
        this.accounts = new HashMap<String, Account>();
        this.authenticationView = new AuthenticationView(this);
        this.toffee = toffee;
        loadmap();
    }

    public void loadmap() {
        try {
            Scanner scanner = new Scanner(new FileReader("jsonFiles/accounts.json"));

            // Use the nextLine() method to read the contents of the file line by line.
            StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine());
            }

            // Convert the StringBuilder object to a String object.
            String json = stringBuilder.toString();

            JSONObject jsonObject = new JSONObject(json);
            Iterator<String> keys = jsonObject.keys();

            while (keys.hasNext()) {
                String key = keys.next();
                if (jsonObject.get(key) instanceof JSONObject) {
                    jsonObject = jsonObject.getJSONObject(key);
                    int id = jsonObject.getInt("id");
                    // String name = jsonObject.getString("name");
                    String email = jsonObject.getString("email");
                    String password = jsonObject.getString("password");
                    String address = jsonObject.getString("address");
                    double loyaltyPoints = jsonObject.getDouble("loyaltyPoints");
                    Account account = new Account(email, password, address);
                    this.accounts.put(email, account);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String sendOTP(int id) {
        Account account = this.accounts.get(id);
        String otp = "1234";
        if (account != null) {
            System.out.println("OTP sent to " + account.getEmail());
            return otp;
        }
        return null;
    }

    public Boolean verifyOTP(int id, String otp) {
        Account account = this.accounts.get(id);
        if (account != null) {
            if (otp.equals(sendOTP(id))) {
                return true;
            }
        }
        return false;
    }

    public Boolean checkAccount(int id) {
        Account account = this.accounts.get(id);
        if (account != null) {
            return true;
        }
        return false;
    }

    public void login() {
        String email = "";
        String password = "";
        authenticationView.setDataforLogin(email, password);
        Account account = this.accounts.get(email);
    }

    public boolean validateEmail(String email) {
        if (email.contains("@") && email.contains(".") && this.accounts.get(email) != null) {
            return true;
        }
        return false;
    }

    public boolean validatePassword(String password, String email) {
        if (password.length() >= 4 && this.accounts.get(email).getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public Boolean signUp() {
        String email = "";
        String password = "";
        String address = "";
        authenticationView.setDataforRegister(email, password, address);
        Account account = this.accounts.get(email);
        if (account == null) {
            account = new Account(email, password, address);
            this.accounts.put(email, account);
            toffee.setAccount(account);
            return true;
        } else {
            System.out.println("Account already exists");
            return false;
        }
    }
}
