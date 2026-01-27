import java.io.*;

class UserAccount implements Serializable {
    private static final long serialVersionUID = 1L; // Recommended

    private String username;
    private transient String password; // Will NOT be saved
    private int level;

    public UserAccount(String u, String p, int l) {
        this.username = u; this.password = p; this.level = l;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){return  password;}
}
public class SerializationExample {
    public static void main(String[] args){
        UserAccount user = new UserAccount("Kiran", "secret123", 50);


        //Serialization ===> converting object into byte output stream --> writing
        try{
            ObjectOutputStream oob = new ObjectOutputStream(new FileOutputStream(("user.ser")));
            oob.writeObject(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //De-Serialization  ==> converting bytes into object --> reading

        try{
            ObjectInputStream obi = new ObjectInputStream(new FileInputStream("user.ser"));
            UserAccount savedUser = (UserAccount)  obi.readObject();

            System.out.println("User: " + savedUser.getUsername());
            System.out.println("Password: " + savedUser.getPassword());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
