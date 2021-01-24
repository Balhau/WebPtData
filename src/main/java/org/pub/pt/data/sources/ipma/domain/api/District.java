package org.pub.pt.data.sources.ipma.domain.api;

public class District {
    private final int idRegiao;
    private final int idDistrict;
    private final String nome;

    public District(int idRegiao, int idDistrict, String nome) {
        this.idRegiao = idRegiao;
        this.idDistrict = idDistrict;
        this.nome = nome;
    }

    public int getIdRegiao() {
        return idRegiao;
    }

    public int getIdDistrict() {
        return idDistrict;
    }

    public String getNome() {
        return nome;
    }
}
