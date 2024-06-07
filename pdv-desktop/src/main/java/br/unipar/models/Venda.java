package br.unipar.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Venda implements Serializable{
    @SerializedName("id")
    private Long id;
    @SerializedName("observacoes")
    private String observacoes;
    @SerializedName("data")
    private LocalDateTime data;
    @SerializedName("total")
    private BigDecimal total;
    @SerializedName("fk_Cliente_id")
    private Cliente clienteId;

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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }    

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Cliente getClienteId() {
        return clienteId;
    }

    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
    }

    
}
