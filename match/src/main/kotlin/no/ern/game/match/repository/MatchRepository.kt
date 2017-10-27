package no.ern.game.match.repository

import no.ern.game.match.domain.model.Match
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


@Repository
interface MatchRepository : CrudRepository<Match, Long>, MatchRepositoryCustom {
    fun findAllByUsername1OrUsername2(username1: String, username2: String): Iterable<Match>
    fun findAllByWinnerName(winnerName: String): Iterable<Match>
}

@Transactional
interface MatchRepositoryCustom {
    fun createMatch1(
            userName1: String,
            userName2: String,
            totalDamage1: Long,
            totalDamage2: Long,
            remainingHealth1: Long,
            remainingHealth2: Long,
            winnerName: String
    ): Long

    fun getMatchesByUserName(username: String): Iterable<Match>
}

open class MatchRepositoryImpl : MatchRepositoryCustom {
    @PersistenceContext
    private lateinit var em: EntityManager

    override fun createMatch1(
            userName1: String,
            userName2: String,
            totalDamage1: Long,
            totalDamage2: Long,
            remainingHealth1: Long,
            remainingHealth2: Long,
            winnerName: String): Long {

        var id = -1L
        val match = Match(
                userName1,
                userName2,
                totalDamage1,
                totalDamage2,
                remainingHealth1,
                remainingHealth2,
                winnerName
        )
        em.persist(match)

        if (match.id!=null) id = match.id!!

        return id
    }

    override fun getMatchesByUserName(username: String): Iterable<Match> {
        val query = em.createQuery("select m from Match m where m.username1 = ?1 OR m.username2=?2", Match::class.java)
        query.setParameter(1, username)
        query.setParameter(2, username)
        return query.resultList.toList()
    }
}