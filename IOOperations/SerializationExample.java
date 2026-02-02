import java.io.*;
import java.nio.ByteBuffer;

class BufferDemo{
    public void BufferMethod() {
        ByteBuffer buffer = ByteBuffer.allocate(10);


        buffer.put((byte) 10);
        buffer.put((byte) 20);
        buffer.put((byte) 30);
        buffer.put((byte) 40);

        buffer.flip();

        while(buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
        buffer.clear();
    }
}

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


        //Serialization ===> converting object in to byte output stream --> writing
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

        new BufferDemo().BufferMethod();
    }
}
