package br.com.springsecurityjwt.integrations.jwt;

import br.com.springsecurityjwt.integrations.common.AbstractIntegrationTest;
import br.com.springsecurityjwt.resource.dto.SearchNetworkDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;

@Sql(value = {"/sql-for-tests/database-initialization/initialize-database.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql-for-tests/database-initialization/clear-database.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class NetworkIntTest extends AbstractIntegrationTest {

    @Test
    public void shouldFindAllNetworks() throws Exception {
        var pageNetwork = performPostRequestExpectedSuccess("/networks", new SearchNetworkDTO(), Page.class);

        Assertions.assertEquals(pageNetwork.getTotalElements(), 1);
    }
}
