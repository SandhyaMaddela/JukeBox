package songsapplication;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UserImpl {
    Scanner sc=new Scanner(System.in);
    JukeBoxImplementation i=new JukeBoxImplementation();
    public void login(String username, String password) {
        try {
            Statement statement = SongsData.getConnection().createStatement();
            String query = "select * from login";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String userName1 = resultSet.getString("username");
                String password1 = resultSet.getString("password");
                if(userName1.equalsIgnoreCase(username) && password1.equalsIgnoreCase(password)) {
                    System.out.println("welcome!!!!!!");
                    i.jukeBoxImpl();
                    break;
                } else {
                    System.out.println("invalid try again");
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public boolean checkUser(String name,String pass){
        boolean flag = true;
        try {
            Statement statement = SongsData.getConnection().createStatement();
            String query = "select * from login";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String userName1 = resultSet.getString("username");
                String password1 = resultSet.getString("password");
                if (userName1.equalsIgnoreCase(name) && password1.equalsIgnoreCase(pass)) {
                    System.out.println("welcome!!!!!!");
                    flag = true;
                    break;
                } else {
                    flag = false;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

    public void newUser(String name,String pass,String id,int num){
        UserImpl u=new UserImpl();
        boolean f=u.checkUser(name, pass);
        if(f){
            System.out.println("Already Existed");
            System.out.println("You can login");
        }else{
            try{
                String query="insert into login(username,password,mailid,contactno) values (?,?,?,?)";
                PreparedStatement preparedStatement=SongsData.getConnection().prepareStatement(query);
                preparedStatement.setString(1,name);
                preparedStatement.setString(2,pass);
                preparedStatement.setString(3,id);
                preparedStatement.setInt(4,num);
                preparedStatement.executeUpdate();
                System.out.println("Account created");
                JukeBoxImplementation i=new JukeBoxImplementation();
                i.jukeBoxImpl();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

