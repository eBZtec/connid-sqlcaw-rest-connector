package com.evolveum.polygon.connector.sqlcaw.rest.api;

import java.util.Date;

public class RUser extends RObject{
    private String cod_grupo;
    private String cod_parceiro;
    private String cod_tipo_usu;
    private String cod_usu;
    private Date dat_alteracao_senha;
    private Date dat_ultimo_acesso;
    private String dsc_email;
    private String password; // password field
    private String flg_ativo;
    private String flg_usu_bloqueado;
    private String id_usu;
    private String idc_ctl_sen_exn;
    private String nom_usu;
    private String qtd_senha_invalida;
    private Boolean enabled;
    // se precisar mais um attr, criar get e set, e mapear nos metodos hashcode, equals e toString

    public String getCod_grupo() {
        return cod_grupo;
    }

    public void setCod_grupo(String cod_grupo) {
        this.cod_grupo = cod_grupo;
    }

    public String getCod_parceiro() {
        return cod_parceiro;
    }

    public void setCod_parceiro(String cod_parceiro) {
        this.cod_parceiro = cod_parceiro;
    }

    public String getCod_tipo_usu() {
        return cod_tipo_usu;
    }

    public void setCod_tipo_usu(String cod_tipo_usu) {
        this.cod_tipo_usu = cod_tipo_usu;
    }

    public String getCod_usu() {
        return cod_usu;
    }

    public void setCod_usu(String cod_usu) {
        this.cod_usu = cod_usu;
    }

    public Date getDat_alteracao_senha() {
        return dat_alteracao_senha;
    }

    public void setDat_alteracao_senha(Date dat_alteracao_senha) {
        this.dat_alteracao_senha = dat_alteracao_senha;
    }

    public Date getDat_ultimo_acesso() {
        return dat_ultimo_acesso;
    }

    public void setDat_ultimo_acesso(Date dat_ultimo_acesso) {
        this.dat_ultimo_acesso = dat_ultimo_acesso;
    }

    public String getDsc_email() {
        return dsc_email;
    }

