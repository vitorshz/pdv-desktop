/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOItemVneda;

/**
 *
 * @author EDUARDO
 */
import java.util.List;

public class VendaDTO {
    private String observacoes;
    private String data;
    private Long clienteId;
    private List<ItemVendaDTOTest> itensVenda;

    // Construtores, getters e setters
    public VendaDTO() {}

    public VendaDTO(String observacoes, String data, Long clienteId, List<ItemVendaDTOTest> itensVenda) {
        this.observacoes = observacoes;
        this.data = data;
        this.clienteId = clienteId;
        this.itensVenda = itensVenda;
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
                ", data='" + data + '\'' +
                ", clienteId=" + clienteId +
                ", itensVenda=" + itensVenda +
                '}';
    }
}

