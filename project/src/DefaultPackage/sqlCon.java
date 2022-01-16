package DefaultPackage;

import java.sql.*;
import java.util.List;
import java.util.Scanner;


public class sqlCon {
    public static void consult(InVoice inVoice) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "raulstein22#!A");
            statement = connection.createStatement();

            //USER
            StringBuilder sb1 = new StringBuilder();
            sb1.append(inVoice.getCpf());
            sb1.deleteCharAt(3);
            sb1.deleteCharAt(6);
            sb1.deleteCharAt(9);
            String cpf = sb1.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("insert into usuario values (default, '");
            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();
            sb2.append(name);
            sb2.append("', '");
            sb2.append(cpf);
            sb2.append("');");
            String sql = sb2.toString();
            System.out.println(sql);
            statement.executeUpdate(sql);

            //ESTABLISHMENT
            StringBuilder sb3 = new StringBuilder();
            sb3.append(inVoice.getCnpj());
            sb3.deleteCharAt(2);
            sb3.deleteCharAt(5);
            sb3.deleteCharAt(8);
            sb3.deleteCharAt(12);
            String cnpj = sb3.toString();
            StringBuilder sb4 = new StringBuilder();
            sb4.append("insert into estabelecimento values (default, '");
            sb4.append("NOT CODED', '");
            sb4.append(cnpj);
            sb4.append("');");
            sql = sb4.toString();
            System.out.println(sql);
            statement.executeUpdate(sql);

            //INVOICE








        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

}
