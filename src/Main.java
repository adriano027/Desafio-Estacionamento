import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Ticket {
    private final int numero;
    private final double valor;
    private boolean pago;

    public Ticket(int numero, double valor) {
        this.numero = numero;
        this.valor = valor;
        this.pago = false; // inicialmente, o ticket não está pago
    }

    public double getValor() {
        return valor;
    }

    public boolean isPago() {
        return pago;
    }

    public void pagar() {
        if (!pago) {
            pago = true;
            System.out.println("Ticket nº " + numero + " foi pago com sucesso.");
        } else {
            System.out.println("Ticket nº " + numero + " já está pago.");
        }
    }
}

class Estacionamento {
    private final Map<Integer, Ticket> tickets;
    private double saldo;
    private int contadorTickets;

    public Estacionamento() {
        this.tickets = new HashMap<>();
        this.saldo = 0;
        this.contadorTickets = 1; // começa com 1
    }

    public void emitirTicket(double valor) {
        Ticket novoTicket = new Ticket(contadorTickets, valor);
        tickets.put(contadorTickets, novoTicket);
        System.out.println("Ticket emitido: nº " + contadorTickets + ", Valor: R$" + valor);
        contadorTickets++;
    }

    public void pagarTicket(int numeroTicket) {
        Ticket ticket = tickets.get(numeroTicket);
        if (ticket != null) {
            if (!ticket.isPago()) {
                ticket.pagar();
                saldo += ticket.getValor();
            } else {
                System.out.println("O ticket nº " + numeroTicket + " já foi pago.");
            }
        } else {
            System.out.println("Ticket não encontrado.");
        }
    }

    public void consultarSaldo() {
        System.out.println("Saldo total das operações: R$" + saldo);
    }
}

class SistemaEstacionamento {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Estacionamento estacionamento = new Estacionamento();
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nSistema de Estacionamento");
            System.out.println("1. Emitir Ticket");
            System.out.println("2. Pagar Ticket");
            System.out.println("3. Consultar Saldo");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o valor do ticket: R$");
                    double valor = scanner.nextDouble();
                    estacionamento.emitirTicket(valor);
                    break;

                case 2:
                    System.out.print("Digite o número do ticket a pagar: ");
                    int numeroTicket = scanner.nextInt();
                    estacionamento.pagarTicket(numeroTicket);
                    break;

                case 3:
                    estacionamento.consultarSaldo();
                    break;

                case 4:
                    continuar = false;
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
        scanner.close();
    }
}
