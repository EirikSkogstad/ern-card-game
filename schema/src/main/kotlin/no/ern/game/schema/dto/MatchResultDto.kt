package no.ern.game.schema.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.time.ZonedDateTime

@ApiModel("MatchResult representation. Data transfer object represents match result")
data class MatchResultDto(

        @ApiModelProperty("Attacker info")
        var attacker: PlayerResultDto? = null,

        @ApiModelProperty("Defender info")
        var defender: PlayerResultDto? = null,

        @ApiModelProperty("Winner name of the match")
        var winnerName: String?= null,

        @ApiModelProperty("When the match result was created")
        var creationTime: ZonedDateTime? = null,

        @ApiModelProperty("MatchResult id")
        var id: String?=null
): Serializable

data class PlayerResultDto(
        @ApiModelProperty("Player id")
        var id: String?=null,
        @ApiModelProperty("Player username")
        var username: String?=null,
        @ApiModelProperty("Total health")
        var health: Int?=null,
        @ApiModelProperty("Total damage")
        var damage: Int?=null,
        @ApiModelProperty("Remaining health, when match is finished")
        var remainingHealth: Int?=null
): Serializable

/** GET
 * example
      {
         "attacker":
         {
             "id": 1,
             "username":"u1",
             "health": 30,
             "damage": 28,
             "remainingHealth": 15
         },
         "defender":{
             "id": 2,
             "username":"u2",
             "health": 25,
             "damage": 15,
             "remainingHealth": -3
         },
        "creationTime": "2017-12-09T21:46:49.374+01:00",
        "winnerName": "u1",
        "id": "1"
      }
 */

/** POST
 * example
    {
    "attacker":
        {
            "id": 1,
            "username":"u1",
            "health": 30,
            "damage": 28,
            "remainingHealth": 15
        },
            "defender":{
            "id": 2,
            "username":"u2",
            "health": 25,
            "damage": 15,
            "remainingHealth": -3
        },
        "winnerName": "u1"
    }
 */