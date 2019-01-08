/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MonitorSerial.model;

import java.util.Date;

/**
 *
 * @author Victor
 */
public class Temperatura {
    private int id; 
    private float temperatura ;
    private float umidade; 
    private float lux; 
    private Date data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public float getUmidade() {
        return umidade;
    }

    public void setUmidade(float umidade) {
        this.umidade = umidade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

      public float getLux() {
        return lux;
    }

    public void setLux(float lux) {
        this.lux = lux;
    }
}
