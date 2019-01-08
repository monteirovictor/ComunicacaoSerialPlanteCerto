/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MonitorSerial.Controle;

import MonitorSerial.Conexao.SerialCom;
import MonitorSerial.Conexao.SerialComLeitura;
import MonitorSerial.Gui.GuiMonitor;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import MonitorSerial.model.Temperatura;
import MonitorSerial.Dao.Sensor1DAO;

/**
 *
 * @author fmaia
 */
public class ControleMonitor {
    
    private SerialCom serialCom;
    private SerialComLeitura comunicacao;
    private final GuiMonitor guiMonitor;
    private final Sensor1DAO sensordao;
    public ControleMonitor (GuiMonitor guiMonitor){
        this.guiMonitor = guiMonitor;
        this.serialCom = new SerialCom();
        this.sensordao = new Sensor1DAO();
    }
    
    public String[] getPortas(){
        return this.serialCom.ObterPortas();
    }
    
    public void startComunicacao(String porta, Integer Baudrate, Integer timeout) throws NoSuchPortException, PortInUseException, TooManyListenersException, UnsupportedCommOperationException{
            this.comunicacao = new SerialComLeitura(porta,
                   Baudrate, timeout, this);
            this.comunicacao.ObterIdDaPorta();
            this.comunicacao.AbrirPorta();
            try {
                this.comunicacao.startComunicacao();
            } catch (IOException ex) {
                Logger.getLogger(GuiMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void transmicao(String dados){
        this.comunicacao.transmicaoDados(dados);
    }
    
    public void stopComunicacao(){
        this.comunicacao.fecharComunicacao();
    }
    
    public Integer[] getBaudrates(String porta){
        return this.serialCom.obterBaudrates(porta);
    }
    
    public void carregaDados(String Dados){
        this.guiMonitor.ajustarDados(Dados);
        String[] valor = Dados.split(";");
        
        Temperatura temp = new Temperatura();
        temp.setTemperatura(Float.parseFloat(valor[0]));
        temp.setUmidade(Float.parseFloat(valor[1]));
        temp.setLux(Float.parseFloat(valor[2]));
        this.sensordao.save(temp);
    }
    
}
