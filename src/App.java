public class App {
    public static void main(String[] args) throws Exception {

        IDandPasswords idandPasswords = new IDandPasswords();

        new LoginPage(idandPasswords.getLoginInfo());

        // new Buttons();
    }
}
