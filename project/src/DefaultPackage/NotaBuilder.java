package DefaultPackage;
import java.util.ArrayList;
import java.util.List;

public class NotaBuilder {
    public List<NotaEvent> listeners;
    public NotaBuilder () {
        this.listeners = new ArrayList<>();
    }

    public void readCpf (String cpf) {
        for (NotaEvent listener: listeners) {
            listener.onCpf(cpf);
        }
    }

    public void readAccessKey (String accessKey) {
        for (NotaEvent listener: listeners) {
            listener.onAccessKey(accessKey);
        }
    }

    public void readData (String data) {
        for (NotaEvent listener: listeners) {
            listener.onData(data);
        }
    }

    public void readCnpj (String cnpj) {
        for (NotaEvent listener: listeners) {
            listener.onCnpj(cnpj);
        }
    }

    public void readName (String name) {
        for (NotaEvent listener: listeners) {
            listener.onName(name);
        }
    }

    public void readProduct (String name, String code, Double price, Double quantity, String measure) {
        for (NotaEvent listener: listeners) {
            listener.onProduct(name, code, price, quantity, measure);
        }
    }

    public void notaParser (String nota) {
        int chave = nota.indexOf("CHAVE DE ACESSO");
        for (int j = 0; j < nota.length() - 20; j++) {
            //NAME
            if (nota.substring(j, j+17).equalsIgnoreCase("CONSULTA DA NFC-e")) {
                String nameE;
                StringBuilder name = new StringBuilder();
                for(int count = j + 19; count < j + 117; count++) {
                    if (nota.charAt(count) == '\n') {
                        break;
                    }
                    name.append(nota.charAt(count));
                }
                nameE = name.toString();
                readName(nameE);
            }
            //CPF
            if (nota.substring(j, j + 3).equalsIgnoreCase("CPF")){
                String a = nota.substring(j + 5, j + 19);
                readCpf(a);
            }
            //CNPJ
            if (nota.substring(j, j + 4).equalsIgnoreCase("CNPJ")) {
                String a = nota.substring(j + 6, j + 24);
                readCnpj(a);
            }
            //DATA
            if (nota.substring(j, j + 8).equals("Impresso")) {
                String a = nota.substring(j + 9, j + 19);
                readData(a);
            }
            //ACCESS KEY
            if (nota.substring(j, j + 15).equals("CHAVE DE ACESSO")) {
                String a = nota.substring(j + 16, j + 70);
                readAccessKey(a);
            }
            //PARSING DOS PRODUTOS
            if(nota.charAt(j) == '\n' && (nota.charAt(j + 1) >= 48 && nota.charAt(j + 1) <= 57) && j != chave + 15 && nota.charAt(j + 2) != '/') {
                //PRODUCT SPOTTED
                int itr = 0, itr2 = 0, itr3 = 0, itr4  = 0;

                //GETING CODE
                for(int k = j; nota.charAt(k) != 32; k++) {
                    itr++;
                }
                itr--;
                String code = nota.substring(j + 1, j + 1 + itr);

                //GETTING NAME
                for(int k = j + 2 + itr; k < j + 1 + itr + 30; k++) {
                    if(nota.charAt(k) >= 48 && nota.charAt(k) <= 57 && ((nota.charAt(k + 2) == 'u' && nota.charAt(k+3) == 'n')) || (nota.charAt(k + 2) == 'k' && nota.charAt(k+3) == 'g')) {
                        break;
                    }
                    itr2++;
                }
                while ((nota.charAt(j + 1 + itr + itr2) >= 48 && nota.charAt(j + 1 + itr + itr2) <= 57) || nota.charAt(j + 1 + itr + itr2) == ',') {
                    itr2--;
                }
                String name = nota.substring(j + 2 + itr, j + 1 + itr + itr2);
                itr2++;

                //GETTING QUANTITY
                for (int k = j + 1 + itr + itr2; nota.charAt(k) != 32; k++) {
                    itr3++;
                }
                StringBuilder sb1 = new StringBuilder();
                for (int k = j + 1 + itr + itr2; k < j + 1 + itr + itr2 + itr3; k++) {
                    if (nota.charAt(k) == ',') {
                        sb1.append('.');
                    }
                    else if (nota.charAt(k) != 32){
                        sb1.append(nota.charAt(k));
                    }
                }
                String quantity = sb1.toString();
                double qt = Double.parseDouble(quantity);

                //GETTING MEASURE
                String measure = nota.substring(j + 2 + itr + itr2 + itr3, j + 4 + itr + itr2 + itr3);

                //GETTING PRICE
                for (int k = j + 1 + itr + itr2 + itr3 + 4; nota.charAt(k) != 32; k++) {
                    itr4++;
                }
                StringBuilder sb = new StringBuilder();
                for (int k = j + 1 + itr + itr2 + itr3 + 4; k < j + 1 + itr + itr2 + itr3 + 4 + itr4; k++) {
                    if (nota.charAt(k) == ',') {
                        sb.append('.');
                    }
                    else if (nota.charAt(k) != 32){
                        sb.append(nota.charAt(k));
                    }
                }
                String price = sb.toString();
                double p = Double.parseDouble(price);
                readProduct(name, code, p, qt, measure);
            }
        }
    }

    public void addListener (NotaEvent listener) {
        listeners.add(listener);
    }
}