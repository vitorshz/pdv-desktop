/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOItemVneda;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author EDUARDO
 */
public class VendaResponse {
    private String observacoes;
    private LocalDateTime data;
    private Double total;
    private Long clienteId;
    private List<ItemVendaDTOTest> itensVenda;

    // Construtores, getters e setters
    public VendaResponse() {}

    public VendaResponse(String observacoes,  LocalDateTime data,  Double total,  Long clienteId, List<ItemVendaDTOTest> itensVenda) {
        this.observacoes = observacoes;
        this.data = data;
        this.total = total;
        this.clienteId = clienteId;
        this.itensVenda = itensVenda;
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

    public List<ItemVendaDTOTest> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItemVendaDTOTest> itensVenda) {
        this.itensVenda = itensVenda;
    }

    @Override
    public String toString() {
        return "VendaDTO{" +
                "observacoes='" + observacoes + '\'' +
                ", data=" + data +
                ", total=" + total +
                ", clienteId=" + clienteId +
                ", itensVenda=" + itensVenda +
                '}';
    }
}