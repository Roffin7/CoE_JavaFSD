package Task1;

import java.io.*;
import java.util.*;
class User2
{
    private String name;
    private String email;
    public User2(String name, String email)
    {
        this.name = name;
        this.email = email;
    }
    @Override
    public String toString() 
    {
        return name + "," + email;
    }
    public static User2 fromString(String data)
    {
        String[] parts = data.split(",");
        return new User2(parts[0], parts[1]);
    }
}
class UserMan
{
    private List<User2> users = new ArrayList<>();
    public void addUser(String name, String email)
    {
        users.add(new User2(name, email));
    }
    public void saveUsersToFile(String filename)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename)))
        {
            for (User2 user : users)
            {
                writer.write(user.toString());
                writer.newLine();
            }
        }
        catch (IOException e)
        {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }
    public void loadUsersFromFile(String filename)
    {
        users.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                users.add(User2.fromString(line));
            }
        }catch (IOException e)
        {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    public void displayUsers()
    {
        for (User2 user : users)
        {
            System.out.println(user);
        }
    }
}
class JavaIO
{
    public static void main(String[] args) 
    {
        UserMan userManager = new UserMan();
        userManager.addUser("ARUN", "arun@example.com");
        userManager.addUser("BALA", "bala@example.com");
        String filename = "users.txt";
        userManager.saveUsersToFile(filename);
        userManager.loadUsersFromFile(filename);
        userManager.displayUsers();
    }
}