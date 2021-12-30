package com.evolveum.polygon.connector.sqlcaw.rest;

import com.evolveum.polygon.connector.sqlcaw.rest.util.SqlCAWRestConstants;
import org.identityconnectors.common.security.GuardedString;
import org.identityconnectors.framework.api.ConnectorFacade;
import org.identityconnectors.framework.common.objects.*;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class AddTest extends BaseTest{

    private Uid createdUid;

    @Test(groups = "addUser")
    public void addUser() throws Exception {

        ConnectorFacade connector = setupConnector();

        Set<Attribute> set = new HashSet<>();
        set.add(AttributeBuilder.build(Name.NAME, "teste01"));
        set.add(AttributeBuilder.build(SqlCAWRestConstants.USER_COD_GRUPO, "codigo01"));
        set.add(AttributeBuilder.build(SqlCAWRestConstants.USER_COD_TIPO_USU, "tipo01"));
        set.add(AttributeBuilder.build(SqlCAWRestConstants.USER_COD_USU, "codigo_usuario01"));
        //set.add(AttributeBuilder.build(SqlCAWRestConstants.USER_DAT_ALTERACAO_SENHA, new Date().toString()));
        //set.add(AttributeBuilder.build(SqlCAWRestConstants.USER_DAT_ULTIMO_ACESSO, new Date().toString()));
        set.add(AttributeBuilder.build(SqlCAWRestConstants.USER_DSC_EMAIL, "usuario@test.com"));
        set.add(AttributeBuilder.build(SqlCAWRestConstants.USER_FLG_ATIVO, "S"));
        set.add(AttributeBuilder.build(SqlCAWRestConstants.USER_FLG_USU_BLOQUEADO, "N"));
        set.add(AttributeBuilder.build(SqlCAWRestConstants.USER_ID_USU, "id_usuario01"));
        set.add(AttributeBuilder.build(SqlCAWRestConstants.USER_NOM_USU, "nome usuario"));
        set.add(AttributeBuilder.build(SqlCAWRestConstants.USER_IDC_CTL_SEN_EXN, "S"));
        set.add(AttributeBuilder.build(SqlCAWRestConstants.USER_QTD_SENHA_INVALIDA, "12"));

        set.add(AttributeBuilder.build(OperationalAttributes.ENABLE_NAME, true));
        set.add(AttributeBuilder.build(OperationalAttributes.PASSWORD_NAME, new GuardedString("smartway".toCharArray())));

        createdUid = connector.create(ObjectClass.ACCOUNT, set, null);

        AssertJUnit.assertNotNull(createdUid);

    }
}
