import GUI.MainMenu;

public class Main {
    public static void main(String[]main){
        try {
            Init.createSchema();
        } catch (Exception e){
            System.out.println("Erro ao inicializar a database: " + e);
        }
        MainMenu.main();
    }
}

