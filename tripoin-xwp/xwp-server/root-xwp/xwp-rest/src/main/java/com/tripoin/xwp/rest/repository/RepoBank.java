package com.tripoin.xwp.rest.repository;

import com.tripoin.xwp.data.entity.EntityBank;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Achmad Fauzi on 2/27/2016.
 */
@Repository
public interface RepoBank extends PagingAndSortingRepository<EntityBank, BigInteger>{

    @Query("select a from EntityBank a where a.code=?1")
    List<EntityBank> selectBanksByCode(String p_Code);
}
