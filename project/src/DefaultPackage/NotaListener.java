package DefaultPackage;

import java.util.ArrayList;
import java.util.List;

public class NotaListener implements NotaEvent {
    private String cpf;
    private String accessKey;
    private String data;
    private String cnpj;
    public List<Product> productList = new ArrayList<>();

    @Override
    public void onCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public void onAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    @Override
    public void onData(String data) {
        this.data = data;
    }

    @Override
    public void onCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public void onProduct(String name, String code, Double price, Double quantity, String measure) {
        Product product = new Product(name, code, price, quantity, measure);
        productList.add(product);
    }

    @Override
    public InVoice build() {
        return new InVoice(cpf, accessKey, data, cnpj, productList);
    }
    
}