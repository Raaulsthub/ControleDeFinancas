package DefaultPackage;
public interface NotaEvent {
    void onCpf(String cpf);
    void onAccessKey(String accessKey);
    void onData(String data);
    void onCnpj(String cnpj);
    void onProduct(String nome, String code, Double price, Double quantity, String measure);
    InVoice build();
}
