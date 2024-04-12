package view;

public class Main {

    public static void main(String[] args) {

        FrmProgressBar.main(args);
        PostInit p = new PostInit();
        p.saveEmpresa();
        p.saveRole();
        p.saveUser();
        p.saveFornecedor();

    }

}
