import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;


// Classe ContaCartao
class ContaCartao {
    private String numeroCartao;
    private String nomeTitular;
    private double limiteCartao;
    private double saldoCartao;
    private double formaPagamento; // Percentagem de 4% a 100%
    private Date dataNascimento;
    private String contactoTelefonico;
    private String email;
    private ArrayList<Movimento> movimentos;
  
    // Classe interna para representar um movimento
    class Movimento implements Comparable<Movimento> {
        private Date data;
        private double valor;
        private String entidadeDestino;
        // Construtor
        public Movimento(Date data, double valor, String entidadeDestino) {
            this.data = data;
            this.valor = valor;
            this.entidadeDestino = entidadeDestino;
        }
        // Getters
        public Date getData() {
            return this.data;
        }
        public double getValor() {
            return this.valor;
        }
        public String getEntidadeDestino() {
            return this.entidadeDestino;
        }
        // Para ordenar os movimentos por data
        public int compareTo(Movimento other) {
            return this.data.compareTo(other.data);
        }
    }

    // Construtor
    public ContaCartao(String numeroCartao, String nomeTitular, double limiteCartao, 
                       double saldoCartao, double formaPagamento, Date dataNascimento, 
                       String contactoTelefonico, String email) {
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
        this.limiteCartao = limiteCartao;
        this.saldoCartao = saldoCartao;
        this.formaPagamento = formaPagamento;
        this.dataNascimento = dataNascimento;
        this.contactoTelefonico = contactoTelefonico;
        this.email = email;
        this.movimentos = new ArrayList<Movimento>();
    }
    // Getters e Setters
    public String getNumeroCartao() {
        return this.numeroCartao;
    }
    public String getNomeTitular() {
        return this.nomeTitular;
    }
    public double getLimiteCartao() {
        return this.limiteCartao;
    }
    public void setLimiteCartao(double limiteCartao) {
        this.limiteCartao = limiteCartao;
    }
    public double getSaldoCartao() {
        return this.saldoCartao;
    }
    public void setSaldoCartao(double saldoCartao) {
        this.saldoCartao = saldoCartao;
    }
    public double getFormaPagamento() {
        return this.formaPagamento;
    }
    public void setFormaPagamento(double formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    public Date getDataNascimento() {
        return this.dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public String getContactoTelefonico() {
        return this.contactoTelefonico;
    }
    public void setContactoTelefonico(String contactoTelefonico) {
        this.contactoTelefonico = contactoTelefonico;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    // Métodos
    public void adicionarMovimento(Date data, double valor, String entidadeDestino) {
        this.movimentos.add(new Movimento(data, valor, entidadeDestino));
        // Reordenar a lista de movimentos em ordem crescente por data
        Collections.sort(this.movimentos);
    }
    // Métodos
    public void efetuarMovimento(Date data, double valor, String entidadeDestino, String tipoMovimento) {
    // Validação do tipo de movimento
    if(tipoMovimento.equals("compra")) {
        if(this.saldoCartao - valor >= 0){
            this.saldoCartao -= valor;
        } else {
            System.out.println("Saldo insuficiente para realizar a compra.");
            return;
        }
    } else if(tipoMovimento.equals("amortizacao")) {
        if(this.saldoCartao + valor <= this.limiteCartao){
            this.saldoCartao += valor;
        } else {
            System.out.println("Limite do cartão excedido com a amortização.");
            return;
        }
    } else {
        System.out.println("Tipo de movimento inválido!");
        return;
    }
    this.movimentos.add(new Movimento(data, valor, entidadeDestino));
    // Reordenar a lista de movimentos em ordem crescente por data
    Collections.sort(this.movimentos);
}

    // Imprime os movimentos da conta
    public void imprimirMovimentos() {
        double totalPagar = 0.0;
        for (Movimento m : this.movimentos) {
            System.out.println("Data: " + m.getData() + ", Valor: " + m.getValor() + ", Entidade Destino: " + m.getEntidadeDestino());
            totalPagar += m.getValor();
        }
        System.out.println("Total a Pagar: " + totalPagar);
    }
}

// Classe GestorContaCartao
class GestorContaCartao {
    private String nomeGestor;
    private ArrayList<ContaCartao> contasCartao;
    // Construtor
    public GestorContaCartao(String nomeGestor) {
        this.nomeGestor = nomeGestor;
        this.contasCartao = new ArrayList<ContaCartao>();
    }
    // Getters e Setters
    public String getNomeGestor() {
        return this.nomeGestor;
    }
    // Métodos
    public void adicionarContaCartao(ContaCartao contaCartao) {
        this.contasCartao.add(contaCartao);
    }
    // Imprime o resumo mensal da conta
    public void resumoMensal(ContaCartao contaCartao) {
        System.out.println("Número do Cartão: " + contaCartao.getNumeroCartao());
        System.out.println("Nome do Titular: " + contaCartao.getNomeTitular());
        System.out.println("Limite do Cartão: " + contaCartao.getLimiteCartao());
        System.out.println("Saldo do Cartão: " + contaCartao.getSaldoCartao());
        System.out.println("Forma de Pagamento: " + contaCartao.getFormaPagamento() + "%");
        System.out.println("Data de Nascimento: " + contaCartao.getDataNascimento());
        System.out.println("Contato Telefônico: " + contaCartao.getContactoTelefonico());
        System.out.println("Email: " + contaCartao.getEmail());
        System.out.println("Movimentos:");
        contaCartao.imprimirMovimentos();
    }

}

// Classe Main
public class Main {
  public static void main(String[] args) {
        // Cria uma nova conta de cartão
        ContaCartao contaCartao = new ContaCartao("1234 5678 9012 3456", "João Vasconcelos", 
                                                   2000.0, 0.0, 100.0, 
                                                   new Date(2000 - 1900, 1, 1), // Ano, Mês, Dia
                                                   "919192929", "joao@mailteste.com");

        // Adiciona alguns movimentos à conta
        contaCartao.adicionarMovimento(new Date(2023 - 1900, 5, 1), 200.0, "Loja A");
        contaCartao.adicionarMovimento(new Date(2023 - 1900, 5, 15), 300.0, "Loja B");
        contaCartao.adicionarMovimento(new Date(2023 - 1900, 6, 5), 100.0, "Loja C");

        // Cria um novo gestor de conta cartão
        GestorContaCartao gestor = new GestorContaCartao("Pedro Gestor");

        // Adiciona a conta ao gestor
        gestor.adicionarContaCartao(contaCartao);

        // Imprime o resumo mensal da conta
        gestor.resumoMensal(contaCartao);
    }
    
}


