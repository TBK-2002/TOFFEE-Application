package Controller;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import org.json.JSONObject;
import ModelsClasses.Account;
import ViewClasses.AuthenticationView;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
                    JSONObject accObject = jsonObject.getJSONObject(key);
                    // int id = accObject.getInt("id");
                    // String name = accObject.getString("name");
                    String email = accObject.getString("email");
                    String password = accObject.getString("password");
                    String address = accObject.getString("address");
                    double loyaltyPoints = accObject.getDouble("loyaltyPoints");
                    Account account = new Account(email, password, address);
                    this.accounts.put(email, account);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String sendOTP(String email) {
        String to = email;
        String from = "toffeeappotp@gmail.com";
        String password = "dmqyfszeyqedwzgl";
        // Set properties for the email server
        Properties props = new Properties();
        //props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.host", "smtp.gmail.com"); // use your email server's SMTP host
        props.put("mail.smtp.port", "587"); // use your email server's SMTP port
        props.put("mail.smtp.auth", "true"); // enable SMTP authentication
        props.put("mail.smtp.starttls.enable", "true"); // enable TLS encryption
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        String otp = "";
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            for (int i = 0; i < 6; i++) {
                otp += (int) (Math.random() * 10);
            }
            message.setSubject("OTP for TOFFEE");
            message.setText("Your OTP is " + otp);
            Transport.send(message);
        } catch (Exception e) {
            System.out.println(e);
        }
        return otp;
    }

    public Boolean verifyOTP(String email) {
        String otp1 = sendOTP(email);
        String otp = authenticationView.set_otp("");
        if (otp.equals(otp1)) {
            return true;
        }
        return false;
    }

    public Boolean checkAccount(String email) {
        Account account = this.accounts.get(email);
        if (account != null) {
            return true;
        }
        return false;
    }

    public void login() {
        String email = "";
        email = authenticationView.setDataforLogin();
        Account account = this.accounts.get(email);
        toffee.setAccount(account);
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

    public void toJSON(Account acc) {
        File file = new File("jsonFiles/accounts.json");
        try {
            JSONObject jsonObject = new JSONObject();
            for (String key : this.accounts.keySet()) {
                Account account = this.accounts.get(key);
                JSONObject accountObject = new JSONObject();
                accountObject.put("email", account.getEmail());
                accountObject.put("password", account.getPassword());
                accountObject.put("address", account.getAddress());
                accountObject.put("loyaltyPoints", account.getLoyaltyPoints());
                jsonObject.put(key, accountObject);

            }

            System.out.println(jsonObject.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Boolean signUp() {
        String email = "";
        String password = "";
        String address = "";
        Account account = null;
        account = authenticationView.setDataforRegister(email, password, address);
        if (accounts.get(account.getEmail()) == null && verifyOTP(account.getEmail())) {
            toJSON(account);
            this.accounts.put(email, account);
            toffee.setAccount(account);
            return true;
        } else if(accounts.get(account.getEmail()) != null) {
            System.out.println("Account already exists");
            return false;
        }
        else{
            System.out.println("Invalid OTP");
            return false;
        }

    }
}