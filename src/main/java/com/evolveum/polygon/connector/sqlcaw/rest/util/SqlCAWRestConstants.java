package com.evolveum.polygon.connector.sqlcaw.rest.util;

public interface SqlCAWRestConstants {

    String OBJECT_ATTR_UID = "uid";
    String OBJECT_ATTR_NAME = "name";
    String OBJECT_ATTR_CHANGED = "changed";

    /* SqlCAW User attributes */
    String USER_COD_GRUPO = "cod_grupo";
    String USER_COD_TIPO_USU = "cod_tipo_usu";
    String USER_COD_PARCEIRO = "cod_parceiro";
    String USER_COD_USU = "cod_usu";
    String USER_DAT_ALTERACAO_SENHA = "dat_alteracao_senha";
    String USER_DAT_ULTIMO_ACESSO = "dat_ultimo_acesso";
    String USER_DSC_EMAIL = "dsc_email";
    String USER_FLG_ATIVO = "flg_ativo";
    String USER_FLG_USU_BLOQUEADO = "flg_usu_bloqueado";
    String USER_ID_USU = "id_usu";
    String USER_NOM_USU = "nom_usu";
    String USER_IDC_CTL_SEN_EXN = "idc_ctl_sen_exn";
    String USER_QTD_SENHA_INVALIDA = "qtd_senha_invalida";
    // mapear o novo atributo, o nome deve ser igual ao da tabela no banco de dados

    /* Midpoint Operational attributes */
    String USER_ATTR_PASSWORD = "password";
    String USER_ATTR_ENABLED = "enabled";
}
