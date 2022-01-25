package DefaultPackage;
import java.util.ArrayList;
import java.util.List;


public class InVoice {
    public  String cpf;
    public  String accessKey;
    public  String date;
    public  String time;
    public  String cnpj;
    public  String name;
    public List<Product> productList = new ArrayList<>();

    public InVoice () {
    }

    public InVoice (String cpf, String accessKey, String data, String time, String cnpj, List productList, String name) {
        this.cpf = cpf;
        this.accessKey = accessKey;
        this.date = data;
        this.time = time;
        this.cnpj = cnpj;
        this.name = name;
        this.productList = productList;
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public String getCpf() {
        return cpf;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getDate() {
        return date;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getName(){ return name; }

    public String getTime(){ return time; }
}
