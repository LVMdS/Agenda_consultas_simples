package Agenda;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class AgendaConsultas {
    private static Map<String, String> clientes = new HashMap<>(); // Armazena clientes
    private static Map<String, String> agendamentos = new HashMap<>(); // Armazena agendamentos
    private static final String ADMIN_SENHA = "admin123";
    private static final List<String> HORARIOS_DISPONIVEIS = Arrays.asList(
            "08:00",  "09:00", "10:00", "11:00",
            "13:30", "14:30", "15:30","16:30","17:30"
    );
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private static JFrame frame;
    private static JTextArea listaConsultas;
    private static JTextField dataField;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AgendaConsultas::criarTelaPrincipal);
    }
    
    private static void criarTelaPrincipal() {
        frame = new JFrame("Agenda de Consultas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        
        JPanel topPanel = new JPanel(new FlowLayout());
        JButton btnCadastro = new JButton("Cadastrar Cliente");
        JButton btnAgendar = new JButton("Agendar Consulta");
        JButton btnAdmin = new JButton("Área do Administrador");
        
        btnCadastro.addActionListener(e -> cadastrarCliente());
        btnAgendar.addActionListener(e -> agendarConsulta());
        btnAdmin.addActionListener(e -> areaAdministrador());
        
        topPanel.add(btnCadastro);
        topPanel.add(btnAgendar);
        topPanel.add(btnAdmin);
        
        JPanel datePanel = new JPanel(new FlowLayout());
        JLabel lblData = new JLabel("Data:");
        dataField = new JTextField(10);
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> atualizarListaConsultas());
        
        datePanel.add(lblData);
        datePanel.add(dataField);
        datePanel.add(btnBuscar);
        
        listaConsultas = new JTextArea(10, 40);
        listaConsultas.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(listaConsultas);
        
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(datePanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
    
    private static void agendarConsulta() {
        JTextField nomeField = new JTextField();
        JTextField dataField = new JTextField();
        JComboBox<String> horarioBox = new JComboBox<>(HORARIOS_DISPONIVEIS.toArray(new String[0]));

        Object[] fields = {
            "Nome do Cliente:", nomeField,
            "Data (dd/MM/yyyy):", dataField,
            "Horário:", horarioBox
        };

        int option = JOptionPane.showConfirmDialog(null, fields, "Agendar Consulta", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String nome = nomeField.getText().trim();
            String data = dataField.getText().trim();
            String horario = (String) horarioBox.getSelectedItem();
            String chave = data + " " + horario;

            if (!clientes.containsKey(nome)) {
                JOptionPane.showMessageDialog(null, "Cliente não cadastrado!");
                return;
            }

            if (agendamentos.containsKey(chave)) {
                JOptionPane.showMessageDialog(null, "Horário já ocupado!");
            } else {
                agendamentos.put(chave, nome);
                JOptionPane.showMessageDialog(null, "Consulta agendada com sucesso!");
            }
        }
    }
    
    private static void atualizarListaConsultas() {
        String data = dataField.getText().trim();
        listaConsultas.setText("Consultas agendadas para " + data + ":\n");
        for (String horario : HORARIOS_DISPONIVEIS) {
            String chave = data + " " + horario;
            if (agendamentos.containsKey(chave)) {
                listaConsultas.append(horario + " - " + agendamentos.get(chave) + "\n");
            }
        }
    }
    
    private static void cadastrarCliente() {
        JTextField nomeField = new JTextField();
        JTextField telefoneField = new JTextField();
        JTextField enderecoField = new JTextField();
        JTextField emailField = new JTextField();

        Object[] fields = {
            "Nome:", nomeField,
            "Telefone:", telefoneField,
            "Endereço:", enderecoField,
            "E-mail:", emailField
        };
        
        int option = JOptionPane.showConfirmDialog(null, fields, "Cadastro de Cliente", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String nome = nomeField.getText().trim();
            if (clientes.containsKey(nome)) {
                JOptionPane.showMessageDialog(null, "Cliente já cadastrado!");
            } else {
                clientes.put(nome, telefoneField.getText() + ", " + enderecoField.getText() + ", " + emailField.getText());
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
            }
        }
    }
    
    private static void areaAdministrador() {
        String senha = JOptionPane.showInputDialog("Digite a senha do administrador:");
        if (!ADMIN_SENHA.equals(senha)) {
            JOptionPane.showMessageDialog(null, "Senha incorreta.");
            return;
        }
        
        String data = JOptionPane.showInputDialog("Digite a data da consulta para desmarcar (dd/MM/yyyy):");
        String hora = JOptionPane.showInputDialog("Digite o horário da consulta para desmarcar:");
        String chave = data + " " + hora;
        
        if (agendamentos.containsKey(chave)) {
            agendamentos.remove(chave);
            JOptionPane.showMessageDialog(null, "Consulta desmarcada com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma consulta encontrada nesse horário.");
        }
    }
}
