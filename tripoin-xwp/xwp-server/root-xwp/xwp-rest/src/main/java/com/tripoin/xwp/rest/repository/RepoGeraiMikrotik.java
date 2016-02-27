package com.tripoin.xwp.rest.repository;

import com.tripoin.xwp.data.entity.EntityBank;
import com.tripoin.xwp.data.entity.EntityGeraiMikrotik;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Achmad Fauzi on 2/27/2016.
 */
@Repository
public interface RepoGeraiMikrotik extends PagingAndSortingRepository<EntityBank, BigInteger>{

    @Query("select a from EntityGeraiMikrotik a where a.code=?1")
    EntityGeraiMikrotik selectGeraiMikrotikByCode(String p_Code);
}
