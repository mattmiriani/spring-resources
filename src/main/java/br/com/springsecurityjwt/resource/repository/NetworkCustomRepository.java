package br.com.springsecurityjwt.resource.repository;

import br.com.springsecurityjwt.resource.dto.SearchNetworkDTO;
import br.com.springsecurityjwt.resource.model.Network;
import br.com.springsecurityjwt.resource.model.Unit;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
@AllArgsConstructor(onConstructor_ = @__({@Autowired}))
public class NetworkCustomRepository {

    private final EntityManager entityManager;

    public Page<Network> find(SearchNetworkDTO search) {
        var query = new StringBuilder("select n.* from tb_network n ");
        var joinUnit = "inner join tb_unit u on n.id = u.network_id ";
        var countQuery = new StringBuilder("select count(*) from tb_network n ");
        var condition = "where ";

        query.append(joinUnit);
        countQuery.append(joinUnit);

        if (search.getSearch().isPresent()) {
            query.append(condition).append("lower(n.name) like :nome ");
            countQuery.append(condition).append("lower(n.name) like :nome ");
            condition = "and ";
        }

        if (nonNull(search.getUnits()) && !search.getUnits().isEmpty()) {
            query.append(condition).append("u.id in (:units) ");
            countQuery.append(condition).append("u.id in (:units) ");
            condition = "and ";
        }

        if (search.getActive().isPresent()) {
            query.append(condition).append("n.active = :active ");
            countQuery.append(condition).append("n.active = :active ");
        }

        query.append("order by n.updated_at desc ");

        System.out.println("Query: " + query);

        var queryBuilder = entityManager.createNativeQuery(query.toString(), Network.class);
        var countQueryBuilder = entityManager.createNativeQuery(countQuery.toString());

        if (search.getSearch().isPresent()) {
            queryBuilder.setParameter("nome", "%" + search.getSearch().orElse("").toLowerCase() + "%");
            countQueryBuilder.setParameter("nome", "%" + search.getSearch().orElse("").toLowerCase() + "%");
        }

        if (nonNull(search.getUnits()) && !search.getUnits().isEmpty()) {
            queryBuilder.setParameter("units", search.getUnits().stream().map(Unit::getId).toList());
            countQueryBuilder.setParameter("units", search.getUnits().stream().map(Unit::getId).toList());
        }

        if (search.getActive().isPresent()) {
            queryBuilder.setParameter("active", search.getActive().get());
            countQueryBuilder.setParameter("active", search.getActive().get());
        }

        var pageable = search.getPageableDTO().getPageable();
        queryBuilder.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        queryBuilder.setMaxResults(pageable.getPageSize());

        var resultList = queryBuilder.getResultList();

        var total = ((Number) countQueryBuilder.getSingleResult()).longValue();

        return new PageImpl<>(resultList, pageable, total);
    }
}