    public void setDsc_email(String dsc_email) {
        this.dsc_email = dsc_email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFlg_ativo() {
        return flg_ativo;
    }

    public void setFlg_ativo(String flg_ativo) {
        this.flg_ativo = flg_ativo;
    }

    public String getFlg_usu_bloqueado() {
        return flg_usu_bloqueado;
    }

    public void setFlg_usu_bloqueado(String flg_usu_bloqueado) {
        this.flg_usu_bloqueado = flg_usu_bloqueado;
    }

    public String getId_usu() {
        return id_usu;
    }

    public void setId_usu(String id_usu) {
        this.id_usu = id_usu;
    }

    public String getNom_usu() {
        return nom_usu;
    }

    public void setNom_usu(String nom_usu) {
        this.nom_usu = nom_usu;
    }

    public String getQtd_senha_invalida() {
        return qtd_senha_invalida;
    }

    public void setQtd_senha_invalida(String qtd_senha_invalida) {
        this.qtd_senha_invalida = qtd_senha_invalida;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getIdc_ctl_sen_exn() {
        return idc_ctl_sen_exn;
    }

    public void setIdc_ctl_sen_exn(String idc_ctl_sen_exn) {
        this.idc_ctl_sen_exn = idc_ctl_sen_exn;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RUser rUser = (RUser) o;

        if (cod_grupo != null ? !cod_grupo.equals(rUser.cod_grupo) : rUser.cod_grupo != null) return false;
        if (cod_parceiro != null ? !cod_parceiro.equals(rUser.cod_parceiro) : rUser.cod_parceiro != null) return false;
        if (cod_tipo_usu != null ? !cod_tipo_usu.equals(rUser.cod_tipo_usu) : rUser.cod_tipo_usu != null) return false;
        if (cod_usu != null ? !cod_usu.equals(rUser.cod_usu) : rUser.cod_usu != null) return false;
        if (dat_alteracao_senha != null ? !dat_alteracao_senha.equals(rUser.dat_alteracao_senha) : rUser.dat_alteracao_senha != null) return false;
        if (dat_ultimo_acesso != null ? !dat_ultimo_acesso.equals(rUser.dat_ultimo_acesso) : rUser.dat_ultimo_acesso != null) return false;
        if (dsc_email != null ? !dsc_email.equals(rUser.dsc_email) : rUser.dsc_email != null) return false;
        if (password != null ? !password.equals(rUser.password) : rUser.password != null) return false;
        if (flg_ativo != null ? !flg_ativo.equals(rUser.flg_ativo) : rUser.flg_ativo != null) return false;
        if (flg_usu_bloqueado != null ? !flg_usu_bloqueado.equals(rUser.flg_usu_bloqueado) : rUser.flg_usu_bloqueado != null) return false;
        if (id_usu != null ? !id_usu.equals(rUser.id_usu) : rUser.id_usu != null) return false;
        if (idc_ctl_sen_exn != null ? !idc_ctl_sen_exn.equals(rUser.idc_ctl_sen_exn) : rUser.idc_ctl_sen_exn != null) return false;
        if (nom_usu != null ? !nom_usu.equals(rUser.nom_usu) : rUser.nom_usu != null) return false;
        if (qtd_senha_invalida != null ? !qtd_senha_invalida.equals(rUser.qtd_senha_invalida) : rUser.qtd_senha_invalida != null) return false;

        return enabled != null ? enabled.equals(rUser.enabled) : rUser.enabled == null;

    }

    @Override
    public int hashCode() {

        int result = super.hashCode();

        result = 31 * result + (cod_grupo != null ? cod_grupo.hashCode() : 0);
        result = 31 * result + (cod_parceiro != null ? cod_parceiro.hashCode() : 0);
        result = 31 * result + (cod_tipo_usu != null ? cod_tipo_usu.hashCode() : 0);
        result = 31 * result + (cod_usu != null ? cod_usu.hashCode() : 0);
        result = 31 * result + (dat_alteracao_senha != null ? dat_alteracao_senha.hashCode() : 0);
        result = 31 * result + (dat_ultimo_acesso != null ? dat_ultimo_acesso.hashCode() : 0);
        result = 31 * result + (dsc_email != null ? dsc_email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (flg_ativo != null ? flg_ativo.hashCode() : 0);
        result = 31 * result + (flg_usu_bloqueado != null ? flg_usu_bloqueado.hashCode() : 0);
        result = 31 * result + (id_usu != null ? id_usu.hashCode() : 0);
        result = 31 * result + (idc_ctl_sen_exn != null ? idc_ctl_sen_exn.hashCode() : 0);
        result = 31 * result + (nom_usu != null ? nom_usu.hashCode() : 0);
        result = 31 * result + (qtd_senha_invalida != null ? qtd_senha_invalida.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("RUser{");
        sb.append("cod_grupo='").append(cod_grupo).append('\'');
        sb.append("cod_parceiro='").append(cod_parceiro).append('\'');
        sb.append("cod_tipo_usu='").append(cod_tipo_usu).append('\'');
        sb.append("cod_usu='").append(cod_usu).append('\'');
        sb.append("dat_alteracao_senha='").append(dat_alteracao_senha).append('\'');
        sb.append("dat_utlimo_acesso='").append(dat_ultimo_acesso).append('\'');
        sb.append("dsc_email='").append(dsc_email).append('\'');
        sb.append("flg_ativo='").append(flg_ativo).append('\'');
        sb.append("flg_usu_bloqueado='").append(flg_usu_bloqueado).append('\'');
        sb.append("id_usu='").append(id_usu).append('\'');
        sb.append("idc_ctl_sen_exn='").append(idc_ctl_sen_exn).append('\'');
        sb.append("nom_usu='").append(nom_usu).append('\'');
        sb.append("qtd_senha_invalida='").append(qtd_senha_invalida).append('\'');
        sb.append("enabled='").append(enabled).append('\'');

        return sb.toString();
    }

}
