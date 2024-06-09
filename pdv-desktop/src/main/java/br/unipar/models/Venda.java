package br.unipar.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.SerializedName;
import dto.ItemVendaDTO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class Venda implements Serializable{
    @SerializedName("id")
    private Long id;
    @SerializedName("observacoes")
    private String observacoes;
    @SerializedName("data")
    private String data;
    @SerializedName("total")
    private Double total;
    @SerializedName("fk_Cliente_id")
    private Long clienteId;
    @SerializedName("itensVenda")
    private List<ItemVendaDTO> itensvenda;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<ItemVendaDTO> getItensvenda() {
        return itensvenda;
    }

    public void setItensvenda(List<ItemVendaDTO> itensvenda) {
        this.itensvenda = itensvenda;
    }

  
    

    
}
