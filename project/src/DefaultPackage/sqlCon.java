package DefaultPackage;

import jdk.jfr.Unsigned;

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
            ResultSet resultSet;

            //USER

            //finding cpf
            StringBuilder sb1 = new StringBuilder();
            sb1.append(inVoice.getCpf());
            sb1.deleteCharAt(3);
            sb1.deleteCharAt(6);
            sb1.deleteCharAt(9);
            String cpf = sb1.toString();
            sb1.delete(0, sb1.length());

            //building sql command
            StringBuilder sb2 = new StringBuilder();
            sb2.append("insert into usuario values (default, '");
            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();
            sb2.append(name);
            sb2.append("', '");
            sb2.append(cpf);
            sb2.append("');");
            String sql = sb2.toString();
            statement.executeUpdate(sql);
            sb2.delete(0, sb2.length());

            //ESTABLISHMENT

            //finding npj
            sb1.append(inVoice.getCnpj());
            sb1.deleteCharAt(2);
            sb1.deleteCharAt(5);
            sb1.deleteCharAt(8);
            sb1.deleteCharAt(12);
            String cnpj = sb1.toString();
            sb1.delete(0, sb1.length());

            //building sql command
            sb2.append("insert into estabelecimento values (default, default, '");
            sb2.append(cnpj);
            sb2.append("', '");
            sb2.append(inVoice.getName());
            sb2.append("');");
            sql = sb2.toString();
            statement.executeUpdate(sql);
            sb2.delete(0, sb2.length());

            //INVOICE

            //finding data
            sb1.append(inVoice.getData().substring(6, 10));
            sb1.append("-");
            sb1.append(inVoice.getData().substring(3, 5));
            sb1.append("-");
            sb1.append(inVoice.getData().substring(0, 2));
            String data = sb1.toString();
            sb1.delete(0, sb1.length());

            //searching user id
            sb1.append("select * from usuario where cpf = '");
            sb1.append(cpf);
            sb1.append("';");
            String query = sb1.toString();
            resultSet = statement.executeQuery(query);
            Integer idUser = -1;
            if(resultSet != null && resultSet.next()) {
                idUser = resultSet.getInt("IdPess");
            }
            sb1.delete(0, sb1.length());

            //searching establishment id
            sb1.append("select * from estabelecimento where cnpj = '");
            sb1.append(cnpj);
            sb1.append("';");
            query = sb1.toString();
            resultSet = statement.executeQuery(query);
            Integer idEst = -1;
            if(resultSet != null && resultSet.next()) {
                idEst = resultSet.getInt("idEst");
            }
            sb1.delete(0, sb1.length());

            //building sql command
            sb2.append("insert into notafiscal values (default, '");
            sb2.append(data);
            sb2.append("', '");
            sb2.append("notCODED");
            sb2.append("', '");
            sb2.append(idUser);
            sb2.append("', '");
            sb2.append(idEst);
            sb2.append("');");
            sql = sb2.toString();
            System.out.println(sql);
            statement.executeUpdate(sql);
            sb2.delete(0, sb2.length());

            //PRODUCTS
            for (Product product: inVoice.productList) {
                sb2.append("insert into produto values (default, '");
                sb2.append(product.getName());
                sb2.append("');");
                sql = sb2.toString();
                System.out.println(sql);
                statement.executeUpdate(sql);
                sb2.delete(0, sb2.length());
            }

            //ITEMS

            //finding idNota
            sb1.append("select * from notafiscal where idPess = '");
            sb1.append(idUser);
            sb1.append("' AND dataNota = '");
            sb1.append(data);
            sb1.append("' AND idEst = '");
            sb1.append(idEst);
            sb1.append("';");
            query = sb1.toString();
            System.out.println(query);
            sb1.delete(0, sb1.length());
            resultSet = statement.executeQuery(query);
            Integer idNota = -1;
            if(resultSet != null && resultSet.next()) {
                idNota = resultSet.getInt("IdNota");
            }
            sb1.delete(0, sb1.length());

            int noOrd = 1;
            for (Product product: inVoice.productList) {
                //finding idProd
                sb1.append("select * from produto where descricao = '");
                sb1.append(product.getName());
                sb1.append("';");
                query = sb1.toString();
                sb1.delete(0, sb1.length());
                resultSet = statement.executeQuery(query);
                Integer idProd = -1;
                if(resultSet != null && resultSet.next()) {
                    idProd = resultSet.getInt("IdProd");
                }
                sb2.append("insert into item values ('");
                sb2.append(idNota);
                sb2.append("', '");
                sb2.append(noOrd);
                sb2.append("', '");
                sb2.append(product.getPrice());
                sb2.append("', '");
                sb2.append(product.getQuantity());
                sb2.append("', '");
                sb2.append(idProd);
                sb2.append("', '");
                sb2.append(product.getMeasure());
                sb2.append("');");
                sql = sb2.toString();
                System.out.println(sql);
                statement.executeUpdate(sql);
                sb2.delete(0, sb2.length());
                noOrd++;

                //ASSOCIACAO

                //building sql command
                sb2.append("insert into associacao values ('");
                sb2.append(product.getCode());
                sb2.append("', '");
                sb2.append(idEst);
                sb2.append("', '");
                sb2.append(idProd);
                sb2.append("');");
                sql = sb2.toString();
                statement.executeUpdate(sql);
                sb2.delete(0, sb2.length());
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

}
