package DefaultPackage;
import java.util.ArrayList;
import java.util.List;

public class NotaBuilder {
    public List<NotaEvent> listeners;

    //MACROS

    private static final int NAME_PARSER = 17;
    private static final int CPF_PARSER = 3;
    private static final int CPF_SIZE = 14;
    private static final int CNPJ_PARSER = 4;
    private static final int CNPJ_SIZE = 18;
    private static final int DATA_PARSER = 8;
    private static final int DATA_SIZE = 10;
    private static final int TIME_PARSER = 23;
    private static final int TIME_SIZE = 8;
    private static final int ACCESSK_PARSER = 15;
    private static final int ASCCI_NUMBER_0 = 48;
    private static final int ASCCI_NUMBER_9 = 57;
    private static final int ASCCI_SPACE = 32;


    //BUILDER

    public NotaBuilder () {
        this.listeners = new ArrayList<>();
    }

    //TRIGGERS

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

    public void readDate(String date) {
        for (NotaEvent listener: listeners) {
            listener.onDate(date);
        }
    }

    public void readTime (String time) {
        for (NotaEvent listener: listeners) {
            listener.onTime(time);
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

    //UTIL

    private static String parserName (int j, String nota) {
        StringBuilder name = new StringBuilder();
        int count = j + NAME_PARSER + 2;
        while(true) {
            if (nota.charAt(count) == '\n') {
                break;
            }
            name.append(nota.charAt(count));
            count++;
        }
        return name.toString();
    }

    private static String parserCpf (int j, String nota) {
        return nota.substring(j + CPF_PARSER + 2, j + CPF_PARSER + 2 + CPF_SIZE);
    }

    private static String parserCnpj (int j, String nota){
        return nota.substring(j + CNPJ_PARSER + 2, j + CNPJ_PARSER + 2 + CNPJ_SIZE);
    }

    private static String parserDate (int j, String nota) {
        return nota.substring(j + DATA_PARSER + 1, j + DATA_PARSER + 1 + DATA_SIZE);
    }

    private static String parserTime (int j, String nota) {
        return nota.substring(j + TIME_PARSER, j + TIME_PARSER + TIME_SIZE);
    }

    private static String parserAccessKey (int j, String nota) {
        StringBuilder aK = new StringBuilder();
        int count = j + ACCESSK_PARSER + 2;
        while(true) {
            if (nota.charAt(count) == '\n') {
                break;
            }
            aK.append(nota.charAt(count));
            count++;
        }
        return aK.toString();
    }

    private static boolean productKickStart(String nota, int j, int exception_chave) {
        if (nota.charAt(j) != '\n'){
            return false;
        }
        if (nota.charAt(j - 2) == 'O') {
            return false;
        }
        if (nota.charAt(j + 1) <= ASCCI_NUMBER_0 || nota.charAt(j + 1) >= ASCCI_NUMBER_9) {
            return false;
        }
        if (j == exception_chave + 15) {
            return false;
        }
        if (nota.charAt(j + 2) == '/') {
            return false;
        }
        return true;
    }

    private static String parserProductCode (String nota, int j, ItrPointer itr) {
        for(int k = j; nota.charAt(k) != 32; k++) {
            itr.value++;
        }
        itr.value--;
        return nota.substring(j + 1, j + 1 + itr.value);
    }

    private static boolean productNameCount(String nota, int k) {
        int stop = 0;
        if (nota.charAt(k) >= ASCCI_NUMBER_0 && nota.charAt(k) <= ASCCI_NUMBER_9) {stop++;}
        if (nota.charAt(k + 2) == 'u' && nota.charAt(k+3) == 'n') {stop++;}
        else if(nota.charAt(k + 2) == 'k' && nota.charAt(k+3) == 'g') {stop++;}
        if (stop == 2) {return true;}
        return false;
    }

    private static boolean productNameDiminution(String nota, int j, ItrPointer itr, ItrPointer itr2){
        if(nota.charAt(j + 1 + itr.value + itr2.value) >= ASCCI_NUMBER_0 && nota.charAt(j + 1 + itr.value + itr2.value) <= ASCCI_NUMBER_9) {
            return true;
        }
        if(nota.charAt(j + 1 + itr.value + itr2.value) == ',') {
            return true;
        }
        return false;
    }

    private static String parserProductName(String nota, int j, ItrPointer itr, ItrPointer itr2) {
        int k = j + 2 + itr.value;
        while(true) {
            if(productNameCount(nota, k)) {
                break;
            }
            itr2.value++;
            k++;
        }
        while (productNameDiminution(nota, j, itr, itr2)) {
            itr2.value--;
        }
        String name = nota.substring(j + 2 + itr.value, j + 1 + itr.value + itr2.value);
        itr2.value++;
        return name;
    }

    private static double parserProductQuantity(String nota, int j, ItrPointer itr, ItrPointer itr2, ItrPointer itr3){
        for (int k = j + 1 + itr.value + itr2.value; nota.charAt(k) != ASCCI_SPACE; k++) {
            itr3.value++;
        }
        StringBuilder sb1 = new StringBuilder();
        for (int k = j + 1 + itr.value + itr2.value; k < j + 1 + itr.value + itr2.value + itr3.value; k++) {
            if (nota.charAt(k) == ',') {
                sb1.append('.');
            }
            else if (nota.charAt(k) != 32){
                sb1.append(nota.charAt(k));
            }
        }
        String quantity = sb1.toString();
        double qt = Double.parseDouble(quantity);
        return qt;
    }

    private static String parserProductMeasure (String nota, int j, ItrPointer itr, ItrPointer itr2, ItrPointer itr3){
        return nota.substring(j + 2 + itr.value + itr2.value + itr3.value, j + 4 + itr.value + itr2.value + itr3.value);
    }

    private static double parserProductPrice (String nota, int j, ItrPointer itr, ItrPointer itr2, ItrPointer itr3, ItrPointer itr4) {
        for (int k = j + 1 + itr.value + itr2.value + itr3.value + 4; nota.charAt(k) != ASCCI_SPACE; k++) {
            itr4.value++;
        }
        StringBuilder sb = new StringBuilder();
        for (int k = j + 1 + itr.value + itr2.value + itr3.value + 4; k < j + 1 + itr.value + itr2.value + itr3.value + 4 + itr4.value; k++) {
            if (nota.charAt(k) == ',') {
                sb.append('.');
            }
            else if (nota.charAt(k) != 32){
                sb.append(nota.charAt(k));
            }
        }
        String price = sb.toString();
        double p = Double.parseDouble(price);
        return p;
    }

    //PARSER

    public void notaParser (String nota) {
        int chave = nota.indexOf("CHAVE DE ACESSO");
        for (int j = 0; j < nota.length() - 20; j++) {
            //NAME
            if (nota.substring(j, j + NAME_PARSER).equalsIgnoreCase("CONSULTA DA NFC-e")) {
                readName(parserName(j, nota));
            }
            //CPF
            if (nota.substring(j, j + CPF_PARSER).equalsIgnoreCase("CPF")){
                readCpf(parserCpf(j, nota));
            }
            //CNPJ
            if (nota.substring(j, j + CNPJ_PARSER).equalsIgnoreCase("CNPJ")) {
                readCnpj(parserCnpj(j, nota));
            }
            //DATA E HORA
            if (nota.substring(j, j + DATA_PARSER).equals("Impresso")) {
                readDate(parserDate(j, nota));
                readTime(parserTime(j, nota));
            }

            //ACCESS KEY
            if (nota.substring(j, j + ACCESSK_PARSER).equals("CHAVE DE ACESSO")) {
                readAccessKey(parserAccessKey(j, nota));
            }
            //PARSING DOS PRODUTOS
            //PRODUCT SPOTTED
            if(productKickStart(nota, j, chave)) {
                ItrPointer itr = new ItrPointer();
                ItrPointer itr2 = new ItrPointer();
                ItrPointer itr3 = new ItrPointer();
                ItrPointer itr4 = new ItrPointer();
                //GETING CODE
                String code = parserProductCode(nota, j, itr);

                //GETTING NAME
                String name = parserProductName(nota, j, itr, itr2);

                //GETTING QUANTITY
                double qt = parserProductQuantity(nota, j, itr, itr2, itr3);

                //GETTING MEASURE
                String measure = parserProductMeasure(nota, j, itr, itr2, itr3);

                //GETTING PRICE
                double p = parserProductPrice(nota, j, itr, itr2, itr3, itr4);
                readProduct(name, code, p, qt, measure);
            }
        }
    }

    public void addListener (NotaEvent listener) {
        listeners.add(listener);
    }
}