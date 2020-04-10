package india.covid19.tracker.presentation.common

import india.covid19.tracker.domain.entity.Entity

/**
 * @author shinilms
 */

class ConfirmedComparatorDesc : Comparator<Entity.StateWise> {
    override fun compare(o1: Entity.StateWise?, o2: Entity.StateWise?): Int {
        val value1 = o2!!.confirmed - o1!!.confirmed
        if (value1 == 0) {
            return o1.state.compareTo(o2.state, true)
        }
        return value1
    }
}