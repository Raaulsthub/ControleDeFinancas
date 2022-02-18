package DefaultPackage;

import jdk.jfr.Unsigned;

import javax.swing.plaf.nimbus.State;
import java.security.ProtectionDomain;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class sqlCon {

    private static String user_sql(InVoice inVoice, String cpf) {
        StringBuilder sb2 = new StringBuilder();
        //building sql command
        sb2.append("insert into usuario values (default, '");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        System.out.println(name);
        sb2.append(name);
        sb2.append("', '");
        sb2.append(cpf);
        sb2.append("');");
        return sb2.toString();
    }

    private static String establishment_sql(InVoice inVoice, String cnpj) {
        StringBuilder sb2 = new StringBuilder();
        //building sql command
        sb2.append("insert into estabelecimento values (default, default, '");
        sb2.append(cnpj);
        sb2.append("', '");
        sb2.append(inVoice.getName());
        sb2.append("');");
        return sb2.toString();
    }

    private static Integer find_idUser(String cpf, ResultSet resultSet, Statement statement) {
        try {
            StringBuilder sb1 = new StringBuilder();
            sb1.append("select * from usuario where cpf = '");
            sb1.append(cpf);
            sb1.append("';");
            String query = sb1.toString();
            resultSet = statement.executeQuery(query);
            Integer idUser = -1;
            if (resultSet != null && resultSet.next()) {
                idUser = resultSet.getInt("IdPess");
            }
            return idUser;
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return -1;
    }

    private static Integer find_idEst(String cnpj, ResultSet resultSet, Statement statement){
        try {
            StringBuilder sb1 = new StringBuilder();
            sb1.append("select * from estabelecimento where cnpj = '");
            sb1.append(cnpj);
            sb1.append("';");
            String query = sb1.toString();
            resultSet = statement.executeQuery(query);
            Integer idEst = -1;
            if (resultSet != null && resultSet.next()) {
                idEst = resultSet.getInt("idEst");
            }
            sb1.delete(0, sb1.length());
            return idEst;
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
        return -1;
    }

    private static Integer find_idNota(Integer idUser, String data, Integer idEst, ResultSet resultSet, Statement statement, InVoice inVoice) {
        try {
            StringBuilder sb1 = new StringBuilder();
            sb1.append("select * from notafiscal where idPess = '");
            sb1.append(idUser);
            sb1.append("' AND dataNota = '");
            sb1.append(data);
            sb1.append("' AND horaNota = '");
            sb1.append(inVoice.time);
            sb1.append("' AND idEst = '");
            sb1.append(idEst);
            sb1.append("';");
            String query = sb1.toString();
            System.out.println(query);
            sb1.delete(0, sb1.length());
            resultSet = statement.executeQuery(query);
            Integer idNota = -1;
            if (resultSet != null && resultSet.next()) {
                idNota = resultSet.getInt("IdNota");
            }
            return idNota;
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return -1;
    }

    private static Integer find_idProd(String name, ResultSet resultSet, Statement statement) {
        try {
            StringBuilder sb1 = new StringBuilder();
            //finding idProd
            sb1.append("select * from produto where descricao = '");
            sb1.append(name);
            sb1.append("';");
            String query = sb1.toString();
            sb1.delete(0, sb1.length());
            resultSet = statement.executeQuery(query);
            Integer idProd = -1;
            if (resultSet != null && resultSet.next()) {
                idProd = resultSet.getInt("IdProd");
            }
            return idProd;
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return -1;
    }

    private static String find_data(String data) {
        StringBuilder sb1 = new StringBuilder();
        sb1.append(data.substring(6, 10));
        sb1.append("-");
        sb1.append(data.substring(3, 5));
        sb1.append("-");
        sb1.append(data.substring(0, 2));
        return sb1.toString();
    }

    private static String inVoice_sql (InVoice inVoice, String cpf, String cnpj, ResultSet resultSet, Statement statement) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
            //finding data
            String data = find_data(inVoice.date);

            //searching user id
            Integer idUser = find_idUser(cpf, resultSet, statement);

            //searching establishment id
            Integer idEst = find_idEst(cnpj, resultSet, statement);

            //building sql command
            sb2.append("insert into notafiscal values (default, '");
            sb2.append(data);
            sb2.append("', '");
            sb2.append(inVoice.time);
            sb2.append("', '");
            sb2.append("notCODED");
            sb2.append("', '");
            sb2.append(idUser);
            sb2.append("', '");
            sb2.append(idEst);
            sb2.append("');");
            return sb2.toString();
    }

    private static String product_sql(Product product) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append("insert into produto values (default, '");
        sb2.append(product.getName());
        sb2.append("');");
        return sb2.toString();
    }

    private static void items_association_sql(InVoice inVoice, ResultSet resultSet, Statement statement) {
        StringBuilder sb2 = new StringBuilder();
        //finding idUser
        Integer idUser = find_idUser(format_cpf(inVoice.cpf), resultSet, statement);
        //finding data
        String data = find_data(inVoice.date);
        //finding idEst
        Integer idEst = find_idEst(format_cnpj(inVoice.cnpj), resultSet, statement);
        //finding idNota
        Integer idNota = find_idNota(idUser, data, idEst, resultSet, statement, inVoice);
        int noOrd = 1;
        for (Product product: inVoice.productList) {
            try {
                //finding idProd
                int idProd = find_idProd(product.name, resultSet, statement);
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
                statement.executeUpdate(sb2.toString());
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
                statement.executeUpdate(sb2.toString());
                sb2.delete(0, sb2.length());
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    private static String format_cpf(String cpf) {
        StringBuilder sb1 = new StringBuilder();
        sb1.append(cpf);
        sb1.deleteCharAt(3);
        sb1.deleteCharAt(6);
        sb1.deleteCharAt(9);
        return sb1.toString();
    }

    private static String format_cnpj(String cnpj) {
        StringBuilder sb1 = new StringBuilder();
        sb1.append(cnpj);
        sb1.deleteCharAt(2);
        sb1.deleteCharAt(5);
        sb1.deleteCharAt(8);
        sb1.deleteCharAt(12);
        return sb1.toString();
    }

    public static void alimentar(InVoice inVoice) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaapp", "root", "raulstein22#!A");

            statement = connection.createStatement();
            ResultSet resultSet = null;

            //USER
            String sql = user_sql(inVoice, format_cpf(inVoice.cpf));

            statement.executeUpdate(sql);

            //ESTABLISHMENT
            sql = establishment_sql(inVoice, format_cnpj(inVoice.cnpj));
            statement.executeUpdate(sql);

            //INVOICE
            sql = inVoice_sql(inVoice, format_cpf(inVoice.cpf), format_cnpj(inVoice.cnpj),resultSet, statement);
            System.out.println(sql);
            statement.executeUpdate(sql);

            //PRODUCTS
            for (Product product: inVoice.productList) {
                sql = product_sql(product);
                statement.executeUpdate(sql);
            }

            //ITEMS AND ASSOCIATION
            items_association_sql(inVoice, resultSet, statement);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

}
