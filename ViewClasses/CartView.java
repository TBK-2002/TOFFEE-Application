package ViewClasses;

import ModelsClasses.Account;

public class CartView {
    private Account account;

    public CartView(Account account) {
        this.account = account;
    };

    public void setAccount(Account account) {
        this.account = account;
    };

    public void viewCart() {
        System.out.println("Items in cart: ");
        int sum = 0;
        System.out.println("-----------");
        for (int i = 0; i < account.getCartProducts().size(); i++) {
            System.out.println(
                    account.getCartProducts().get(i).getName() + " - " + account.getCartProducts().get(i).getPrice());
            sum += account.getCartProducts().get(i).getPrice();
        }
        System.out.println("-----------");
        System.out.println("Total: " + sum);
    }

}
