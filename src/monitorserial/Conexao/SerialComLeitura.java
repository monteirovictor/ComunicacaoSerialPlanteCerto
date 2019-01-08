/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MonitorSerial.Conexao;

import MonitorSerial.Controle.ControleMonitor;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;

/**
 *
 * @author victor
 */
public class SerialComLeitura{
    public String Dadoslidos;
    public int nodeBytes;
    private int baudrate;
    private int timeout;
    private CommPortIdentifier cp;
    private SerialPort porta;
    private Thread threadLeitura;
    private String Porta;
    private ControleMonitor controleMonitor;
    private Monitoramento moni;
    
    public SerialComLeitura( String p , int b , int t ,ControleMonitor controleMonitor){
        this.Porta = p;
        this.baudrate = b;
        this.timeout = t;
        this.controleMonitor = controleMonitor;
    }
    
    public void ObterIdDaPorta() throws NoSuchPortException{
        cp = CommPortIdentifier.getPortIdentifier(Porta);
    }

    public void AbrirPorta() throws PortInUseException, UnsupportedCommOperationException{
        porta = (SerialPort)cp.open("SerialComLeitura", timeout);
        //configurar parâmetros
        porta.setSerialPortParams(baudrate,
        SerialPort.DATABITS_8,
        SerialPort.STOPBITS_1,
        SerialPort.PARITY_NONE);
        porta.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
    }
    
    public void startComunicacao() throws IOException, TooManyListenersException{
        this.moni = new Monitoramento(Porta, porta, controleMonitor);
        porta.addEventListener(moni);
        porta.notifyOnDataAvailable(true);
        threadLeitura = new Thread(moni);
        threadLeitura.start();
        moni.run();
    }
    
    public void transmicaoDados(String dados){
        this.moni.EnviarUmaString(dados);
    }
    
    public void fecharComunicacao(){
            porta.close();
    }
    
    public String obterPorta(){
        return Porta;
    }

    public int obterBaudrate(){
        return baudrate;
    }
    
    
    
}
