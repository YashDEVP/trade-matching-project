package com.osttra.tradeMatching.repo;



import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



import com.osttra.tradeMatching.models.MatchedTrade;



@Repository
public interface MatchRepository extends JpaRepository<MatchedTrade, Integer>{



//    ResponseEntity<?> findAllByA_tradeRefNum(String partyA_tradeRefNum);
    
    @Query ("SELECT t FROM MatchedTrade t WHERE t.a_tradeRefNum = ?1")
    List<MatchedTrade> findAllByA_tradeRefNum(String partyA_tradeRefNum);




}
