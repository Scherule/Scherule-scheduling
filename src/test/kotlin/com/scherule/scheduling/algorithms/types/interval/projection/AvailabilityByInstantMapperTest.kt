package com.scherule.scheduling.algorithms.types.interval.projection

import com.scherule.scheduling.algorithms.Availability
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.*
import org.joda.time.Instant
import org.joda.time.Interval
import java.util.stream.Stream

internal class AvailabilityByInstantMapperTest {

    val mapper = AvailabilityByInstantMapper()

    @Test
    fun mapsSingleIntervalToTwoInstants() {
        val availability = Availability(setOf(Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z")))
        assertThat(mapper.mapByInstants(Stream.of(
                availability)
        )).containsExactly(
                entry(Instant.parse("2017-10-03T14:15Z"), setOf(availability)),
                entry(Instant.parse("2017-10-03T16:00Z"), setOf(availability))
        )
    }

    @Test
    fun mapsTwoNonOverlappingIntervalsToFourInstants() {
        val availability = Availability(
                setOf(
                        Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z"),
                        Interval.parse("2017-10-04T15:45Z/2017-10-04T17:35Z")
                )
        )
        assertThat(mapper.mapByInstants(Stream.of(
                availability)
        )).containsExactly(
                entry(Instant.parse("2017-10-03T14:15Z"), setOf(availability)),
                entry(Instant.parse("2017-10-03T16:00Z"), setOf(availability)),
                entry(Instant.parse("2017-10-04T15:45Z"), setOf(availability)),
                entry(Instant.parse("2017-10-04T17:35Z"), setOf(availability))
        )
    }

    @Test
    fun mapsTwoOverlappingIntervalsToThreeInstants() {
        val firstAvailability = Availability(
                setOf(
                        Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z")
                )
        )
        val secondAvailability = Availability(
                setOf(
                        Interval.parse("2017-10-03T14:15Z/2017-10-03T18:00Z")
                )
        )
        assertThat(mapper.mapByInstants(
                Stream.of(
                        firstAvailability,
                        secondAvailability
                )
        )).containsExactly(
                entry(Instant.parse("2017-10-03T14:15Z"), setOf(firstAvailability, secondAvailability)),
                entry(Instant.parse("2017-10-03T16:00Z"), setOf(firstAvailability)),
                entry(Instant.parse("2017-10-03T18:00Z"), setOf(secondAvailability))
        )
    }

}