package DTO;

import java.sql.Date;

public class AssetDTO {
    private Long id;
    private String status;
    private String descricao;
    private Float valorAquisicao;
    private Float valorDepreciado;
    private String estadoConservacao;
    private Long departamentoResponsavelId;
    private Long campusId;
    private Long fornecedorId;
    private Long salaId;
    private Long documentId;
    private Date entrada;
    private Date carga;

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getCarga() {
        return carga;
    }

    public void setCarga(Date carga) {
        this.carga = carga;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getValorAquisicao() {
        return valorAquisicao;
    }

    public void setValorAquisicao(Float valorAquisicao) {
        this.valorAquisicao = valorAquisicao;
    }

    public Float getValorDepreciado() {
        return valorDepreciado;
    }

    public void setValorDepreciado(Float valorDepreciado) {
        this.valorDepreciado = valorDepreciado;
    }

    public String getEstadoConservacao() {
        return estadoConservacao;
    }

    public void setEstadoConservacao(String estadoConservacao) {
        this.estadoConservacao = estadoConservacao;
    }

    public Long getDepartamentoResponsavelId() {
        return departamentoResponsavelId;
    }

    public void setDepartamentoResponsavelId(Long departamentoResponsavelId) {
        this.departamentoResponsavelId = departamentoResponsavelId;
    }

    public Long getCampusId() {
        return campusId;
    }

    public void setCampusId(Long campusId) {
        this.campusId = campusId;
    }

    public Long getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Long fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public Long getSalaId() {
        return salaId;
    }

    public void setSalaId(Long salaId) {
        this.salaId = salaId;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
