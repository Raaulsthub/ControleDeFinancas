package DefaultPackage;
public interface NotaEvent {
    void onCpf(String cpf);
    void onAccessKey(String accessKey);
    void onDate(String data);
    void onTime(String time);
    void onCnpj(String cnpj);
    void onName(String name);
    void onProduct(String nome, String code, Double price, Double quantity, String measure);
    InVoice build();
}
