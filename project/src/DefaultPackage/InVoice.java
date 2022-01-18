package DefaultPackage;
import java.util.ArrayList;
import java.util.List;


public class InVoice {
    public  String cpf;
    public  String accessKey;
    public  String data;
    public  String cnpj;
    public  String name;
    public List<Product> productList = new ArrayList<>();

    public InVoice () {
    }

    public InVoice (String cpf, String accessKey, String data, String cnpj, List productList, String name) {
        this.cpf = cpf;
        this.accessKey = accessKey;
        this.data = data;
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

    public String getData() {
        return data;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getName(){ return name; }
}
